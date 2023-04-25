package com.vip.coders.model;

import com.vip.coders.entity.User;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

        private long id;
        private String title;
        private String body;
        private User assignee;
        private String state;


}
