package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.Resume;
import com.jayho.backend.db.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
