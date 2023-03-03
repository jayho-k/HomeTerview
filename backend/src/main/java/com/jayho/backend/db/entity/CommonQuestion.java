package com.jayho.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jayho.backend.common.util.ValidEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonQuestion extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "study_id")
    private Study study;
    private String contents;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;


    public void setStudy(Study study) {
        this.study = study;
        study.getCommonQuestionList().add(this);
    }
    public void updateCommonQuestion(QuestionType questionType, String contents) {
        this.questionType = questionType;
        this.contents = contents;
    }
}
