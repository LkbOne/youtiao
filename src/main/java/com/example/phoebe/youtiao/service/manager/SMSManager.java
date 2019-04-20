package com.example.phoebe.youtiao.service.manager;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SMSManager {
    final String appkey = "ff1f828436b3874a541f16727489495e";
    final int appid = 1400170140;
    final int templateId = 314149;
    final String smsSign = "205183";
    public boolean sendSMS(String phone, String verificationCode, Integer mins){
        try {
            String[] params = {verificationCode, String.valueOf(mins)};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    templateId, params, "", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
