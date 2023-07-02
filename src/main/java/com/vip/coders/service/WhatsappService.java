package com.vip.coders.service;

import com.vip.coders.model.WhatsAppChatItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WhatsappService {

    public Map<String, List<WhatsAppChatItem>> analyseWhatsappChat(int learnerId, int mentorId, MultipartFile chatFile) {
        List<String> fileContent = readFile(chatFile);
        List<WhatsAppChatItem> whatsAppChatItemList = new ArrayList<>();
        fileContent.forEach( str -> {
            if(str.contains(" - ")) {
                String[] stringArr = str.split(" - ");
                if (!stringArr[1].contains("end-to-end encrypted")) {
                    if(stringArr[0].length() == 17 && stringArr[0].contains(":")) {
                        WhatsAppChatItem chatItem = new WhatsAppChatItem();
                        chatItem.setMessageTime(stringArr[0]);
                        chatItem.setSenderName(stringArr[1].split(":")[0]);
                        chatItem.setMessage(stringArr[1].split(":")[1]);

                        whatsAppChatItemList.add(chatItem);
                    }
                }
            }
        });

        return convert(whatsAppChatItemList);
    }

    public Map<String, List<WhatsAppChatItem>> convert(List<WhatsAppChatItem> list) {
        Map<String, List<WhatsAppChatItem>> map = new HashMap<>();
        for (WhatsAppChatItem whatsAppChatItem : list) {
            if(!map.containsKey(whatsAppChatItem.getSenderName())){
                map.put(whatsAppChatItem.getSenderName(), new ArrayList<>());
            }
            map.get(whatsAppChatItem.getSenderName()).add(whatsAppChatItem);
        }
        return map;
    }

    private List<String> readFile(MultipartFile file) {
        BufferedReader br;
        List<String> result = new ArrayList<>();
        try {

            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return result;

    }
}
