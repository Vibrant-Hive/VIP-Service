package com.vip.coders.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubIssue {

    private int number;
    private String title;
    private GitHubUser assignee;
    private GitHubRepository repository;
    private String state;
    private Date createdAt;
    private Date closedAt;
}

