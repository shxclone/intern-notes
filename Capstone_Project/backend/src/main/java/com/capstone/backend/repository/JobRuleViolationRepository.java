package com.capstone.backend.repository;

import com.capstone.backend.model.JobRuleViolation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobRuleViolationRepository extends JpaRepository<JobRuleViolation, Long> {

    List<JobRuleViolation> findByStatus(String status);

    Optional<JobRuleViolation> findByJobCodeAndRuleCode(String jobCode, String ruleCode);
}
