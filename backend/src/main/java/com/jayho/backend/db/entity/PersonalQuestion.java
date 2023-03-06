package com.jayho.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalQuestion extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="resume_detail_id")
    private ResumeDetail resumeDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

    private String itemContents;

    private boolean itemDelete;

    public void setResumeDetail(ResumeDetail resumeDetail) {
        this.resumeDetail = resumeDetail;
        resumeDetail.getPersonalQuestionList().add(this);
    }


}
