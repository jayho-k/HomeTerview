package com.jayho.backend.db.repository;


import com.jayho.backend.db.entity.CommonQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonQuestionRepository extends JpaRepository<CommonQuestion, Long> {

    List<CommonQuestion> findAllByStudyId(Long StudyId);

}
