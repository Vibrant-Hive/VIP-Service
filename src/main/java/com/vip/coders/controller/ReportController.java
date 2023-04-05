package com.vip.coders.controller;

import com.vip.coders.entity.LearnerReport;
import com.vip.coders.service.LearnerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    private LearnerReportService learnerReportService;


    @GetMapping(path = "/getLearnerReport")
    public LearnerReport getLearnerReport(@RequestParam Integer id) {
        return learnerReportService.getLearnerReport(id);

    }

}
