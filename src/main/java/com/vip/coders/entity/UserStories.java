package com.vip.coders.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER_STORIES")
public class UserStories {

    @Id
    Integer storyNumber;

    Integer learnerId;

    String storyName;

    String repositoryName;

    String storyStatus;

    Date storyCreatedDate;

    Date storyCompletedDate;

}
