package com.vip.coders.service;

import com.vip.coders.entity.UserStories;
import com.vip.coders.model.GitHubUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public List<UserStories> getOrganisationIssues(Integer learnerId) {

        return null;
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















