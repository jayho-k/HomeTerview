package com.jayho.backend.api.response;

import com.jayho.backend.api.service.dto.ResumeDetailDto;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ResumeRes extends BaseResponseBody {

    private Long resumeId;
    private String resumeTitle;
    private String resumeWriterName;
    private String resumeWriterImg;
    private int resumeDetailsCnt;

    public static ResumeRes of (Resume resume, Integer statusCode, String message) {
        ResumeRes res = new ResumeRes();
        res.setResumeId(resume.getId());
        res.setResumeTitle(resume.getResumeTitle());
        res.setResumeWriterName(resume.getUser().getUserName());
        res.setResumeWriterImg(resume.getUser().getUserImg());
        res.setResumeDetailsCnt(
                resume.getResumeDetail().size()
        );
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
