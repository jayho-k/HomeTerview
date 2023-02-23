package com.jayho.backend.api.request;

import com.jayho.backend.common.util.ValidEnum;
import com.jayho.backend.db.entity.Status;
import com.jayho.backend.db.entity.StudyType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RecruitInfoReq {
    @NotEmpty
    private String recruitTitle;

    @NotEmpty
    private String stdName;

    @NotEmpty
    private String stdDetail;

    @ValidEnum(enumClass = StudyType.class)
    private StudyType stdType;

    private String comName;

    @NotEmpty
    private String startDate;

    @NotEmpty
    private String endDate;

    @NotEmpty
    private String stdDay;

    private int stdLimit;

    @ValidEnum(enumClass = StudyType.class)
    private Status recruitStatus;

    @NotEmpty
    private String stdImg;

}
