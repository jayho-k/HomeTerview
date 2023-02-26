package com.jayho.backend.api.service;

import com.jayho.backend.api.request.UserLoginReq;
import com.jayho.backend.api.request.UserRegisterReq;
import com.jayho.backend.api.request.UserUpdateReq;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.entity.UserType;
import com.jayho.backend.db.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerUser(UserRegisterReq userRegisterReqInfo){

        User user = User.builder()
                .userPw(passwordEncoder.encode(userRegisterReqInfo.getUserPw()))
                .userName(userRegisterReqInfo.getUserName())
                .userEmail(userRegisterReqInfo.getUserEmail())
                .userType(UserType.USER)
                .userImg(userRegisterReqInfo.getUserImg())
                .userDelete(false)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User loginUser(UserLoginReq userLoginReq) {
        String userEmail = userLoginReq.getUserEmail();
        String userPw = userLoginReq.getUserPw();
        User user = getByUserEmail(userEmail);
        System.out.println("user : " + user);
        if (user==null){
            return null;
        }else if (passwordEncoder.matches(userPw, user.getUserPw())){
            return user;
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public User updateUser(Long id, UserUpdateReq userUpdateReq) {
        // 이부분 엔티티에 따로 Update를 만들어줘야할 것 같음
        // 이유 : set하면서 바뀌지 말아야 할 것들도 바뀔 수 있을 것이기 때문
        User user = getByUserId(id);
        user.setUserName(userUpdateReq.getUserName());
        user.setUserImg((userUpdateReq.getUserImg()));
        return user;
    }

    @Override
    public User getByUserName(String username) {
        return userRepository.findByUserName(username).orElse(null);
    }
    @Override
    public User getByUserId(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public User getByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail).orElse(null);
    }
}
