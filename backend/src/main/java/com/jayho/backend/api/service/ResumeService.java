package com.jayho.backend.api.service;

import com.jayho.backend.api.request.ResumeDetailCreateReq;
import com.jayho.backend.api.service.dto.ResumeDetailDto;
import com.jayho.backend.api.service.dto.ResumeListDto;
import com.jayho.backend.db.entity.ResumeDetail;

import javax.validation.Valid;
import java.util.List;

public interface ResumeService {

    void createResume( Long userId, String resumeTitle);

    int deleteResume(Long resumeId, Long userId);

    List<ResumeListDto> getResumeList(Long userId);

    ResumeListDto updateResume(Long userId, Long resumeId, String resumeTitle);

    ResumeDetail createResumeDetail(ResumeDetailCreateReq resumeDetailCreateReq);

    ResumeDetailDto getResumeDetail(Long resumeId, Long itemNo);
}
