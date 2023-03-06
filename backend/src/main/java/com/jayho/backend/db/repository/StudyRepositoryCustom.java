package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.Study;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyRepositoryCustom {
    List<Study> findAllByUserIdJoinedByStudy(Long UserId);
}
