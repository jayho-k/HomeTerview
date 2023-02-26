package com.jayho.backend.api.response;

import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.Recruit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class RecruitListRes extends BaseResponseBody {

    private Page<RecruitDto> recruitList;

    public static RecruitListRes of(Page<RecruitDto> recruitList, Integer statusCode, String message){
        RecruitListRes res = new RecruitListRes();
        res.setRecruitList(recruitList);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
