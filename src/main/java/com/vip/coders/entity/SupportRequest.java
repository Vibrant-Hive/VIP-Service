package com.vip.coders.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SUPPORT_REQUESTS")
public class SupportRequest {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    int learnerId;
    int mentorId;
    Date requestedOn;
    boolean verified;
}
