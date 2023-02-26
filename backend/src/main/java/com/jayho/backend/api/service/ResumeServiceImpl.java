package com.jayho.backend.api.service;


import com.jayho.backend.api.request.RecruitInfoReq;
import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.api.service.dto.ResumeListDto;
import com.jayho.backend.db.entity.Recruit;
import com.jayho.backend.db.entity.Resume;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.repository.ResumeDetailRepository;
import com.jayho.backend.db.repository.ResumeRepository;
import com.jayho.backend.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final ResumeDetailRepository resumeDetailRepository;

    @Override
    @Transactional
    public void createResume(Long userId, String resumeTitle) {
        User user = userRepository.findById(userId).orElse(null);
        Resume resume = Resume.builder()
                .resumeTitle(resumeTitle)
                .user(user)
                .build();
        resumeRepository.save(resume);
    }

    @Override
    public List<ResumeListDto> getResumeList(Long userId) {
        List<Resume> resumeList = resumeRepository.findAllByUserId(userId);
        List<ResumeListDto> result = resumeList.stream().map(r -> new ResumeListDto(r)).collect(Collectors.toList());
        return result;
    }
    @Override
    @Transactional
    public ResumeListDto updateResume(Long userId, Long resumeId, String resumeTitle) {
        Resume resume = resumeRepository.findByIdAndUserId(resumeId, userId).orElse(null);
        if (resume==null){return null;}
        resume.setResumeTitle(resumeTitle);
        ResumeListDto resumeListDto = new ResumeListDto(resume);
        return resumeListDto;
    }
}




