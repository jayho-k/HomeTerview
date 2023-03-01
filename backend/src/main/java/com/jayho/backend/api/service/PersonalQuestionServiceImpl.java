package com.jayho.backend.api.service;

import com.jayho.backend.api.request.PersonalQuestionReq;
import org.springframework.stereotype.Service;

@Service
public class PersonalQuestionServiceImpl implements PersonalQuestionService{

    @Override
    public void registerQuestion(Long userNo, Long stdNo, Long detailNo, PersonalQuestionReq personalQuestionReq) {
    }
}
