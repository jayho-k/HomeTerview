package com.jayho.backend.api.response;

import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.Recruit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class RecruitRes extends BaseResponseBody {

    private RecruitDto recruitDto;

    public static RecruitRes of (RecruitDto recruitDto, Integer statusCode, String message){
        RecruitRes res = new RecruitRes();
        res.setRecruitDto(recruitDto);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
