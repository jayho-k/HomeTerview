package com.jayho.backend.api.service.dto;

import com.jayho.backend.db.entity.Apply;
import com.jayho.backend.db.entity.JoinType;
import com.jayho.backend.db.entity.Recruit;
import com.jayho.backend.db.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ApplyDto {
    private Long id;
    private JoinType joinType;
    private Long userId;
    private String username;
    private String userEmail;
    public ApplyDto(Apply apply){
        id = apply.getId();
        joinType = apply.getJoinType();
        userId = apply.getUser().getId();
        username = apply.getUser().getUserName();
        userEmail = apply.getUser().getUserEmail();
    }
}
