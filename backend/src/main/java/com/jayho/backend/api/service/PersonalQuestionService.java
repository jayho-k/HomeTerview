package com.jayho.backend.api.service;

import com.jayho.backend.api.request.PersonalQuestionReq;

public interface PersonalQuestionService {
    void registerQuestion(Long userNo, Long stdNo, Long detailNo, PersonalQuestionReq personalQuestionReq);
}
