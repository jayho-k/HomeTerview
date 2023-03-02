package com.jayho.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Resume extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="resume_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private String resumeTitle;

    @OneToMany(mappedBy = "resume")
    @Builder.Default
    private List<ResumeDetail> resumeDetail = new ArrayList<>();

    @OneToOne(mappedBy = "resume")
    private StudyJoin studyJoin;
}
