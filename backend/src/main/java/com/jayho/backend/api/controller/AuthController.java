package com.jayho.backend.api.controller;

import com.jayho.backend.api.request.UserLoginReq;
import com.jayho.backend.api.response.LoginUserRes;
import com.jayho.backend.api.service.UserService;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.common.util.JwtTokenUtil;
import com.jayho.backend.db.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class AuthController {


    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<? extends BaseResponseBody> login(@RequestBody @Valid UserLoginReq loginInfo){
        User user = userService.loginUser(loginInfo);
        if (user!=null){
            return ResponseEntity.ok(LoginUserRes.of(user, 200, "로그인에 성공하였습니다.", JwtTokenUtil.getToken(user.getUserEmail())));
        }
        else if (user==null){
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Not Registered"));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "비밀번호를 다시 확인해주세요."));
    }
}
