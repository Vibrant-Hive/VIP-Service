package com.vip.coders.entity;


import lombok.*;

import javax.persistence.*;
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MENTOR_FILES")
public class MentorFiles {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
//    String resumeFileType;
    String photoFileType;
//    String resumeFileName;
    String photoFileName;

//    @Lob
//    byte[] resume;

    @Lob
    byte [] photo;

}
