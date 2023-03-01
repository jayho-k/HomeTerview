package com.jayho.backend.db.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
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
