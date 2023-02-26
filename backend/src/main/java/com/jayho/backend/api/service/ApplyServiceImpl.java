package com.jayho.backend.api.service;

import com.jayho.backend.api.service.dto.ApplyRecruitDto;
import com.jayho.backend.db.entity.Apply;
import com.jayho.backend.db.entity.JoinType;
import com.jayho.backend.db.entity.Recruit;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.repository.ApplyRepository;
import com.jayho.backend.db.repository.ApplyRepositoryCustom;
import com.jayho.backend.db.repository.RecruitRepository;
import com.jayho.backend.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyServiceImpl implements ApplyService{
    private final UserRepository userRepository;
    private final RecruitRepository recruitRepository;
    private final ApplyRepository applyRepository;
    private final ApplyRepositoryCustom applyRepositoryCustom;

    @Override
    @Transactional
    public ApplyRecruitDto applyRecruit(Long userId, Long recruitId, JoinType joinType) {
        // 몇명이 모집했는지에 대한 정보가 존재해야함
        // => recruit 번호가 같은 회원 수를 세야함
        // Dto => [res, apply] => apply를 뽑아주기 위함
        User user = userRepository.findById(userId).orElse(null);
        Recruit recruit = recruitRepository.findById(recruitId).orElse(null);
        Apply applyExist = applyRepository.findApplyByUserIdAndRecruitId(userId, recruitId).orElse(null);
        ApplyRecruitDto applyRecruitDto = new ApplyRecruitDto();

        if (applyExist==null) {
            long applyCnt = applyRepositoryCustom.countByRecruitId(recruitId);
            if (applyCnt >= recruit.getStdLimit()) {
                applyRecruitDto.setResult(1);
                applyRecruitDto.setApply(null);
                return applyRecruitDto;
            } else {
                Apply apply = Apply.builder()
                        .user(user)
                        .recruit(recruit)
                        .joinType(joinType)
                        .build();
                applyRepository.save(apply);
                applyRecruitDto.setResult(2);
                applyRecruitDto.setApply(apply);
                return applyRecruitDto;
            }
        }
        applyRecruitDto.setResult(2);
        applyRecruitDto.setApply(null);
        return applyRecruitDto;
    }
}
