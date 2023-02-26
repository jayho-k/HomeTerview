package com.jayho.backend.api.service;

import com.jayho.backend.api.request.UserLoginReq;
import com.jayho.backend.api.request.UserRegisterReq;
import com.jayho.backend.api.request.UserUpdateReq;
import com.jayho.backend.db.entity.User;

public interface UserService {
    User registerUser(UserRegisterReq userRegisterReqInfo);
    User loginUser(UserLoginReq userLoginReq);
    User updateUser(Long id, UserUpdateReq userUpdateReq);
    User getByUserName(String username);
    User getByUserId(Long id);
    User getByUserEmail(String userEmail);
}