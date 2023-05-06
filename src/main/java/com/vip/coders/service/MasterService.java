package com.vip.coders.service;

import com.vip.coders.entity.SupportRequest;
import com.vip.coders.entity.User;
import com.vip.coders.model.MentorResponse;
import com.vip.coders.repository.SkillSetRepository;
import com.vip.coders.repository.SupportRequestRepository;
import com.vip.coders.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillSetRepository skillSetRepository;

    @Autowired
    private SupportRequestRepository supportRequestRepository;

    private static MentorResponse updateProfile(User user) {
        MentorResponse mentorResponse = MentorResponse.builder().build();
        BeanUtils.copyProperties(user, mentorResponse);
        return mentorResponse;
    }

    public List<MentorResponse> appliedMentors() {
        List<User> master = this.userRepository.findByRoleAndActive("MASTER", false);
        List<User> mentors = this.userRepository.findByRoleAndActive("MENTOR", false);
        master.addAll(mentors);
        return master.stream().map(MasterService::updateProfile).collect(Collectors.toList());
    }

    public boolean approveMentor(long userId, int rate) {
        Optional<User> user = this.userRepository.findById(userId);
        user.ifPresent(value -> {
            user.get().setActive(true);
            user.get().setRate(rate);
            this.userRepository.save(value);
        });
        return true;
    }



    public List<SupportRequest> getAllSupportRequests() {
        return (List<SupportRequest>) supportRequestRepository.findAll();
    }

    public boolean approveSupport(int requestId) {
        supportRequestRepository.findById(requestId).ifPresent(supportRequest -> {
            supportRequest.setVerified(true);
            supportRequestRepository.save(supportRequest);
        });
        return true;
    }
}
