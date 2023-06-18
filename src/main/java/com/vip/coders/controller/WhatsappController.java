package com.vip.coders.controller;

import com.vip.coders.service.WhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class WhatsappController {

    @Autowired
    WhatsappService whatsappService;

    @PostMapping(path="/analyseWhatsappChat")
    public Double analyseWhatsappChat(int learnerId, int mentorId, MultipartFile chatFile){
        whatsappService.analyseWhatsappChat(learnerId, mentorId, chatFile);

        return null;
    }
}
