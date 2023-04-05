package com.vip.coders.service;

import com.vip.coders.entity.LearnerReport;
import com.vip.coders.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LearnerReportService {

    @Autowired
    ReportRepository reportRepository;

    public LearnerReport getLearnerReport(Integer id) {
        return this.reportRepository.findById(id).orElse(null);
    }
}
