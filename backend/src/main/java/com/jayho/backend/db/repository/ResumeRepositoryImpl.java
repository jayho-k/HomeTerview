package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.QResume;
import com.jayho.backend.db.entity.Resume;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.jayho.backend.db.entity.QResume.*;

public class ResumeRepositoryImpl implements ResumeRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ResumeRepositoryImpl(EntityManager em){
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Optional<Resume> findByIdAndUserId(Long resumeId, Long userId) {
        Resume result = queryFactory
                .selectFrom(resume)
                .where(resume.id.eq(resumeId),
                        resume.user.id.eq(userId))
                .fetchOne();
        return Optional.ofNullable(result);
    }
}
