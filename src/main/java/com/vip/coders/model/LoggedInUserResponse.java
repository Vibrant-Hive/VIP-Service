package com.vip.coders.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoggedInUserResponse {
    Long id;
    String email;
    String mobileNo;
    String role;
    String fullName;
    Boolean active;
}
