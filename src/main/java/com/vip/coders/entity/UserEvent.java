package com.vip.coders.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER_EVENTS")
public class UserEvent {

    @GeneratedValue
    @Id
    Long id;
    Integer userId;
    String ipAddress;
    String city;
    String eventName;
    Date eventDate;
}
