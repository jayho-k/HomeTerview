package com.jayho.backend.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeUpdateReq {
    private Long resumeId;
    private String resumeTitle;

}
