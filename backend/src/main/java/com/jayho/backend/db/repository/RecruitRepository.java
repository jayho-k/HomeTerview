package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecruitRepository extends JpaRepository<Recruit, Long> {
}
