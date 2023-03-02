package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.QStudyJoin;
import com.jayho.backend.db.entity.StudyJoin;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.jayho.backend.db.entity.QStudyJoin.*;

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

}
