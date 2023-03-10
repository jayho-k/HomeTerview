package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.StudyJoin;

import java.util.List;
import java.util.Optional;

public interface StudyJoinRepositoryCustom {

    Optional<StudyJoin> findByStudyIdAndUserId(Long studyId, Long userId);

//    List<StudyJoin> findAllByUserIdJoinedByStudy(Long userId);

}
