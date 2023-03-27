package com.vip.coders.entity;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

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
