package com.spring.customersspringmvc.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {

    private Logger logger = Logger.getLogger(this.getClass().getName());


    @Pointcut("execution(* com.spring.customersspringmvc.controllers.CustomerController.getCustomerById(..))")
    public void methodById() {}
    @Pointcut("execution(* com.spring.customersspringmvc.controllers.CustomerController.saveCustomer(..))")
    public void methodSave() {}
    @Pointcut("execution(* com.spring.customersspringmvc.controllers.CustomerController.*(..))")
    public void allCustomerMethods() {}


    @Around("allCustomerMethods()")
    public Object logMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String remoteAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr();
        if ("getCustomerById".equals(methodName)) {
            logger.info("Logging for getCustomerById from " + remoteAddress);
        } else if ("saveCustomer".equals(methodName)) {
            logger.info("Logging for saveCustomer from " + remoteAddress);
        }
        Object result = joinPoint.proceed();
        System.out.println("Logging result: " + result);
        return result;
    }




}
