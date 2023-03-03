package com.jayho.backend.api.response;

import com.jayho.backend.api.service.dto.PersonalQuestionDto;
import com.jayho.backend.api.service.dto.RecruitDto;
import com.jayho.backend.common.model.response.BaseResponseBody;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PersonalQuestionListRes extends BaseResponseBody {

    private List<PersonalQuestionDto> personalQuestionDtoList;
    private int count;

    public static PersonalQuestionListRes of(List<PersonalQuestionDto> personalQuestionDtoList, int count,Integer statusCode, String message) {
        PersonalQuestionListRes res = new PersonalQuestionListRes();
        res.setPersonalQuestionDtoList(personalQuestionDtoList);
        res.setCount(count);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}