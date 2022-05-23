package com.vip.coders.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
