package com.jayho.backend.api.service.dto;

import com.jayho.backend.api.response.ResumeRes;
import com.jayho.backend.db.entity.Resume;
import com.jayho.backend.db.entity.ResumeDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
public class ResumeListDto {

    private Long resumeId;
    private String resumeTitle;
    private String resumeWriterName;
    private String resumeWriterImg;
    private int resumeDetailsCnt;
    public ResumeListDto (Resume resume) {
        resumeId = resume.getId();
        resumeTitle = resume.getResumeTitle();
        resumeWriterName = resume.getUser().getUserName();
        resumeWriterImg = resume.getUser().getUserImg();
        resumeDetailsCnt = Math.toIntExact(resume.getResumeDetail().stream()
                .map(r -> new ResumeDetailDto(r))
                .collect(Collectors.counting()));
    }

}
