package com.jayho.backend.db.repository;

import com.jayho.backend.api.service.dto.PersonalQuestionDto;
import com.jayho.backend.db.entity.PersonalQuestion;

import java.util.List;

public interface PersonalQuestionRepositoryCustom {

    List<PersonalQuestion> findAllByStudyIdAndResumeDetailId(Long studyId, Long resumeDetailId);


}
