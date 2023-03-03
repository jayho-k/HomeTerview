package com.jayho.backend.api.service;

import com.jayho.backend.api.service.dto.ApplyRecruitDto;
import com.jayho.backend.db.entity.JoinType;



public interface ApplyService {

    ApplyRecruitDto applyRecruit(Long userId, Long recruitId, JoinType joinType);

}
