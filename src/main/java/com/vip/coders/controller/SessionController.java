package com.vip.coders.controller;

import com.vip.coders.entity.Session;
import com.vip.coders.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @Autowired
    SessionService sessionService;

    @PostMapping(path = "/saveSession")
    public Session saveSession(@RequestBody Session session){
        return sessionService.save(session);
    }
}
