package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.QStudy;
import com.jayho.backend.db.entity.QStudyJoin;
import com.jayho.backend.db.entity.QUser;
import com.jayho.backend.db.entity.Study;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.jayho.backend.db.entity.QStudy.*;
import static com.jayho.backend.db.entity.QStudyJoin.*;
import static com.jayho.backend.db.entity.QUser.*;

public class StudyRepositoryImpl implements StudyRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public StudyRepositoryImpl(EntityManager em){
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Study> findAllByUserIdJoinedByStudy(Long userId) {
        return queryFactory
                .select(study)
                .from(studyJoin)
                .where(studyJoin.user.id.eq(userId))
//                .join(study.studyJoinList, studyJoin).fetchJoin()
                .fetch();
    }
}
