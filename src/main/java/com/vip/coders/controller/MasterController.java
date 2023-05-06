package com.vip.coders.controller;

import com.vip.coders.entity.SupportRequest;
import com.vip.coders.model.MentorResponse;
import com.vip.coders.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MasterController {

    @Autowired
    MasterService masterService;

    @GetMapping(path = "/getAllSupportRequests")
    public List<SupportRequest> getAllSupportRequests() {
        return masterService.getAllSupportRequests();
    }

    @GetMapping(path = "/approveSupport")
    public boolean approveSupport(@RequestParam int requestId) {
        return masterService.approveSupport(requestId);
    }

    @GetMapping(path = "/appliedMentors")
    public List<MentorResponse> appliedMentors() {
        return masterService.appliedMentors();
    }

    @GetMapping(path = "/approveMentor")
    public boolean approveMentor(@RequestParam long userId, @RequestParam int rate) {
        return masterService.approveMentor(userId, rate);
    }

}
