package com.vip.coders.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUser {
    private Long id;
    private String login;
    private String name;
    private String email;
    private String avatarUrl;
}
