package com.jayho.backend.api.response;

import com.jayho.backend.api.service.dto.ResumeDto;
import com.jayho.backend.api.service.dto.ResumeListDto;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.Resume;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ResumeListRes extends BaseResponseBody {

    List<ResumeDto> resumeListDtoList;
    public static ResumeListRes of (List<ResumeDto> resumeListDtoList, Integer statusCode, String message) {
        ResumeListRes res = new ResumeListRes();
        res.setResumeListDtoList(resumeListDtoList);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
