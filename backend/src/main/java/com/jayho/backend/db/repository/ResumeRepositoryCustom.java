package com.jayho.backend.db.repository;

import com.jayho.backend.api.service.dto.ResumeDto;
import com.jayho.backend.db.entity.Resume;

import java.util.List;
import java.util.Optional;

public interface ResumeRepositoryCustom {

    Optional<Resume> findByIdAndUserId(Long resumeId, Long userId);

    List<ResumeDto> findAllByUserId(Long userId);
}
