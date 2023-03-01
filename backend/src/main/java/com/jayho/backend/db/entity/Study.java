package com.jayho.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Study extends RoomSession{
    @Id @GeneratedValue
    @Column(name="std_id")
    private Long id;

    @OneToMany(mappedBy = "study")
    @JsonIgnore
    @Builder.Default
    private List<StudyJoin> studyJoinList = new ArrayList<>();


    public void addStudyJoin(StudyJoin studyJoin){
        studyJoinList.add(studyJoin);
        studyJoin.setStudy(this);
    }


}
