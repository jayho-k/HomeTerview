package com.jayho.backend.api.response;

import com.jayho.backend.api.service.dto.StudyCreateDto;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.Resume;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyRes extends BaseResponseBody {

    private StudyCreateDto studyCreateDto;

    public static StudyRes of (StudyCreateDto studyCreateDto, Integer statusCode, String message) {
        StudyRes res = new StudyRes();
        res.setStudyCreateDto(studyCreateDto);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
