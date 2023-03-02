package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.Resume;

import java.util.Optional;

public interface ResumeRepositoryCustom {

    Optional<Resume> findByIdAndUserId(Long resumeId, Long userId);
}
