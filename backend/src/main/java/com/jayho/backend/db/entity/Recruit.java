package com.jayho.backend.db.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
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