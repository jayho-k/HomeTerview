package com.jayho.backend.api.service;

import com.jayho.backend.api.request.PersonalQuestionReq;
import com.jayho.backend.api.service.dto.PersonalQuestionDto;
import com.jayho.backend.db.entity.PersonalQuestion;
import com.jayho.backend.db.entity.ResumeDetail;
import com.jayho.backend.db.entity.Study;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonalQuestionServiceImpl implements PersonalQuestionService{

    private final PersonalQuestionRepository personalQuestionRepository;
    private final PersonalQuestionRepositoryCustom personalQuestionRepositoryCustom;
    private final StudyRepository studyRepository;
    private final ResumeDetailRepository resumeDetailRepository;

    @Override
    @Transactional
    public void registerQuestion(User user, Long studyId, Long resumeDetailId, PersonalQuestionReq personalQuestionReq) {
        Study study = studyRepository.findById(studyId).orElseThrow();
        ResumeDetail resumeDetail = resumeDetailRepository.findById(resumeDetailId).orElseThrow();
        PersonalQuestion res = PersonalQuestion.builder()
                .user(user)
                .resumeDetail(resumeDetail)
                .study(study)
                .itemContents(personalQuestionReq.getContents())
                .itemDelete(false)
                .build();
        personalQuestionRepository.save(res);
    }

    @Override
    public List<PersonalQuestionDto> getList(Long studyId, Long resumeDetailId) {
        List<PersonalQuestion> personalQuestionList = personalQuestionRepositoryCustom.findAllByStudyIdAndResumeDetailId(studyId, resumeDetailId);
        return personalQuestionList.stream()
                .map(p -> new PersonalQuestionDto(p))
                .collect(Collectors.toList());


    }
}
