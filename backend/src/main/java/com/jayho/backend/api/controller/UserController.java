package com.jayho.backend.api.controller;

import com.jayho.backend.api.request.UserRegisterReq;
import com.jayho.backend.api.service.UserService;
import com.jayho.backend.common.model.response.BaseResponseBody;

import com.jayho.backend.db.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService userService;
    // 형식 확인하기
    @PostMapping("/join")
    public ResponseEntity<? extends BaseResponseBody> registerUser(@RequestBody UserRegisterReq userInfo){
        userService.registerUser(userInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"sucess"));
    }



}
