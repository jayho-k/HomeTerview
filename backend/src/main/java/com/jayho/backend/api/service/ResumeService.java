package com.jayho.backend.api.service;

import com.jayho.backend.api.service.dto.ResumeListDto;

import java.util.List;

public interface ResumeService {

    void createResume( Long userId, String resumeTitle);

    List<ResumeListDto> getResumeList(Long userId);

    ResumeListDto updateResume(Long userId, Long resumeId, String resumeTitle);

}
