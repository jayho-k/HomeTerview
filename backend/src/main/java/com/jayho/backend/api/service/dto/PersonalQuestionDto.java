package com.jayho.backend.api.service.dto;

import com.jayho.backend.db.entity.PersonalQuestion;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonalQuestionDto {

    private Long personalQuestionId;
    private String userName;
    private String userImg;
    private String itemContents;

    public PersonalQuestionDto (PersonalQuestion personalQuestion) {

        personalQuestionId = personalQuestion.getId();
        userName = personalQuestion.getUser().getUserName();
        userImg = personalQuestion.getUser().getUserImg();
        itemContents = personalQuestion.getItemContents();
    }

}
