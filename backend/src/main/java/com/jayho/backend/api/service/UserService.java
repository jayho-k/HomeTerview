package com.jayho.backend.api.service;

import com.jayho.backend.api.request.UserRegisterReq;
import com.jayho.backend.db.entity.User;

public interface UserService {
    User registerUser(UserRegisterReq userRegisterReqInfo);
}
