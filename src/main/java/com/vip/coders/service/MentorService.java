package com.vip.coders.service;

import com.vip.coders.entity.MentorFiles;
import com.vip.coders.entity.SkillSet;
import com.vip.coders.entity.SupportRequest;
import com.vip.coders.entity.User;
import com.vip.coders.model.MentorResponse;
import com.vip.coders.repository.SkillSetRepository;
import com.vip.coders.repository.SupportRequestRepository;
import com.vip.coders.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentorService {

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

    public boolean updateProfile(long userId, String fullName, boolean active, int skillSetId, String role, int experience, String designation, String languages, String zoomLink, String availability, MultipartFile resume, MultipartFile photo) throws IOException {
        SkillSet skillSet = this.skillSetRepository.findById(skillSetId).orElse(SkillSet.builder().build());
        User user = this.userRepository.findById(userId).orElse(User.builder().build());
        user.setExperience(experience);
        user.setFullName(fullName);
        user.setSkillSet(skillSet);
        user.setRole(role);
        user.setActive(active);
        user.setDesignation(designation);
        user.setLanguages(languages);
        user.setZoomLink(zoomLink);
        user.setAvailability(availability);
        if (user.getMentorFiles() == null) {
            user.setMentorFiles(MentorFiles.builder().build());
        }
//        if (resume != null) {
//            user.getMentorFiles().setResume(resume.getBytes());
//            user.getMentorFiles().setResumeFileType(resume.getContentType());
//            user.getMentorFiles().setResumeFileName(user.getFullName() + "_Resume." + FilenameUtils.getExtension(resume.getOriginalFilename()));
//        }
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

    public SupportRequest requestForSupport(int learnerId, int mentorId) {
        SupportRequest supportRequest = SupportRequest.builder()
                .requestedOn(new Date(System.currentTimeMillis()))
                .mentorId(mentorId)
                .learnerId(learnerId)
                .verified(false)
                .build();
        return supportRequestRepository.save(supportRequest);
    }

    public SupportRequest getSupportRequest(int learnerId, int mentorId) {
        return supportRequestRepository.findByLearnerIdAndMentorId(learnerId, mentorId);
    }
}
