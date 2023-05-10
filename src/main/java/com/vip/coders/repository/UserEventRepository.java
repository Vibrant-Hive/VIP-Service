package com.vip.coders.repository;

import com.vip.coders.entity.UserEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository extends CrudRepository<UserEvent, Long> {
}
