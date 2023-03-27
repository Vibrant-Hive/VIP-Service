package com.vip.coders.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class User {

    @GeneratedValue
    @Id
    Long id;

    String email;
    String mobileNo;
    String password;
    String role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_set_id", referencedColumnName = "id")
    SkillSet skillSet;
    String fullName;
    Boolean active;
    Integer experience;
    String availability;
    String languages;
    String designation;
    Integer rate;
    String zoomLink;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mentor_files_id", referencedColumnName = "id")
    MentorFiles mentorFiles;

}
