package com.jayho.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig {

    @Bean // BCryptPasswordEncoder을 bean으로 등록
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
