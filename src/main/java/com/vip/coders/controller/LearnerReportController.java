package com.vip.coders.controller;

import com.vip.coders.entity.LearnerReport;
import com.vip.coders.service.LearnerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
public class LearnerReportController {
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
    @PostMapping(path = "/calculateOnTimeCompletion")
    public LearnerReport calculateOnTimeCompletion(@RequestParam Integer learnerId, @RequestParam Integer mentorId) {
        return learnerReportService.calculateOnTimeCompletion(learnerId, mentorId);
    }
}
