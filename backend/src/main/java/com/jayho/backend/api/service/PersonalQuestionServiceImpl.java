package com.jayho.backend.api.service;

import com.jayho.backend.api.request.PersonalQuestionReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonalQuestionServiceImpl implements PersonalQuestionService{

    @Override
    public void registerQuestion(Long userNo, Long stdNo, Long detailNo, PersonalQuestionReq personalQuestionReq) {
    }
}
