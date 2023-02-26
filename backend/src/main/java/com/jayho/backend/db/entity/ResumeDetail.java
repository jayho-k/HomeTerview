package com.jayho.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDetail extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="resume_detail_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="resume_id")
    private Resume resume;
    private Long itemNo;
    private String detailQuestion;
    private String detailContents;

}
