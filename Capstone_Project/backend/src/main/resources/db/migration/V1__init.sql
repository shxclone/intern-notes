CREATE TABLE jobs (
    id SERIAL PRIMARY KEY,
    job_name VARCHAR(100) NOT NULL,
    active CHAR(1),
    last_triggered_date TIMESTAMP,
    trigger_status VARCHAR(10),
    next_trigger_date TIMESTAMP,
    job_code VARCHAR(200),
    last_result VARCHAR(500),
    schedule_period VARCHAR(50),
    cron_expression VARCHAR(100),
    start_date DATE,
    end_date DATE,
    run_time VARCHAR(10),
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    date_created TIMESTAMP,
    date_updated TIMESTAMP,
    job_type INT
);

CREATE TABLE job_exceptions (
    job_code          VARCHAR(200)   NOT NULL,
    record_key      BIGSERIAL     NOT NULL,
    excep_id        VARCHAR(20),
    excep_desc      VARCHAR(4000),
    date_created    TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_job_exceptions PRIMARY KEY (job_code, record_key)
);

CREATE TABLE job_history (
    id SERIAL PRIMARY KEY,
    job_code VARCHAR(200) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP,
    status VARCHAR(10),
    message TEXT
);

CREATE TABLE rules (
    id               SERIAL PRIMARY KEY,
    rule_code        VARCHAR(50)  NOT NULL UNIQUE,
    rule_name        VARCHAR(255) NOT NULL,
    expression       VARCHAR(1000) NOT NULL,
    severity         VARCHAR(20)  NOT NULL CHECK (severity IN ('LOW','MEDIUM','HIGH')),
    active           CHAR(1)      NOT NULL DEFAULT 'Y' CHECK (active IN ('Y','N')),
    message_template VARCHAR(500),
    created_by       VARCHAR(100),
    updated_by       VARCHAR(100),
    date_created     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_updated     TIMESTAMP
);

CREATE TABLE rule_evaluation_log (
    id             SERIAL PRIMARY KEY,
    job_code       VARCHAR(200) NOT NULL,
    rule_code      VARCHAR(50) NOT NULL,
    passed         CHAR(1) NOT NULL CHECK (passed IN ('Y','N')),
    evaluated_at   TIMESTAMP NOT NULL DEFAULT NOW(),
    message        VARCHAR(500)
)
PARTITION BY RANGE (evaluated_at);

-- Pre-create monthly partitions (12 months ahead) to simulate Oracle's interval partitioning
DO $$
DECLARE
    start_date   DATE := date_trunc('month', now());
    i            INT;
    part_name    TEXT;
    start_part   DATE;
    end_part     DATE;
BEGIN
    FOR i IN 0..12 LOOP
        start_part := start_date + (i || ' month')::interval;
        end_part   := start_part + interval '1 month';
        part_name  := 'rule_evaluation_log_' || to_char(start_part, 'YYYYMM');

        EXECUTE format(
            'CREATE TABLE IF NOT EXISTS %I PARTITION OF rule_evaluation_log
             FOR VALUES FROM (%L) TO (%L);',
            part_name, start_part, end_part
        );
    END LOOP;
END $$;

CREATE TABLE job_rule_violations (
    id              SERIAL PRIMARY KEY,
    job_code        VARCHAR(200) NOT NULL,
    rule_code       VARCHAR(50) NOT NULL,
    first_detected  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_detected   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status          VARCHAR(20) NOT NULL CHECK (status IN ('ACTIVE','ACKNOWLEDGED')),
    severity        VARCHAR(20) NOT NULL CHECK (severity IN ('LOW','MEDIUM','HIGH')),
    message         VARCHAR(500),
    updated_by      VARCHAR(100)
);

INSERT INTO jobs (
    job_name,
    active,
    last_triggered_date,
    trigger_status,
    next_trigger_date,
    job_code,
    last_result,
    schedule_period,
    cron_expression,
    start_date,
    end_date,
    run_time,
    created_by,
    updated_by,
    date_created,
    date_updated,
    job_type
) VALUES
('Daily Award Sync', 'Y', NOW() - INTERVAL '1 day', 'S', NOW() + INTERVAL '1 day', 'JOB_SYNC_001', 'S', 'DAILY', '0 * * * * ?', NOW() - INTERVAL '30 days', NULL, '02:00', 'system', 'system', NOW(), NOW(), 1),
('Weekly Report Generation', 'Y', NOW() - INTERVAL '7 days', 'P', NOW() + INTERVAL '7 days', 'JOB_REP_002', 'P', 'WEEKLY', '0 0 4 * * ?', NOW() - INTERVAL '60 days', NULL, '04:00', 'system', 'admin', NOW(), NOW(), 2),
('Monthly Cleanup', 'N', NOW() - INTERVAL '30 days', 'E', NOW() + INTERVAL '30 days', 'JOB_CLN_003', 'E', 'MONTHLY', '0 0 23 1 * ?', NOW() - INTERVAL '120 days', NULL, '23:00', 'system', 'admin', NOW(), NOW(), 3),
('Broken Cron Job', 'Y', NOW() - INTERVAL '10 days', 'S', NOW() + INTERVAL '1 day', 'JOB_BADCRON_005', 'S', 'DAILY', NULL, NOW() - INTERVAL '30 days', NULL, '02:00', 'system', 'system', NOW(), NOW(), 2);

INSERT INTO job_exceptions (job_code, excep_id, excep_desc, date_created)
VALUES
('JOB_CLN_003', 'EX001', 'Disk write permission denied during cleanup', NOW() - INTERVAL '30 days'),
('JOB_CLN_003', 'EX002', 'Cleanup timed out while deleting temp files', NOW() - INTERVAL '30 days' + INTERVAL '5 minutes'),
('JOB_REP_002', 'EX003', 'Connection to reporting DB timed out, retry scheduled', NOW() - INTERVAL '1 hour');

INSERT INTO rules (
    rule_code,
    rule_name,
    expression,
    severity,
    active,
    message_template,
    created_by,
    updated_by,
    date_created,
    date_updated
) VALUES
('INACTIVE_90D', 'Inactive Job (90 Days)', 'days_since_last_run > 90', 'MEDIUM', 'Y', 'Job has been inactive for #{days_since_last_run} days (threshold 90).', 'system', 'system', NOW(), NOW()),
('FAIL_3X', 'Consecutive Failures (3x)', 'failure_count >= 3', 'HIGH', 'Y', 'Job failed consecutively #{failure_count} times (threshold 3).', 'system', 'system', NOW(), NOW()),
('MISSING_CRON', 'Missing Cron Expression', 'cron_expression IS NULL OR TRIM(cron_expression) = ''''', 'LOW', 'Y', 'Job is missing a valid cron expression.', 'system', 'system', NOW(), NOW());