package com.vip.coders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "MENTOR_AVAILABILITY")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MentorAvailability {
    @Id
    @GeneratedValue
    int availabilityId;
    String morningTime;
    String eveningTime;
    String noonTime;
    String nightTime;
}
