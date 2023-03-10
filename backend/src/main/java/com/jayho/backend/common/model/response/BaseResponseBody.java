package com.jayho.backend.common.model.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseBody {

    Integer statusCode = null;
    String message = null;
    // 생성자
    public BaseResponseBody(){}
    public BaseResponseBody(Integer statusCode){
        this.statusCode = statusCode;
    }

    public BaseResponseBody(Integer statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }

    public static BaseResponseBody of (Integer statusCode, String message){
        BaseResponseBody body = new BaseResponseBody();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }


}
