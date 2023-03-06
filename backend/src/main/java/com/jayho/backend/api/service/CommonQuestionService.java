package com.jayho.backend.api.service;

import com.jayho.backend.api.request.CommonQuestionReq;
import com.jayho.backend.api.service.dto.CommonQuestionDto;
import com.jayho.backend.api.service.dto.CommonQuestionListDto;
import com.jayho.backend.db.entity.CommonQuestion;

import java.util.List;

public interface CommonQuestionService {
    void registerCommonQuestion(Long userId, Long studyId, CommonQuestionReq commonQuestionReq);
    List<CommonQuestionListDto> getList(Long studyId);
    List<CommonQuestionDto> getListFetch(Long studyId);

}
