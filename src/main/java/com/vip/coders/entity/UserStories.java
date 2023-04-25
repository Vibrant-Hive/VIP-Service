package com.vip.coders.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserStories {
    @Id
    Integer learnerId;

    Integer storyNumber;

    String storyName;

    String repositoryName;

    String storyStatus;

    Date storyCreatedDate;

    Date storyCompletedDate;

}
