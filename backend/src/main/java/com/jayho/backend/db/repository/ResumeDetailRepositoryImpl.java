package com.jayho.backend.db.repository;

import com.jayho.backend.api.service.dto.ResumeDetailDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.jayho.backend.db.entity.QResumeDetail.*;


public class ResumeDetailRepositoryImpl implements ResumeDetailRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ResumeDetailRepositoryImpl(EntityManager em){
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public ResumeDetailDto findByResumeIdAndItemNo(Long resumeId, Long itemNo) {
        return queryFactory
                .select(Projections.constructor(
                        ResumeDetailDto.class,
                        resumeDetail.resume.id.as("resumeId") ,
                        resumeDetail.itemNo,
                        resumeDetail.detailQuestion,
                        resumeDetail.detailContents
                ))
                .from(resumeDetail)
                .where(resumeIdEq(resumeId),
                        itemNoEq(itemNo))
                .fetchOne();
    }

    private BooleanExpression resumeIdEq(Long resumeId) {
        return (resumeId != null) ? resumeDetail.resume.id.eq(resumeId) : null;
    }
    private BooleanExpression itemNoEq(Long itemNo) {
        return (itemNo != null) ? resumeDetail.itemNo.eq(itemNo) : null;
    }
}
