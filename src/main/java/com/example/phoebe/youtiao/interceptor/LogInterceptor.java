package com.example.phoebe.youtiao.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class LogInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String className = invocation.getMethod().getDeclaringClass().getName();
		String methodName = invocation.getMethod().getName();

		long beforeTime = System.currentTimeMillis();
		try {
			Object result = invocation.proceed();

			long afterTime = System.currentTimeMillis();
			log.info("service finished, serviceName={}#{} args={} result={} duration={}ms",
				className, methodName, invocation.getArguments(), result, afterTime - beforeTime);

			return result;
		} catch (Throwable e) {
			long afterTime = System.currentTimeMillis();
			log.error("service exception, serviceName={}#{} args={} duration={}ms exception:",
				className, methodName, invocation.getArguments(), afterTime - beforeTime, e);
			throw e;
		}
	}

}
