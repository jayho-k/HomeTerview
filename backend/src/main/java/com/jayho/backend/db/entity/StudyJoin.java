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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    private boolean joinStatus;


    // mapping
    public void setResume(Resume resume){
        this.resume = resume;
    }

    public void setStudy(Study study){
        this.study = study;
        study.getStudyJoinList().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getStudyJoinList().add(this);
    }
}
