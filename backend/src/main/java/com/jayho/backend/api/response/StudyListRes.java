package com.jayho.backend.api.response;

import com.jayho.backend.api.service.dto.StudyCreateDto;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.Resume;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudyListRes extends BaseResponseBody {
    private List<StudyCreateDto> studyList;

    public static StudyListRes of (List<StudyCreateDto> studyList, Integer statusCode, String message) {
        StudyListRes res = new StudyListRes();
        res.setStudyList(studyList);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
