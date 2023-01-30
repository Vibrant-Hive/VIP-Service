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
@Entity(name = "SESSION_RECORDS")
public class SessionRecord {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String recordFileLink;
    String recordFileName;
    Date uploadedDate;
}
