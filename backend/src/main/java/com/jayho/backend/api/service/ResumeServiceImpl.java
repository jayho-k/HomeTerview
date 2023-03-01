package com.jayho.backend.api.service;


import com.jayho.backend.api.request.ResumeDetailCreateReq;
import com.jayho.backend.api.request.UpdateResumeReq;
import com.jayho.backend.api.service.dto.ResumeDetailDto;
import com.jayho.backend.api.service.dto.ResumeListDto;
import com.jayho.backend.common.auth.UserDetails;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.Resume;
import com.jayho.backend.db.entity.ResumeDetail;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.repository.ResumeDetailRepositoryCustom;
import com.jayho.backend.db.repository.ResumeDetailRepository;
import com.jayho.backend.db.repository.ResumeRepository;
import com.jayho.backend.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final ResumeDetailRepository resumeDetailRepository;
    private final ResumeDetailRepositoryCustom resumeDetailCustomRepository;

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
    @Transactional
    public int deleteResume(Long resumeId, Long userId) {
        Resume resume = resumeRepository.findById(resumeId).orElse(null);
        if (resume == null) return 0;
        if (userId!=resume.getUser().getId()) return 1;
        resumeRepository.delete(resume);
        return 2;
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

    @Override
    @Transactional
    public ResumeDetail createResumeDetail(ResumeDetailCreateReq resumeDetailCreateReq) {
        Resume resume = resumeRepository.findById(resumeDetailCreateReq.getResumeId()).orElseThrow();
        ResumeDetail resumeDetail = ResumeDetail.builder()
                .resume(resume)
                .itemNo(resumeDetailCreateReq.getItemNo())
                .detailQuestion(resumeDetailCreateReq.getDetailQuestion())
                .detailContents(resumeDetailCreateReq.getDetailContents())
                .build();
        return resumeDetailRepository.save(resumeDetail);
    }

    @Override
    public ResumeDetailDto getResumeDetail(Long resumeId, Long itemNo) {
        return resumeDetailCustomRepository.findByResumeIdAndItemNo(resumeId, itemNo);
    }

    @Override
    public Optional<ResumeDetail> getResumeDetailById(Long resumeDetailId) {
        return resumeDetailRepository.findById(resumeDetailId);
    }

    @Override
    @Transactional
    public ResumeDetailDto updateResumeDetail(ResumeDetail resumeDetail,Long userId , UpdateResumeReq updateResumeReq) {
        if (resumeDetail.getResume().getUser().getId() != userId) {
            return null;
        }
        resumeDetail.setDetailContents(updateResumeReq.getDetailContents());
        resumeDetail.setDetailQuestion(updateResumeReq.getDetailQuestion());
        ResumeDetailDto resumeDetailDto = new ResumeDetailDto(resumeDetail);
        return resumeDetailDto;
    }

    @Override
    @Transactional
    public int deleteResumeDetail(Long resumeDetailId, Long userId) {
        ResumeDetail resumeDetail = resumeDetailRepository.findById(resumeDetailId).orElse(null);
        if(resumeDetail==null || resumeDetail.getResume().getUser().getId()!=userId) return 1;
        else{
            resumeDetailRepository.delete(resumeDetail);
            return 0;
        }
    }
}




