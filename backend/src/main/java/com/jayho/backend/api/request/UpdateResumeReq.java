package com.jayho.backend.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateResumeReq {

    private String detailQuestion;

    private String detailContents;

}
