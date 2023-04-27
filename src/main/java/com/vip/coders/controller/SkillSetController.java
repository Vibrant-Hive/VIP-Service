package com.vip.coders.controller;


import com.vip.coders.entity.SkillSet;
import com.vip.coders.model.MentorResponse;
import com.vip.coders.service.SkillSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillSetController {
    @Autowired
    private SkillSetService skillSetService;

}


