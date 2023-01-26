package com.vip.coders.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableMentorResponse {
    long id;
    String fullName;
    String skills;
    int experience;
    String designation;
    int rate;
}
