package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.QStudy;
import com.jayho.backend.db.entity.QStudyJoin;
import com.jayho.backend.db.entity.QUser;
import com.jayho.backend.db.entity.StudyJoin;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.jayho.backend.db.entity.QStudy.*;
import static com.jayho.backend.db.entity.QStudyJoin.*;
import static com.jayho.backend.db.entity.QUser.*;

public class StudyJoinRepositoryImpl implements StudyJoinRepositoryCustom {


    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public StudyJoinRepositoryImpl(EntityManager em){
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Optional<StudyJoin> findByStudyIdAndUserId(Long studyId, Long userId) {
        StudyJoin result = queryFactory
                .selectFrom(studyJoin)
                .where(studyJoin.study.id.eq(studyId), studyJoin.user.id.eq(userId))
                .fetchOne();
        return Optional.ofNullable(result);
    }

//    @Override
//    public List<StudyJoin> findAllByUserIdJoinedByStudy(Long userId) {
//        return queryFactory
//                .selectFrom(studyJoin)
//                .where(studyJoin.user.id.eq(userId))
//                .join(studyJoin.study, study).fetchJoin()
//                .join(studyJoin.user, user).fetchJoin()
//                .fetch();
//    }
}
