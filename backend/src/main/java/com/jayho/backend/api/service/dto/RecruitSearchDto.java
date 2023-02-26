package com.jayho.backend.api.service.dto;

import com.jayho.backend.db.entity.JoinType;
import com.jayho.backend.db.entity.Status;
import com.jayho.backend.db.entity.StudyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitSearchDto {
    private StudyType studyType;
    private Status status;
}
