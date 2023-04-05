package com.vip.coders.service;

import com.vip.coders.entity.Session;
import com.vip.coders.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;
    public Session save(Session session) {
        return sessionRepository.save(session);
    }
}
