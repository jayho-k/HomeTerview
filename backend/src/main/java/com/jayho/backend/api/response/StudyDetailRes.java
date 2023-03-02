package com.jayho.backend.api.response;

import com.jayho.backend.api.service.dto.StudyCreateDto;
import com.jayho.backend.api.service.dto.StudyDetailGetDto;
import com.jayho.backend.common.model.response.BaseResponseBody;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudyDetailRes extends BaseResponseBody {
    private StudyDetailGetDto study;

    public static StudyDetailRes of (StudyDetailGetDto study, Integer statusCode, String message) {
        StudyDetailRes res = new StudyDetailRes();
        res.setStudy(study);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
