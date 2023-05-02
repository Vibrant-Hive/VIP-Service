package com.vip.coders.service;

import com.vip.coders.entity.Session;
import com.vip.coders.entity.SkillSet;
import com.vip.coders.repository.SessionRepository;
import com.vip.coders.repository.SkillSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Service
public class SkillSetService {

    @Autowired
    SkillSetRepository skillSetRepository;

    public List<SkillSet> getAllSkillSet() {

        List<SkillSet> list = (List<SkillSet>) this.skillSetRepository.findAll();
        return list;

    }
}