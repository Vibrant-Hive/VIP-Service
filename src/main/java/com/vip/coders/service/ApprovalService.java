package com.vip.coders.service;

import com.vip.coders.entity.User;
import com.vip.coders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovalService {

    @Autowired
    private UserRepository userRepository;

    public List<User> appliedMentors() {
        return this.userRepository.findByRoleAndActive("MENTOR", false);
    }

    public byte[] downloadResume(long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        return user.map(User::getResume).orElse(null);
    }

    public boolean approveMentor(long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        user.ifPresent(value -> {
            user.get().setActive(true);
            this.userRepository.save(value);
        });
        return true;
    }
}
