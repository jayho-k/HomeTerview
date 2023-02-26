package com.jayho.backend.api.service.dto;

import com.jayho.backend.api.response.RecruitRes;
import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.db.entity.Resume;
import com.jayho.backend.db.entity.ResumeDetail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ResumeDetailDto {

    private Long resumeDetailId;
    private Long itemNo;
    private String detailQuestion;
    private String detailContents;

    public ResumeDetailDto(ResumeDetail resumeDetail){
        resumeDetailId = resumeDetail.getId();
        itemNo = resumeDetail.getItemNo();
        detailQuestion = resumeDetail.getDetailQuestion();
        detailContents = resumeDetail.getDetailContents();
    }


}
