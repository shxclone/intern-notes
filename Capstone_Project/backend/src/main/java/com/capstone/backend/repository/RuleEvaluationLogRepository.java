package com.capstone.backend.repository;

import com.capstone.backend.model.RuleEvaluationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RuleEvaluationLogRepository extends JpaRepository<RuleEvaluationLog, Long> {

    List<RuleEvaluationLog> findByJobCode(String jobCode);

    List<RuleEvaluationLog> findByRuleCode(String ruleCode);
}
