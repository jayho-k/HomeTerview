package com.jayho.backend.api.service.dto;

import com.jayho.backend.db.entity.Study;
import com.jayho.backend.db.entity.StudyJoin;
import com.jayho.backend.db.entity.StudyType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class StudyCreateDto {

    private Long studyId;
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
    private List<StudyJoinCreateDto> studyJoinDtoList;

    public StudyCreateDto(Study study){
        studyId = study.getId();
        stdName = study.getStdName();
        stdDetail = study.getStdDetail();
        stdImg = study.getStdImg();
        stdType = study.getStdType();
        comName = study.getComName();
        stdDay = study.getStdDay();
        stdLimit = study.getStdLimit();
        stdNotation = study.getStdNotation();
        startDate = study.getStartDate();
        endDate = study.getEndDate();
        studyJoinDtoList = study.getStudyJoinList().stream()
                .map(s -> new StudyJoinCreateDto(s))
                .collect(Collectors.toList());
    }




}
