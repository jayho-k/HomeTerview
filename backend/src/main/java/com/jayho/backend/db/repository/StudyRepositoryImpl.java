package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.QCommonQuestion;
import com.jayho.backend.db.entity.QStudy;
import com.jayho.backend.db.entity.Study;
import com.querydsl.jpa.impl.JPAQueryFactory;
import net.bytebuddy.dynamic.DynamicType;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static com.jayho.backend.db.entity.QCommonQuestion.*;
import static com.jayho.backend.db.entity.QStudy.*;

//public class StudyRepositoryImpl implements StudyRepositoryCustom{
//
//    private final EntityManager em;
//    private final JPAQueryFactory queryFactory;
//
//    public StudyRepositoryImpl(EntityManager em){
//        this.em = em;
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//}
