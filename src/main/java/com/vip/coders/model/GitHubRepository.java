package com.vip.coders.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitHubRepository {
    private Long id;
    private String name;
    private String description;
    private String htmlUrl;

}
