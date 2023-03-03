package com.jayho.backend.api.service;

import com.jayho.backend.api.request.PersonalQuestionReq;
import com.jayho.backend.api.service.dto.PersonalQuestionDto;
import com.jayho.backend.db.entity.User;

import java.util.List;

public interface PersonalQuestionService {
    void registerQuestion(User user, Long studyId, Long resumeDetailId, PersonalQuestionReq personalQuestionReq);

    List<PersonalQuestionDto> getList(Long studyId, Long resumeDetailId);

}
