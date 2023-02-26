package com.jayho.backend.api.response;

import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.Recruit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class ApplyListRes extends BaseResponseBody {

    private List<Recruit> applyingList;
    public static ApplyListRes of(List<Recruit> applyingList, Integer statusCode, String message){
        ApplyListRes res = new ApplyListRes();
        res.setApplyingList(applyingList);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}