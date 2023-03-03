package com.jayho.backend.db.repository;

import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.db.entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<RecruitDto> findAllByStatusAndStudyTypeRecruitIdDesc(Status status, StudyType studyType, Pageable pageable) {
        // status와 std type에 index를 넣는 것은 어떤지 확인해보기
        List<Recruit> recruitingList = queryFactory
                .selectFrom(recruit)
                .where(
                        recruitStatusEq(status),
                        studyTypeEq(studyType))
                .orderBy(recruit.id.desc())
                .fetch();
        // DTO변환후 get에 삽입
        List<RecruitDto> content = recruitingList.stream().map(r -> new RecruitDto(r)).collect(Collectors.toList());

        // count쿼리
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
        // fetch join을 썼을때 쿼리 2개 => user하나 나머지 조인 하나
        // 하지만 join을 썼을 때 또한 user하나 recruit하나가 나가게 된다.
        // 밖에서 get으로 또다른 값을 가져오는 것이 아닌 where문에서 사용하는 것이기 때문에
        // fetch join을 사용하지 않아도 된다.
        return queryFactory
                .selectFrom(recruit)
                .join(recruit.applyList, apply)
                .where(apply.user.id.eq(userId))
                .fetch();
    }
    private BooleanExpression recruitStatusEq(Status status) {
        return (status!=null) ? recruit.recruitStatus.eq(status) : null;
    }
    private BooleanExpression studyTypeEq(StudyType studyType) {
        return (studyType!=null) ? recruit.stdType.eq(studyType) : null;
    }

}
