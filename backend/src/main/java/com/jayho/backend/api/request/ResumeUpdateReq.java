package com.jayho.backend.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ResumeUpdateReq {
    private Long resumeId;
    private String resumeTitle;


}
