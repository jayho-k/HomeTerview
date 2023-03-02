package com.jayho.backend.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class ResumeDetailCreateReq {

    @NotNull
    private Long resumeId;

    @NotNull
    private Long itemNo;

    @NotNull
    private String detailQuestion;

    @Lob
    private String detailContents;


}