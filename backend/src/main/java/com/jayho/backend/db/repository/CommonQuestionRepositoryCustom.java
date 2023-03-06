package com.jayho.backend.db.repository;

import com.jayho.backend.api.service.dto.CommonQuestionDto;
import com.jayho.backend.api.service.dto.CommonQuestionListDto;
import com.jayho.backend.db.entity.CommonQuestion;

import java.util.List;

public interface CommonQuestionRepositoryCustom {

    List<CommonQuestionListDto> findCommonAndUserByStudyId(Long studyId);
    List<CommonQuestion> findCommonUserByStudyIdFetch(Long studyId);

}
