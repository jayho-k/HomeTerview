package com.jayho.backend.api.controller;

import com.jayho.backend.api.request.UserRegisterReq;
import com.jayho.backend.api.request.UserUpdateReq;
import com.jayho.backend.api.service.UserService;
import com.jayho.backend.common.auth.UserDetails;
import com.jayho.backend.common.model.response.BaseResponseBody;

import com.jayho.backend.db.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;
    // 형식 확인하기
    @PostMapping("/join")
    public ResponseEntity<? extends BaseResponseBody> registerUser(@RequestBody @Valid UserRegisterReq userInfo){
        userService.registerUser(userInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공하였습니다."));
    }
    @PutMapping("/update")
    public ResponseEntity<? extends BaseResponseBody> updateUser(Authentication authentication, @RequestBody @Valid UserUpdateReq userInfo) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        userService.updateUser(userDetails.getUserNo(), userInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공하였습니다."));
    }

}
