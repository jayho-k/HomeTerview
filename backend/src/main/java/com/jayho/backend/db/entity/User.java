package com.jayho.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayho.backend.api.request.UserRegisterReq;

import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    private String userName;

    private String userImg;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPw;

    private String userEmail;

    private boolean userDelete;

}