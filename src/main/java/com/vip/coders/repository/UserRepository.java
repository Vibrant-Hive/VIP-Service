package com.vip.coders.repository;

import com.vip.coders.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByRoleAndActive(String role, boolean isActive);

    User findByMobileNo(String mobileNo);
}
