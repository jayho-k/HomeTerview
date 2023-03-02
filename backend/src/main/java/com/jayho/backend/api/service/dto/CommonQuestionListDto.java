package com.jayho.backend.api.service.dto;

import com.jayho.backend.db.entity.CommonQuestion;
import com.jayho.backend.db.entity.QuestionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonQuestionListDto {

    private Long questionId;
    private String contents;
    private String writer;
    private String writerImg;
    private QuestionType questionType;

    public CommonQuestionListDto(Long questionId, String contents, String writer, String writerImg, QuestionType questionType) {
        this.questionId = questionId;
        this.contents = contents;
        this.writer = writer;
        this.writerImg = writerImg;
        this.questionType = questionType;
    }

}
