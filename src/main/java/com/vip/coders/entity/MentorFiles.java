package com.vip.coders.entity;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Data
@Getter
@Setter
@Builder
@Entity(name = "MENTOR_FILES")
public class MentorFiles {
    @GeneratedValue
    @Id
    Long id;

    String resumeFileType;
    String photoFileType;
    String resumeFileName;
    String photoFileName;

    @Lob
    byte[] resume;

    @Lob
    byte [] photo;

}
