package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.QApply;
import com.jayho.backend.db.entity.Recruit;
import com.jayho.backend.db.entity.Status;
import com.jayho.backend.db.entity.StudyType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.jayho.backend.db.entity.QApply.*;
import static com.jayho.backend.db.entity.QRecruit.*;
import static org.springframework.data.support.PageableExecutionUtils.*;


public class RecruitRepositoryImpl implements RecruitRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    public RecruitRepositoryImpl(EntityManager em){
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Recruit> findAllByOrderByRecruitIdDesc(Pageable pageable) {
        List<Recruit> content = queryFactory
                .selectFrom(recruit)
                .orderBy(recruit.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Recruit> countQuery = queryFactory.selectFrom(recruit);
        return getPage(content, pageable, countQuery.fetch()::size);

    }
    @Override
    public Page<Recruit> findAllByStatusRecruitIdDesc(Status status, Pageable pageable) {
        List<Recruit> content = queryFactory
                .selectFrom(recruit)
                .where(recruitStatusEq(status))
                .orderBy(recruit.id.desc())
                .fetch();

        JPAQuery<Recruit> countQuery = queryFactory.selectFrom(recruit);
        return getPage(content, pageable, countQuery.fetch()::size);
    }
    @Override
    public Page<Recruit> findAllByStatusAndStudyTypeRecruitIdDesc(Status status, StudyType studyType, Pageable pageable) {
        // status와 std type에 index를 넣는 것은 어떤지 확인해보기
        List<Recruit> content = queryFactory
                .selectFrom(recruit)
                .where(
                        recruitStatusEq(status),
                        studyTypeEq(studyType))
                .orderBy(recruit.id.desc())
                .fetch();
        JPAQuery<Recruit> countQuery = queryFactory
                .selectFrom(recruit)
                .where(
                        recruitStatusEq(status),
                        studyTypeEq(studyType));
        return getPage(content, pageable, countQuery.fetch()::size);
    }
    @Override
    public List<Recruit> findApplyingRecruitAllByUserId(Long userId) {
        // user가 만든 모든 apply된 recruit를 찾을꺼야
        return new ArrayList<>();
//        return queryFactory
//                .selectFrom(recruit)
//                .join(recruit.applyList, apply).fetchJoin()
//                .where(apply.user.id.eq(userId))
//                .fetch();
    }
    private BooleanExpression recruitStatusEq(Status status) {
        return (status!=null) ? recruit.recruitStatus.eq(status) : null;
    }
    private BooleanExpression studyTypeEq(StudyType studyType) {
        return (studyType!=null) ? recruit.stdType.eq(studyType) : null;
    }


}
