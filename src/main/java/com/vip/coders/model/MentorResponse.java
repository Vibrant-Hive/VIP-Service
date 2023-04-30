package com.vip.coders.model;

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
    String skills;
    Integer experience;
    String designation;
    String languages;
    Integer rate;
}
