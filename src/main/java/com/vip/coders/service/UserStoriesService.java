package com.vip.coders.service;

import com.vip.coders.entity.UserStories;
import com.vip.coders.model.GitHubIssue;
import com.vip.coders.model.GitHubUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserStoriesService {

    private static final String ISSUE_STATE_OPEN = "open";

    private final RestTemplate restTemplate;
    private String githubUrl = "https://api.github.com";
    private String githubToken = "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";

    public UserStoriesService(RestTemplateBuilder restTemplateBuilder,
                              @Value("${github.url}") String githubUrl,
                              @Value("${github.token}") String githubToken) {
        this.restTemplate = restTemplateBuilder.build();

    }

    public List<UserStories> getOrganisationIssuesAssignedToAuthenticatedUser(Integer learnerId) {
        String url = githubUrl + "/issues";
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setBearerAuth(githubToken);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<GitHubIssue[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, GitHubIssue[].class);
        List<GitHubIssue> issues = Arrays.asList(response.getBody());
        List<UserStories> userStories = new ArrayList<>();
        for (GitHubIssue issue : issues) {
            if (issue.getAssignee() != null && issue.getAssignee().getLogin().equals(getUser().getLogin()) && issue.getState().equals(ISSUE_STATE_OPEN)) {
                UserStories story = UserStories.builder()
                        .learnerId(Math.toIntExact(getUser().getId()))
                        .storyNumber(issue.getNumber())
                        .storyName(issue.getTitle())
                        .repositoryName(issue.getRepository().getName())
                        .storyStatus(issue.getState())
                        .storyCreatedDate(issue.getCreatedAt())
                        .storyCompletedDate(issue.getClosedAt())
                        .build();
                userStories.add(story);
            }
        }
        return userStories;
    }

    private GitHubUser getUser() {
        String url = githubUrl + "/user";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(githubToken);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<GitHubUser> response = restTemplate.exchange(url, HttpMethod.GET, entity, GitHubUser.class);
        return response.getBody();
    }

}















