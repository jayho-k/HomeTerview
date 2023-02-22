package com.jayho.backend.api.controller;

import com.jayho.backend.api.service.ApplyService;
import com.jayho.backend.common.model.response.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apply")
public class ApplyController {

    private final ApplyService applyService;

    public ResponseEntity<? extends BaseResponseBody> applyRecruit(Authentication authentication, ){

    }








}
