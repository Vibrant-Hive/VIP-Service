package com.vip.coders.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SESSIONS")
public class Session {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    Long mentorUserId;
    Long learnerUserId;

    @JsonFormat(pattern="yyyy-MM-dd")
    Date sessionDate;
    Time startTime;
    Time endTime;
    String classContentSummary;
    boolean learnerAcceptance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_record_id", referencedColumnName = "id")
    SessionRecord sessionRecord;

}
