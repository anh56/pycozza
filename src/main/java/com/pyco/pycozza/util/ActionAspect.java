package com.pyco.pycozza.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ActionAspect {
    private static Logger logger = LoggerFactory.getLogger(ActionAspect.class);

    @Around("execution(* com.pyco.pycozza.service..*.*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        Object result = null;
        try {
            logger.info("Start method '{}' with arguments {}", methodName, this.getParameterNames(joinPoint));
            result = joinPoint.proceed();
            logger.info("End method '{}'", methodName);
        } catch (Throwable ex) {
            logger.error("Failed to execute '{}' {}", methodName, ex);
        }
        return result;
    }

    private String getParameterNames(ProceedingJoinPoint joinPoint) {
        CodeSignature codeSig = (CodeSignature)joinPoint.getSignature();
        return String.join(", ", codeSig.getParameterNames());
    }


}
