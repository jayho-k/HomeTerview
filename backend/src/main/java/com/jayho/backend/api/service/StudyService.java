package com.jayho.backend.api.service;

import com.jayho.backend.api.service.dto.StudyCreateDto;
import com.jayho.backend.api.service.dto.StudyDetailGetDto;
import com.jayho.backend.db.entity.Recruit;

import java.util.List;

public interface StudyService {
    StudyCreateDto createStudy(Long recruitId, Long userId);

    List<StudyCreateDto> getStudyList(Long userId);

    StudyDetailGetDto getStudyDetail(Long studyId, Long userId);

    void updateRegistedResume(Long studyId, Long userId, Long resumeId);

    void recruitComplete(Recruit recruit);
}
