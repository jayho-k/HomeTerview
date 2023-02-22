package com.jayho.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recruit extends RoomSession{

    @Id @GeneratedValue
    @Column(name="recruit_id")
    private Long id;

    private String recruitTitle;

    @Enumerated(EnumType.STRING)
    private Status recruitStatus;

}
