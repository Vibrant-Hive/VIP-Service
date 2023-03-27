package com.vip.coders.repository;

import com.vip.coders.entity.Session;
import com.vip.coders.entity.SkillSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillSetRepository extends CrudRepository<SkillSet, Integer> {
}
