package com.vip.coders.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class WhatsappService {

    public Double analyseWhatsappChat(int learnerId, int mentorId, MultipartFile chatFile) {
        List<String> fileContent = readFile(chatFile);
        fileContent.forEach(s -> {
            //your code goes here
        });
        return null;
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
