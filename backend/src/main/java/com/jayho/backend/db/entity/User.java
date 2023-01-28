package com.jayho.backend.db.entity;

import com.jayho.backend.api.request.UserRegisterReq;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    private String userName;

    private String userImg;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String userPw;

    private String userEmail;

    private boolean userDelete;

    public static User of(UserRegisterReq userRegisterReqInfo) {
        PasswordEncoder passwordEncoder = null;
        return User.builder()
                .userEmail(userRegisterReqInfo.getUserEmail())
                .userPw(passwordEncoder.encode(userRegisterReqInfo.getUserPw()))
                .userName(userRegisterReqInfo.getUserName())
                .userDelete(false)
                .userImg(userRegisterReqInfo.getUserImg())
                .userType(UserType.USER)
                .build();

    };
}