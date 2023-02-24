package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.Recruit;
import com.jayho.backend.db.entity.Status;
import com.jayho.backend.db.entity.StudyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecruitRepositoryCustom {

    Page<Recruit> findAllByOrderByRecruitIdDesc(Pageable pageable);
    Page<Recruit> findAllByStatusRecruitIdDesc(Status status, Pageable pageable);
    Page<Recruit> findAllByStatusAndStudyTypeRecruitIdDesc(Status status, StudyType studyType ,Pageable pageable);
    List<Recruit> findApplyingRecruitAllByUserId(Long userId);

}
