package com.jayho.backend.api.service;

import com.jayho.backend.api.request.CommonQuestionReq;
import com.jayho.backend.api.service.dto.CommonQuestionDto;
import com.jayho.backend.api.service.dto.CommonQuestionListDto;
import com.jayho.backend.db.entity.CommonQuestion;
import com.jayho.backend.db.entity.Study;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.repository.CommonQuestionRepository;
import com.jayho.backend.db.repository.CommonQuestionRepositoryCustom;
import com.jayho.backend.db.repository.StudyRepository;
import com.jayho.backend.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommonQuestionServiceImpl implements CommonQuestionService{

    private final CommonQuestionRepository commonQuestionRepository;
    private final CommonQuestionRepositoryCustom commonQuestionRepositoryCustom;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void registerCommonQuestion(Long userId, Long studyId, CommonQuestionReq commonQuestionReq) {
        Study study = studyRepository.findById(studyId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        CommonQuestion commonQuestion = CommonQuestion.builder()
                .study(study)
                .user(user)
                .contents(commonQuestionReq.getContents())
                .questionType(commonQuestionReq.getQuestionType())
                .build();
        commonQuestionRepository.save(commonQuestion);
    }

    @Override
    public List<CommonQuestionListDto> getList(Long studyId) {
        return commonQuestionRepositoryCustom.findCommonAndUserByStudyId(studyId);

        // 레거시 코드
//        List<CommonQuestion> commpnQuestionList = commonQuestionRepository.findAllByStudyId(studyId);
//        List<CommonQuestionDto> commonQuestionDtoList = commpnQuestionList.stream()
//                .map(c -> new CommonQuestionDto(c)).collect(Collectors.toList());
//        return commonQuestionDtoList;
    }

    @Override
    public List<CommonQuestionDto> getListFetch(Long studyId) {
        List<CommonQuestion> commonQuestionList = commonQuestionRepositoryCustom.findCommonUserByStudyIdFetch(studyId);
        return commonQuestionList.stream().map(c -> new CommonQuestionDto(c)).collect(Collectors.toList());
    }

}
