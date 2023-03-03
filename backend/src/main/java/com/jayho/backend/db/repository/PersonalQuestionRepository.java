package com.jayho.backend.db.repository;

import com.jayho.backend.db.entity.PersonalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalQuestionRepository extends JpaRepository<PersonalQuestion, Long> {

    List<PersonalQuestion> findAllByStudyIdAndResumeDetailId(Long studyId, Long resumeDetailId);

}
