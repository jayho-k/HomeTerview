package com.jayho.backend.api.service.dto;

import com.jayho.backend.db.entity.Resume;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
public class ResumeDto {

    private Long resumeId;
    private String resumeTitle;
    private String resumeWriterName;
    private String resumeWriterImg;

    public ResumeDto(Long resumeId, String resumeTitle, String resumeWriterName, String resumeWriterImg) {
        this.resumeId = resumeId;
        this.resumeTitle = resumeTitle;
        this.resumeWriterName = resumeWriterName;
        this.resumeWriterImg = resumeWriterImg;
    }
}

