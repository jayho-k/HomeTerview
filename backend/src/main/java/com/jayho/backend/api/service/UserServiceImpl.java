package com.jayho.backend.api.service;

import com.jayho.backend.api.request.UserLoginReq;
import com.jayho.backend.api.request.UserRegisterReq;
import com.jayho.backend.api.request.UserUpdateReq;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.entity.UserType;
import com.jayho.backend.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.Proxy;

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

        if (user==null){
            return null;
        }else if(user.getUserPw().equals(userPw)){
            return user;
        }
//        else if (passwordEncoder.matches(userPw, user.getUserPw())){
//            return user;
//        }
        else{
            return null;
        }
    }

    @Override
    @Transactional
    public User updateUser(Long id, UserUpdateReq userUpdateReq) {
        // ????????? ???????????? ?????? Update??? ?????????????????? ??? ??????
        // ?????? : set????????? ????????? ????????? ??? ????????? ?????? ??? ?????? ????????? ??????
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
