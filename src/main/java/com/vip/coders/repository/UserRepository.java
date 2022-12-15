package com.vip.coders.repository;

import com.vip.coders.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByRoleAndActive(String role, boolean isActive);
}
