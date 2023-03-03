package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.Apply;

import java.util.List;

public interface ApplyRepositoryCustom {

    long countByRecruitId(Long recruitId);

    List<Apply> findAllByRecruitId(Long recruitId);
}
