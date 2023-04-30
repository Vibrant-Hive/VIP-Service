package com.vip.coders.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Comparable<UserResponse>{
    Long id;
    String email;
    String mobileNo;
    String role;
    String fullName;
    Boolean active;
    String languages;

    @Override
    public int compareTo(UserResponse o) {
        return 0;
    }
}
