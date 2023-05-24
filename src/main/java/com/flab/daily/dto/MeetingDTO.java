package com.flab.daily.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class MeetingDTO {

    private Long id;
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

    public MeetingDTO(Long id, String meeting_name, String meeting_description, Timestamp meeting_date, String meeting_place, Integer current_people, Integer meeting_people, String meeting_image, String created_by, Timestamp created_date, Timestamp updated_date) {
        this.id = id;
        this.meeting_name = meeting_name;
        this.meeting_description = meeting_description;
        this.meeting_date = meeting_date;
        this.meeting_place = meeting_place;
        this.current_people = current_people;
        this.meeting_people = meeting_people;
        this.meeting_image = meeting_image;
        this.created_by = created_by;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }




}
