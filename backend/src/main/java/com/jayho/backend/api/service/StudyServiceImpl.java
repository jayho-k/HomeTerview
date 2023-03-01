package com.jayho.backend.api.service;

import com.jayho.backend.api.service.dto.StudyCreateDto;
import com.jayho.backend.db.entity.*;
import com.jayho.backend.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final RecruitRepository recruitRepository;
    private final ApplyRepository applyRepository;
    private final StudyJoinRepository studyJoinRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public StudyCreateDto createStudy(Long recruitId, Long userId) {
        /*
        알고리즘적 성능 개선 => saveAll과 deleteALl을 사용하여 성능을 개선한다.
        */
        // study 만들기
        Recruit recruit = recruitRepository.findById(recruitId).orElse(null);
        List<Apply> applyList = recruit.getApplyList();

        // leader만 방을 만들수 있음
        Long leaderId = getLeaderId(applyList);
        if (userId != leaderId){return null;}


        Study studyBuilder = Study.builder()
                .stdName(recruit.getStdName())
                .stdDetail(recruit.getStdDetail())
                .stdImg(recruit.getStdImg())
                .stdType(recruit.getStdType())
                .comName(recruit.getComName())
                .startDate(recruit.getStartDate())
                .endDate(recruit.getEndDate())
                .stdDay(recruit.getStdDay())
                .stdLimit(recruit.getStdLimit())
                .build();
        Study study = studyRepository.save(studyBuilder);

        // recruit 완료로 수정
        this.recruitComplete(recruit);

        // apply을 이용하여 => study Join 생성
        List<StudyJoin> studyJoinList = getStudyJoinList(applyList, study);

        // save and delete
        studyJoinRepository.saveAll(studyJoinList);
        applyRepository.deleteAllInBatch(applyList);

        // dto변환
        StudyCreateDto studyCreateDto = new StudyCreateDto(study);
        return studyCreateDto;
    }

    @Override
    @Transactional
    public void recruitComplete(Recruit recruit) {
        recruit.recruitComplete();
    }

    @NotNull
    private List<StudyJoin> getStudyJoinList(List<Apply> applyList, Study study) {

        boolean overJoinLimit = isOverJoinLimit(applyList.size(), study.getStdLimit());
        List<StudyJoin> studyJoinList = new ArrayList<>();
        for (Apply apply : applyList) {
            StudyJoin studyJoin = StudyJoin.builder()
                    .user(apply.getUser())
                    .study(study)
                    .joinType(apply.getJoinType())
                    .joinStatus(overJoinLimit) // 적으면 true, 넘거나 같으면 false
                    .build();
            studyJoinList.add(studyJoin);
        }
        return studyJoinList;
    }
    private static Long getLeaderId(List<Apply> applyList) {
        Long leaderId = null;
        for (Apply apply : applyList) {
            if(apply.getJoinType()==JoinType.LEADER){
                leaderId = apply.getId();
                break;
            }
        }
        return leaderId;
    }
    private boolean isOverJoinLimit(int joinCnt, int total){
        return (joinCnt <total) ? true : false;
    }
}
