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
@Table(indexes = {
        @Index(name = "itemNo", columnList = "itemNo")
}
)
public class ResumeDetail extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="resume_detail_id", unique = true)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="resume_id")
    private Resume resume;
    private Long itemNo;
    private String detailQuestion;
    private String detailContents;

    @OneToMany(mappedBy = "resumeDetail")
    @JsonIgnore
    @Builder.Default
    private List<PersonalQuestion> personalQuestionList = new ArrayList<>();


    // association method
    public void addPersonalQuestion(PersonalQuestion personalQuestion) {
        personalQuestionList.add(personalQuestion);
        personalQuestion.setResumeDetail(this);
    }
}
