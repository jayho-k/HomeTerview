package com.jayho.backend.api.response;

import com.jayho.backend.common.model.response.BaseResponseBody;
import com.jayho.backend.db.entity.User;
import com.jayho.backend.db.entity.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRes extends BaseResponseBody {

    private String accessToken;
    private Long id;
    private UserType userType;
    private String userName;
    private String userImg;

    public static LoginUserRes of(User user, Integer statusCode, String message, String accessToken) {
        LoginUserRes res = new LoginUserRes();
        res.setStatusCode(statusCode);
        res.setId(user.getId());
        res.setUserName(user.getUserName());
        res.setUserType(user.getUserType());
        res.setUserImg(user.getUserImg());
        res.setMessage(message);
        res.setAccessToken(accessToken);
        return res;
    }
}
