package com.jayho.backend.api.service;

import com.jayho.backend.db.entity.JoinType;
import com.jayho.backend.db.entity.Recruit;
import com.jayho.backend.db.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface ApplyService {

    int applyRecruit(Long userId, Long recruitId, JoinType joinType);

}
