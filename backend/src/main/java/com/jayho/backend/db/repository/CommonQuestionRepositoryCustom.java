package com.jayho.backend.db.repository;

import com.jayho.backend.api.service.dto.CommonQuestionDto;
import com.jayho.backend.api.service.dto.CommonQuestionListDto;

import java.util.List;

public interface CommonQuestionRepositoryCustom {

    List<CommonQuestionListDto> findCommonAndUserByStudyId(Long studyId);

}
