package com.jayho.backend.api.service;

import com.jayho.backend.api.request.UserRegisterReq;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User registerUser(UserRegisterReq userRegisterReqInfo){
        User user = User.of(userRegisterReqInfo);
        return userRepository.save(user);
    }

    @Override
    public User getByUserId(String username) {
        return userRepository.findByUserName(username);
    }
}
