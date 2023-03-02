package com.jayho.backend.db.repository;

import com.jayho.backend.api.service.dto.ResumeDetailDto;

public interface ResumeDetailRepositoryCustom {
    ResumeDetailDto findByResumeIdAndItemNo(Long resumeId, Long itemNo);

}
