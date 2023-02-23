package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    Optional<Apply> findApplyByUserIdAndRecruitId(Long userId, Long recruitId);
}
