package com.jayho.backend.db.entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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
    private String startDate;
    private String endDate;

}
