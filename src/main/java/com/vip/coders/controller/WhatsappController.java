package com.vip.coders.controller;

import com.vip.coders.model.WhatsAppChatItem;
import com.vip.coders.service.WhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class WhatsappController {

    @Autowired
    WhatsappService whatsappService;

    @PostMapping(path="/analyseWhatsappChat")
    public Map<String, List<WhatsAppChatItem>> analyseWhatsappChat(int learnerId, int mentorId, MultipartFile chatFile){
        return whatsappService.analyseWhatsappChat(learnerId, mentorId, chatFile);

    }
}
