package com.jayho.backend.api.response;

import com.jayho.backend.api.service.dto.CommonQuestionDto;
import com.jayho.backend.api.service.dto.CommonQuestionListDto;
import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.QuestionType;
import com.jayho.backend.db.entity.Recruit;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommonQuestionListRes extends BaseResponseBody {

    private List<CommonQuestionListDto> commonQuestionDtoList;

    public static CommonQuestionListRes of(List<CommonQuestionListDto> commonQuestionDtoList, Integer statusCode, String message){
        CommonQuestionListRes res = new CommonQuestionListRes();
        res.setCommonQuestionDtoList(commonQuestionDtoList);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
