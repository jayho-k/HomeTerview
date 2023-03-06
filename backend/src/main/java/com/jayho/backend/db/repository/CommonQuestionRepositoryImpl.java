package com.jayho.backend.db.repository;

import com.jayho.backend.api.service.dto.CommonQuestionDto;
import com.jayho.backend.api.service.dto.CommonQuestionListDto;
import com.jayho.backend.db.entity.CommonQuestion;
import com.jayho.backend.db.entity.QCommonQuestion;
import com.jayho.backend.db.entity.QUser;
import com.jayho.backend.db.entity.QuestionType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static com.jayho.backend.db.entity.QCommonQuestion.*;
import static com.jayho.backend.db.entity.QUser.*;


public class CommonQuestionRepositoryImpl implements CommonQuestionRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    public CommonQuestionRepositoryImpl(EntityManager em){
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<CommonQuestionListDto> findCommonAndUserByStudyId(Long studyId) {
         return queryFactory
                .select(Projections.constructor(
                        CommonQuestionListDto.class,
                                commonQuestion.id.as("questionId"),
                                commonQuestion.contents,
                                user.userName.as("writer"),
                                user.userImg.as("writerImg"),
                                commonQuestion.questionType
                        ))
                .from(commonQuestion)
                .join(commonQuestion.user,user)
                .fetch();
    }

    @Override
    public List<CommonQuestion> findCommonUserByStudyIdFetch(Long studyId) {
        return queryFactory
                .select(commonQuestion)
                .from(commonQuestion)
                .join(commonQuestion.user).fetchJoin()
                .fetch();

    }
}
