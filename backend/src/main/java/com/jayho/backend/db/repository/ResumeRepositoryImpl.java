package com.jayho.backend.db.repository;

import com.jayho.backend.api.service.dto.CommonQuestionListDto;
import com.jayho.backend.api.service.dto.ResumeDto;
import com.jayho.backend.db.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.jayho.backend.db.entity.QResume.*;
import static com.jayho.backend.db.entity.QResumeDetail.*;
import static com.jayho.backend.db.entity.QUser.*;

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

    @Override
    public List<ResumeDto> findAllByUserId(Long userId) {
         return queryFactory
                .select(Projections.constructor(
                        ResumeDto.class,
                        resume.id.as("resumeId"),
                        resume.resumeTitle,
                        resume.user.userName.as("resumeWriterName"),
                        resume.user.userImg.as("resumeWriterImg")
                ))
                .from(resume)
                .where(resume.user.id.eq(userId))
                .join(resume.user, user)
                .fetch();
    }
}
