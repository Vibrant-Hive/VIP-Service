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
    String fullName;
    String skills;
    Boolean active;
    Integer experience;
    String availability;
    String languages;
    String designation;
    Integer rate;
    String zoomLink;
    String resumeFileType;
    String photoFileType;
    String resumeFileName;
    String photoFileName;
    @Lob
    byte[] resume;

    @Lob
    byte[] photo;

}
