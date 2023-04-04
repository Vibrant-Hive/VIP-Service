package com.vip.coders.controller;

import com.vip.coders.entity.LearnerReport;
import com.vip.coders.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ReportController {
    @Autowired
    private ReportRepository reportRepository;


    @GetMapping(path = "/getLearnerReport")
    public Optional<LearnerReport> findById(@RequestParam Integer id) {
        Optional<LearnerReport> learnerReport = this.reportRepository.findById(id);
        return learnerReport;

    }

}
