package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.Study;

import java.util.List;

public interface StudyRepositoryCustom {
    List<Study> findAllByUserIdJoinedByStudy(Long UserId);
}
