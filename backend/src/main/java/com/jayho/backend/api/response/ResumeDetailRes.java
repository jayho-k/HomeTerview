package com.jayho.backend.api.response;

import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.api.service.dto.ResumeDetailDto;
import com.jayho.backend.common.model.response.BaseResponseBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeDetailRes extends BaseResponseBody {
    private ResumeDetailDto resumeDetailDto;

    public static ResumeDetailRes of (ResumeDetailDto resumeDetailDto, Integer statusCode, String message){
        ResumeDetailRes res = new ResumeDetailRes();
        res.setResumeDetailDto(resumeDetailDto);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
