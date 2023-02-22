package com.jayho.backend.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserRegisterReq {
    @Email
    private String userEmail;
    @NotBlank
    private String userName;
    @NotBlank
    private String userPw;
    @NotNull
    private String userImg;
}
