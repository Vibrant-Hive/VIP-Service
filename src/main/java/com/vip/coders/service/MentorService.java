package com.vip.coders.service;

import com.vip.coders.entity.User;
import com.vip.coders.model.AvailableMentorResponse;
import com.vip.coders.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MentorService {

    @Autowired
    private UserRepository userRepository;

    private static AvailableMentorResponse updateProfile(User user) {
        AvailableMentorResponse availableMentorResponse = AvailableMentorResponse.builder().build();
        BeanUtils.copyProperties(user, availableMentorResponse);
        return availableMentorResponse;
    }

    public boolean updateProfile(long userId, String fullName, boolean active, String skills, String role, int experience, String designation, String languages, String zoomLink, String availability, MultipartFile resume, MultipartFile photo) throws IOException {
        User user = this.userRepository.findById(userId).orElse(User.builder().build());
        user.setExperience(experience);
        user.setFullName(fullName);
        user.setSkills(skills);
        user.setRole(role);
        user.setActive(active);
        user.setDesignation(designation);
        user.setLanguages(languages);
        user.setZoomLink(zoomLink);
        user.setAvailability(availability);
        if (resume != null) {
            user.setResume(resume.getBytes());
            user.setResumeFileType(resume.getContentType());
            user.setResumeFileName(user.getFullName() + "_Resume." + FilenameUtils.getExtension(resume.getOriginalFilename()));
        }
        if (photo != null) {
            user.setPhoto(photo.getBytes());
            user.setPhotoFileType(photo.getContentType());
            user.setPhotoFileName(user.getFullName() + "_Photo." + FilenameUtils.getExtension(photo.getOriginalFilename()));
        }
        this.userRepository.save(user);
        return true;
    }

    public List<AvailableMentorResponse> availableMentors() {
        List<User> mentors = this.userRepository.findByRoleAndActive("MENTOR", true);
        List<User> master = this.userRepository.findByRoleAndActive("MASTER", true);
        mentors.addAll(master);
        return mentors.stream().map(MentorService::updateProfile).collect(Collectors.toList());
    }

    public List<AvailableMentorResponse> appliedMentors() {
        List<User> mentors = this.userRepository.findByRoleAndActive("MENTOR", false);
        List<User> master = this.userRepository.findByRoleAndActive("MASTER", false);
        mentors.addAll(master);
        return mentors.stream().map(MentorService::updateProfile).collect(Collectors.toList());
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
}
