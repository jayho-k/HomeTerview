package com.jayho.backend.api.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayho.backend.db.entity.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class StudyJoinCreateDto {
    private Long studyJoinId;
    private Long userId;
    private String userName;
    private String userImg;
    private String userEmail;
    private JoinType joinType;
    public StudyJoinCreateDto(StudyJoin studyJoin){
        studyJoinId = studyJoin.getId();
        userId = studyJoin.getUser().getId();
        userName = studyJoin.getUser().getUserName();
        userImg = studyJoin.getUser().getUserImg();
        userEmail = studyJoin.getUser().getUserEmail();
        joinType = studyJoin.getJoinType();
    }
}
