package com.jayho.backend.db.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
@NoArgsConstructor
public abstract class RoomSession extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="std_id")
    private Long id;
    private String stdName;
    private String stdDetail;
    private String stdImg;
    @Enumerated(EnumType.STRING)
    private StudyType stdType;
    private String comName;
    private String stdDay;
    private int stdLimit;
    @Lob
    private String stdNotation;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
