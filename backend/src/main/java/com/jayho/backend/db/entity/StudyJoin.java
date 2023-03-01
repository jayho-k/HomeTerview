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
public class StudyJoin {
    @Id @GeneratedValue
    @Column(name="study_join_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

    @Enumerated(EnumType.STRING)
    private JoinType joinType;

    private boolean joinStatus;

    public void setStudy(Study study){
        this.study = study;
        study.getStudyJoinList().add(this);
    }
}
