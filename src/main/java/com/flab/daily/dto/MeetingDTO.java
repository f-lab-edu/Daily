package com.flab.daily.dto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDTO {

    private Long category_id;

    private String meeting_name;

    private String meeting_description;

    private Timestamp meeting_date;

    private String meeting_place;

    private Integer current_people;

    private Integer meeting_people;

    private String meeting_image;

    private String created_by;

    private Timestamp created_date;

    private Timestamp updated_date;

}
