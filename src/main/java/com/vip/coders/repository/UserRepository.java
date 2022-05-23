package com.vip.coders.repository;

import com.vip.coders.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
