package com.jayho.backend.api.service.dto;

import com.jayho.backend.db.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RecruitDto {

    private Long recruitId;
    private String recruitTitle;
    private Status recruitStatus;
    private String stdName;
    private String stdDetail;
    private String stdImg;
    private StudyType stdType;
    private String comName;
    private String stdDay;
    private int stdLimit;
    private String stdNotation;
    private String startDate;
    private String endDate;
    private List<ApplyDto> applyList;


    public RecruitDto(Recruit recruit) {
        recruitId = recruit.getId();
        recruitTitle = recruit.getRecruitTitle();
        recruitStatus = recruit.getRecruitStatus();
        stdName = recruit.getStdName();
        stdDetail = recruit.getStdDetail();
        stdImg = recruit.getStdImg();
        stdType = recruit.getStdType();
        comName = recruit.getComName();
        stdDay = recruit.getStdDay();
        stdLimit = recruit.getStdLimit();
        stdNotation = recruit.getStdNotation();
        startDate = recruit.getStartDate();
        endDate = recruit.getEndDate();
        applyList = recruit.getApplyList().stream()
                .map(a -> new ApplyDto(a))
                .collect(Collectors.toList());
    }


}
