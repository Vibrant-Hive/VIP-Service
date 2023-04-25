package com.vip.coders.repository;

import com.vip.coders.entity.UserStories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoriesRepository extends CrudRepository<UserStories, Integer> {


}
