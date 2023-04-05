package com.vip.coders.repository;

import com.vip.coders.entity.LearnerReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends
        CrudRepository<LearnerReport, Integer> {
}
