package com.jayho.backend.api.service;

import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.db.entity.JoinType;
import com.jayho.backend.db.entity.Recruit;
import com.jayho.backend.db.entity.Status;
import com.jayho.backend.db.entity.StudyType;
import com.jayho.backend.db.repository.RecruitRepository;
import com.jayho.backend.db.repository.RecruitRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitServiceImpl implements RecruitService{

    private final RecruitRepository recruitRepository;
    private final RecruitRepositoryCustom recruitRepositoryCustom;

    @Override
    @Transactional
    public Recruit writeRecruit(RecruitInfoReq recruitInfoReq, Long userId) {
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
    public Page<RecruitDto> getList(int type, Pageable pageable) {
        if(type==1){
            return recruitRepositoryCustom.findAllByStatusAndStudyTypeRecruitIdDesc(null,null,pageable);
        } else if(type==2){
            return recruitRepositoryCustom.findAllByStatusAndStudyTypeRecruitIdDesc(null,StudyType.COM,pageable);
        } else {
            return recruitRepositoryCustom.findAllByStatusAndStudyTypeRecruitIdDesc(null,StudyType.COM,pageable);
        }
    }
    @Override
    public Page<RecruitDto> getRecruitingList(int type, Pageable pageable) {
        if (type==1){
            return recruitRepositoryCustom.findAllByStatusAndStudyTypeRecruitIdDesc(Status.ING, null, pageable);
        }else if(type==2){
            return recruitRepositoryCustom.findAllByStatusAndStudyTypeRecruitIdDesc(Status.ING, StudyType.COM, pageable);
        }else{
            return recruitRepositoryCustom.findAllByStatusAndStudyTypeRecruitIdDesc(Status.ING, StudyType.FREE, pageable);
        }
    }

    @Override
    public RecruitDto getRecruitDetail(Long recruitId) {
        Recruit recruit = recruitRepository.findById(recruitId).orElse(null);
        RecruitDto recruitDto = new RecruitDto(recruit);
        return recruitDto;
    }

    @Override
    @Transactional
    public RecruitDto updateRecruitDetail(Long recruitId, RecruitInfoReq recruitInfoReq) {
        Recruit recruit = recruitRepository.findById(recruitId).orElse(null);
        if (recruit == null) {
            return null;
        }
        recruit.setRecruitTitle(recruitInfoReq.getRecruitTitle());
        recruit.setStdName(recruitInfoReq.getStdName());
        recruit.setStdDetail(recruitInfoReq.getStdDetail());
        recruit.setStdType(recruitInfoReq.getStdType());
        recruit.setStartDate(recruitInfoReq.getStartDate());
        recruit.setEndDate(recruitInfoReq.getEndDate());
        recruit.setStdDay(recruitInfoReq.getStdDay());
        recruit.setStdImg(recruitInfoReq.getStdImg());
        recruit.setStdLimit(recruitInfoReq.getStdLimit());
        RecruitDto recruitDto = new RecruitDto(recruit);
        return recruitDto;
    }

    @Override
    @Transactional
    public int deleteRecruit(Long recruitId) {
        Recruit recruit = recruitRepository.findById(recruitId).orElse(null);
        if (recruit!=null){
            recruitRepository.delete(recruit);
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public List<Recruit> getApplyingList(Long userId) {
        return recruitRepositoryCustom.findApplyingRecruitAllByUserId(userId);
    }
}




