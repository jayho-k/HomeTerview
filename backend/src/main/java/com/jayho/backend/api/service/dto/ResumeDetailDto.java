package com.jayho.backend.api.service.dto;

import com.jayho.backend.api.response.RecruitRes;
import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.db.entity.Resume;
import com.jayho.backend.db.entity.ResumeDetail;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDetailDto {

    private Long itemNo;
    private String detailQuestion;
    private String detailContents;


    public ResumeDetailDto(ResumeDetail resumeDetail){
//        resumeId = resumeDetail.getResume().getId();
        itemNo = resumeDetail.getItemNo();
        detailQuestion = resumeDetail.getDetailQuestion();
        detailContents = resumeDetail.getDetailContents();
    }


}
