package com.vip.coders.controller;

import com.vip.coders.entity.LearnerReport;
import com.vip.coders.service.LearnerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {
    @Autowired
    LearnerReportService learnerReportService;


    @GetMapping(path = "/getLearnerReport")
    public LearnerReport getLearnerReport(@RequestParam Integer id) {
        return learnerReportService.getLearnerReport(id);

    }

    @PostMapping(path = "/saveLearnerReport")
    public LearnerReport learnerReport(@RequestBody LearnerReport learnerReport) {
        return learnerReportService.saveLearnerReport(learnerReport);
    }

}
