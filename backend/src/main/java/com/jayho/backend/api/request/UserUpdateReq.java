package com.jayho.backend.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserUpdateReq {
    @NotBlank
    private String userName;
    @NotNull
    private String userImg;
}