package com.vip.coders.controller;

import com.vip.coders.entity.UserStories;
import com.vip.coders.service.UserStoriesService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserStoriesController {

    @Autowired
    UserStoriesService userStoriesService;

    @SneakyThrows
    @GetMapping(path = "/syncUserStories")
    public List<UserStories> syncUserStories(@RequestParam Integer learnerId, @RequestParam String repoName) {
        return userStoriesService.getOrganisationIssues(learnerId, repoName);

    }


}
