package com.vip.coders.repository;

import com.vip.coders.entity.User;
import com.vip.coders.entity.UserStories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface UserStoriesRepository extends CrudRepository<UserStories, Integer> {
    UserStories findByStoryName(String storyTitle);

    List findByLearner(User learner);
}
