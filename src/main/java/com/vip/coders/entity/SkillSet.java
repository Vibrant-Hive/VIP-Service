package com.vip.coders.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SKILL_SET")
public class SkillSet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    String skillSetName;
    Boolean active;
    String relatedTechnologies;
}
