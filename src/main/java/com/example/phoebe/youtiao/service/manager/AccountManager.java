package com.example.phoebe.youtiao.service.manager;

import com.example.phoebe.youtiao.commmon.util.HttpClientUtil;
import com.example.phoebe.youtiao.commmon.util.HttpUtil;
import com.google.gson.Gson;
import com.ning.http.client.Response;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountManager {
    final String appid = "wxdb53550a763fe91e";
    final String secret = "cb2d87411aeb5c261bd05f9939d1dcb9";

    Gson gs = new Gson();

    @Data
    public class WxAuth{
        String session_key;
        String openid;
    }

    public WxAuth getWxSession(String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code=";
        url += code;
        url += "&grant_type=authorization_code";
        Response response = HttpClientUtil.doGet(url);

        try {
            String answear= response.getResponseBody();
            return gs.fromJson(answear, WxAuth.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
