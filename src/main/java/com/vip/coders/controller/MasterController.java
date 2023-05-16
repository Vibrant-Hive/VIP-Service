package com.vip.coders.controller;

import com.vip.coders.service.MasterService;
import com.vip.coders.util.HTMLTableConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterController {

    @Autowired
    MasterService masterService;

    @GetMapping(path = "/getAllSupportRequests")
    public String getAllSupportRequests() throws NoSuchFieldException, IllegalAccessException {
        return HTMLTableConverter.convertListToHTMLTable(masterService.getAllSupportRequests());
    }

    @GetMapping(path = "/approveSupport")
    public boolean approveSupport(@RequestParam int id) {
        return masterService.approveSupport(id);
    }

    @GetMapping(path = "/appliedMentors")
    public String appliedMentors() throws NoSuchFieldException, IllegalAccessException {
        return HTMLTableConverter.convertListToHTMLTable(masterService.appliedMentors());
    }

    @GetMapping(path = "/approveMentor")
    public boolean approveMentor(@RequestParam long id, @RequestParam int rate) {
        return masterService.approveMentor(id, rate);
    }

}
