package com.jayho.backend.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
public class UserRegisterReq {
    private String userEmail;
    private String userName;
    private String userPw;

    @NotNull
    private String userImg;
}
