package com.vip.coders.controller;

import com.vip.coders.entity.User;
import com.vip.coders.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApprovalController {
    @Autowired
    private ApprovalService approvalService;

    @GetMapping(path = "/appliedMentors")
    public List<User> appliedMentorsUrl() {
        return approvalService.appliedMentors();
    }

    @GetMapping(path = "/downloadResume")
    public ResponseEntity<byte[]> downloadResume(@RequestParam long userId) {

        byte[] resume = approvalService.downloadResume(userId);
        String fileName = "resume.docx";

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(resume.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return new ResponseEntity<>(resume, respHeaders, HttpStatus.OK);
    }


    @GetMapping(path = "/approveMentor")
    public boolean approveMentor(@RequestParam long userId) {
        return approvalService.approveMentor(userId);
    }

}
