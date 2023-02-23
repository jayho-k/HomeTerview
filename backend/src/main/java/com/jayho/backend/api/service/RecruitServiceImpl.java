package com.jayho.backend.api.service;

import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.db.entity.Recruit;
import com.jayho.backend.db.entity.RoomSession;
import com.jayho.backend.db.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitServiceImpl implements RecruitService{

    private final RecruitRepository recruitRepository;

    @Override
    public Recruit writeRecruit(RecruitInfoReq recruitInfoReq) {
        Recruit recruit = Recruit.builder()
                .recruitTitle(recruitInfoReq.getRecruitTitle())
                .stdName(recruitInfoReq.getStdName())
                .stdDetail(recruitInfoReq.getStdDetail())
                .stdType(recruitInfoReq.getStdType())
                .comName(recruitInfoReq.getComName())
                .startDate(recruitInfoReq.getStartDate())
                .endDate(recruitInfoReq.getEndDate())
                .stdDay(recruitInfoReq.getStdDay())
                .stdLimit(recruitInfoReq.getStdLimit())
                .recruitStatus(recruitInfoReq.getRecruitStatus())
                .stdImg(recruitInfoReq.getStdImg())
                .build();
        return recruitRepository.save(recruit);
    }
}




