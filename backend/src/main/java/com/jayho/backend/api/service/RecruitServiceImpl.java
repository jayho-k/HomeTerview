package com.jayho.backend.api.service;

import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.db.entity.Recruit;
import com.jayho.backend.db.entity.Status;
import com.jayho.backend.db.entity.StudyType;
import com.jayho.backend.db.repository.RecruitRepository;
import com.jayho.backend.db.repository.RecruitRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitServiceImpl implements RecruitService{

    private final RecruitRepository recruitRepository;
    private final RecruitRepositoryCustom recruitRepositoryCustom;

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

    @Override
    public Page<Recruit> getList(Pageable pageable) {
        return recruitRepositoryCustom.findAllByOrderByRecruitIdDesc(pageable);
    }

    @Override
    public Page<Recruit> getRecruitingList(int type, Pageable pageable) {
        if (type==1){
            return recruitRepositoryCustom.findAllByStatusRecruitIdDesc(Status.ING, pageable);
        }else if(type==2){
            return recruitRepositoryCustom.findAllByStatusAndStudyTypeRecruitIdDesc(Status.ING, StudyType.COM, pageable);
        }else{
            return recruitRepositoryCustom.findAllByStatusAndStudyTypeRecruitIdDesc(Status.ING, StudyType.FREE, pageable);
        }
    }
    @Override
    public List<Recruit> getApplyingList(Long userId) {
        return recruitRepositoryCustom.findApplyingRecruitAllByUserId(userId);
    }
}




