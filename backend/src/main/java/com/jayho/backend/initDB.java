package com.jayho.backend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayho.backend.api.controller.AuthController;
import com.jayho.backend.api.controller.UserController;
import com.jayho.backend.api.request.UserLoginReq;
import com.jayho.backend.api.service.UserService;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.common.util.JwtTokenUtil;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.entity.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//@Profile("local")
@Component
@RequiredArgsConstructor
public class initDB {

    private final InitService initService;

    @PostConstruct
    // spring에서 component scan이 된 후에 스프링 빈이 다 엮기고 나서
    // 마지막에 PostConstruct가 init 함수를 호출해주게 된다.
    // 여기 안에다가 한번에 만들면 될 것같은데 왜 이렇게 사용하는가?
    // 스프링 라이프사이클 때문에 그렇게 동작하지 않는다.
    // 따라서 PostCon을 사용하는 것이다.
    public void init() throws InterruptedException, IOException {
//        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final UserService userService;

        public void dbInit1() throws InterruptedException, IOException {
            File file = new File("C:/Users/jayho/Desktop/token.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            for (int i = 1; i < 51; i++) {
                String stringI = Integer.toString(i);
                User user = userService.getByUserEmail("sox"+stringI+"@naver.com");
                String token = JwtTokenUtil.getToken(user.getUserEmail());
//                System.out.println(token);
                writer.write(stringI + "\n");
                writer.write(token + "\n");
                writer.write("\n");
                Thread.sleep(100);
            }
            writer.close();
        }
    }
}

