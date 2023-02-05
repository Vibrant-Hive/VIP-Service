package com.vip.coders.service;

import com.vip.coders.entity.MentorFiles;
import com.vip.coders.entity.User;
import com.vip.coders.model.MentorResponse;
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

    private static MentorResponse updateProfile(User user) {
        MentorResponse mentorResponse = MentorResponse.builder().build();
        BeanUtils.copyProperties(user, mentorResponse);
        return mentorResponse;
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
        if(user.getMentorFiles() == null){
            user.setMentorFiles(MentorFiles.builder().build());
        }
        if (resume != null) {
            user.getMentorFiles().setResume(resume.getBytes());
            user.getMentorFiles().setResumeFileType(resume.getContentType());
            user.getMentorFiles().setResumeFileName(user.getFullName() + "_Resume." + FilenameUtils.getExtension(resume.getOriginalFilename()));
        }
        if (photo != null) {
            user.getMentorFiles().setPhoto(photo.getBytes());
            user.getMentorFiles().setPhotoFileType(photo.getContentType());
            user.getMentorFiles().setPhotoFileName(user.getFullName() + "_Photo." + FilenameUtils.getExtension(photo.getOriginalFilename()));
        }
        this.userRepository.save(user);
        return true;
    }

    public List<MentorResponse> availableMentors() {
        List<User> master = this.userRepository.findByRoleAndActive("MASTER", true);
        List<User> mentors = this.userRepository.findByRoleAndActive("MENTOR", true);
        master.addAll(mentors);
        return master.stream().map(MentorService::updateProfile).collect(Collectors.toList());
    }

    public List<MentorResponse> appliedMentors() {
        List<User> master = this.userRepository.findByRoleAndActive("MASTER", false);
        List<User> mentors = this.userRepository.findByRoleAndActive("MENTOR", false);
        master.addAll(mentors);
        return master.stream().map(MentorService::updateProfile).collect(Collectors.toList());
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
