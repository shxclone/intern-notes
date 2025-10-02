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

    CONSTRAINT pk_job_exceptions PRIMARY KEY (job_id, record_key)
);

CREATE TABLE job_history (
    id SERIAL PRIMARY KEY,
    job_id VARCHAR(200) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP,
    status VARCHAR(10),
    message TEXT
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
('Daily Award Sync', 'Y', NOW() - INTERVAL '1 day', 'S', NOW() + INTERVAL '1 day', 'JOB_SYNC_001', 'S', 'DAILY', '0 0 2 * * ?', NOW() - INTERVAL '30 days', NULL, '02:00', 'system', 'system', NOW(), NOW(), 1),
('Weekly Report Generation', 'Y', NOW() - INTERVAL '7 days', 'P', NOW() + INTERVAL '7 days', 'JOB_REP_002', 'P', 'WEEKLY', '0 0 4 * * ?', NOW() - INTERVAL '60 days', NULL, '04:00', 'system', 'admin', NOW(), NOW(), 2),
('Monthly Cleanup', 'N', NOW() - INTERVAL '30 days', 'E', NOW() + INTERVAL '30 days', 'JOB_CLN_003', 'E', 'MONTHLY', '0 0 23 1 * ?', NOW() - INTERVAL '120 days', NULL, '23:00', 'system', 'admin', NOW(), NOW(), 3);

INSERT INTO job_exceptions (job_code, excep_id, excep_desc, date_created)
VALUES
('JOB_CLN_003', 'EX001', 'Disk write permission denied during cleanup', NOW() - INTERVAL '30 days'),
('JOB_CLN_003', 'EX002', 'Cleanup timed out while deleting temp files', NOW() - INTERVAL '30 days' + INTERVAL '5 minutes'),
('JOB_REP_002', 'EX003', 'Connection to reporting DB timed out, retry scheduled', NOW() - INTERVAL '1 hour');
