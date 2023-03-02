package com.jayho.backend.api.service.dto;

import com.jayho.backend.db.entity.*;
import com.jayho.backend.db.repository.CommonQuestionRepository;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
public class CommonQuestionDto {

    private Long questionId;
    private String contents;
    private String writer;
    private String writerImg;
    private QuestionType questionType;

    public CommonQuestionDto(CommonQuestion commonQuestion) {
        questionId = commonQuestion.getId();
        contents = commonQuestion.getContents();
        writer = commonQuestion.getUser().getUserName();
        writerImg = commonQuestion.getUser().getUserImg();
        questionType = commonQuestion.getQuestionType();
    }

}
