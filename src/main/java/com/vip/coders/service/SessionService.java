package com.vip.coders.service;

import com.vip.coders.entity.Session;
import com.vip.coders.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;
    public Session save(Session session) {
        session.getSessionRecord().setUploadedDate(new Date(System.currentTimeMillis()));
        return sessionRepository.save(session);
    }
}
