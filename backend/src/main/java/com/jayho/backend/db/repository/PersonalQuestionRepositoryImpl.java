package com.jayho.backend.db.repository;

import com.jayho.backend.api.service.dto.PersonalQuestionDto;
import com.jayho.backend.api.service.dto.ResumeDetailDto;
import com.jayho.backend.db.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.jayho.backend.db.entity.QPersonalQuestion.*;
import static com.jayho.backend.db.entity.QResumeDetail.resumeDetail;
import static com.jayho.backend.db.entity.QStudy.*;
import static com.jayho.backend.db.entity.QUser.*;

public class PersonalQuestionRepositoryImpl implements PersonalQuestionRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    public PersonalQuestionRepositoryImpl(EntityManager em){
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<PersonalQuestion> findAllByStudyIdAndResumeDetailId(Long studyId, Long resumeDetailId) {
        return queryFactory
                .selectFrom(personalQuestion)
                .join(personalQuestion.user).fetchJoin()
                .where(personalQuestion.study.id.eq(studyId),
                        personalQuestion.resumeDetail.id.eq(resumeDetailId))
                .fetch();

    }
}
