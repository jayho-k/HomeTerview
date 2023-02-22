package com.jayho.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Study extends RoomSession{
    @Id @GeneratedValue
    @Column(name="std_id")
    private Long id;
}
