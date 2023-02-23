package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.QApply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.jayho.backend.db.entity.QApply.*;


public class ApplyRepositoryImpl implements ApplyRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    public ApplyRepositoryImpl(EntityManager em){
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }



    @Override
    public long countByRecruitId(Long recruitId) {
        return queryFactory
                .selectFrom(apply)
                .where(apply.recruit.id.eq(recruitId))
                .fetchCount();
    }
}
