package com.vip.coders.service;

import com.vip.coders.entity.LearnerReport;
import com.vip.coders.entity.User;
import com.vip.coders.entity.UserStories;
import com.vip.coders.repository.LearnerReportRepository;
import com.vip.coders.repository.UserRepository;
import com.vip.coders.repository.UserStoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class LearnerReportService {

    @Autowired
    LearnerReportRepository learnerReportRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserStoriesRepository userStoriesRepository;

    public LearnerReport getLearnerReport(Integer id) {
        return this.learnerReportRepository.findById(id).orElse(null);
    }

    public LearnerReport saveLearnerReport(LearnerReport learnerReport) {
        return learnerReportRepository.save(learnerReport);
    }
    public <UserStoriesRepository> void UserStoriesService(UserRepository userRepository, UserStoriesRepository userStoriesRepository) {
        this.userRepository = userRepository;
        this.userStoriesRepository = userStoriesRepository;
    }

    public List<UserStories> getUserStoriesByLearnerId(Long learnerId) {
        // Check if the learner exists in the database
        User learner = userRepository.findById(learnerId)
                .orElseThrow(() -> new RuntimeException("Learner not found with ID: " + learnerId));

        // Fetch all user stories assigned to the learner
        return userStoriesRepository.findByLearner(learner);
    }
}
