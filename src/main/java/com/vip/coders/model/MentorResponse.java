package com.vip.coders.model;

import com.vip.coders.entity.SkillSet;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorResponse {
    Long id;
    String fullName;
    SkillSet skillSet;
    Integer experience;
    String designation;
    Integer rate;
}
