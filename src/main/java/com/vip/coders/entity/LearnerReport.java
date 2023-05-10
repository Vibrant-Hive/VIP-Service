package com.vip.coders.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LearnerReport {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    Integer learnerId;
    Integer mentorId;
    Double selfLearning;
    Double onTimeCompletion;
    Double consistency;
    Double collaboration;
    Double learningSpirit;
    Double passion;
    Double synergy;
    Double willPower;


}
