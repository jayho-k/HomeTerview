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
public class Recruit extends RoomSession {

    @Id
    @GeneratedValue
    @Column(name = "recruit_id")
    private Long id;

    private String recruitTitle;

    @Enumerated(EnumType.STRING)
    private Status recruitStatus;

    @OneToMany(mappedBy = "recruit")
    @JsonIgnore
    @Builder.Default
    private List<Apply> applyList = new ArrayList<>();

    public void recruitComplete() {
        this.recruitStatus = Status.COMPLETE;
    }
    public void addApplyList(Apply apply){
        applyList.add(apply);
        apply.setRecruit(this);
    }

}
