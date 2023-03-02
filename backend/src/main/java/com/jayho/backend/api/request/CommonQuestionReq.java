package com.jayho.backend.api.request;

import com.jayho.backend.common.util.ValidEnum;
import com.jayho.backend.db.entity.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CommonQuestionReq {
    @NotEmpty
    private String contents;

//    @ValidEnum(enumClass = QuestionType.class)
    private QuestionType questionType;
}
