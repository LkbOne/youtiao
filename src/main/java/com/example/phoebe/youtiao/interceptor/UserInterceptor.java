package com.example.phoebe.youtiao.interceptor;

import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.annotion.TokenCheckTrigger;
import com.example.phoebe.youtiao.controller.arg.BaseArg;
import com.example.phoebe.youtiao.service.manager.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserInterceptor implements MethodInterceptor {
    @Autowired
    TokenManager tokenManager;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();

        if (arguments == null || arguments.length == 0 || arguments[0] == null) {
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }

        Object token = null;
        Object arg = arguments[0];

        if (arguments.length > 1) {
            token = arguments[0];
            arg = arguments[1];
        }

//        if (arg instanceof BaseArg) {
//            BaseArg baseArg = (BaseArg) arg;
//            baseArg.setMiniAppSource(SourceContext.getSource());
//        }

        TokenCheckTrigger tokenCheckTriggerAnnotation = invocation.getMethod().getAnnotation(TokenCheckTrigger.class);
        if (tokenCheckTriggerAnnotation != null) {
            if (arg instanceof BaseArg) {
                BaseArg baseArg = (BaseArg) arg;
                System.out.println("old baseArg:" + baseArg.toString());
                String tokenString = (String)token;
                if (StringUtils.isBlank(tokenString)) {
                    log.warn("token not found");
                    return new ModelResult(SHErrorCode.LOGIN_TOKEN_INVALID);
                }

                ModelResult<BaseArg>baseArgModelResult = tokenManager.getUserInfo(tokenString);
                if (!baseArgModelResult.isSuccess()) {
                    return baseArgModelResult;
                }
                BaseArg newBaseArg = baseArgModelResult.getData();
                log.info("newBaseArg={}", newBaseArg);

                baseArg.setToken(newBaseArg.getToken());
                baseArg.setOpenid(newBaseArg.getOpenid());
                baseArg.setSessionKey(newBaseArg.getSessionKey());
                baseArg.setAccountId(newBaseArg.getAccountId());
                System.out.println("new baseArg accountId:" + baseArg.getAccountId());
//                baseArg.setMiniAppVersion(newBaseArg.getMiniAppVersion());
            }
        }
        return invocation.proceed();
    }
}
