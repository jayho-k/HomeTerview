package com.jayho.backend.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PersonalQuestionReq {
    @Lob
    @NotEmpty
    private String contents;
}
