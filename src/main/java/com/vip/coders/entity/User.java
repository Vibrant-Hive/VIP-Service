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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    String githubUsername;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_files_id", referencedColumnName = "id")
    MentorFiles mentorFiles;

}
