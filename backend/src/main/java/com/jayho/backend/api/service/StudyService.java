package com.jayho.backend.api.service;

import com.jayho.backend.api.service.dto.StudyCreateDto;
import com.jayho.backend.db.entity.Recruit;

public interface StudyService {
    StudyCreateDto createStudy(Long recruitId, Long userId);

    void recruitComplete(Recruit recruit);
}
