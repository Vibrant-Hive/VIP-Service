package com.vip.coders.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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

    @Lob
    byte[] resume;
}
