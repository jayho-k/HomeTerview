package com.jayho.backend.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
@AllArgsConstructor
public class UserLoginReq {
    @Email
    private String userEmail;
    private String userPw;

}
