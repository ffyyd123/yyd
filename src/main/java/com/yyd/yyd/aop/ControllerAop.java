package com.yyd.yyd.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * aop
 * controller层aop，打印日志
 */
@Component
@Aspect
@Slf4j
public class ControllerAop {
    //声明切入点，@Doc自定义注解出现在哪个方法上就对哪个方法生效
    @Pointcut("@annotation(com.yyd.yyd.aop.Doc)")
    private void pointcut(){

    }
    //前置通知，方法执行前通知
    @Before("pointcut()")
    private void befor(JoinPoint joinPoint)  {
        Object[] args = joinPoint.getArgs();
        System.err.println(args.toString());
        log.info("start:::"+joinPoint.getSignature().getDeclaringTypeName()+"===="+joinPoint.getSignature().getName());
    //    System.out.println("start:::"+joinPoint.getSignature().getDeclaringTypeName()+"===="+joinPoint.getSignature().getName());
    }
    //后置通知，方法执行后通知
    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    private void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        log.info("请求返回 : {}", ret);
    }

    //环绕通知，在方法执行前有提示，在方法执行后有提示
//    @Around("pointcut()")
//    private Object around(ProceedingJoinPoint point) throws Throwable {
//        System.out.println("around执行了");
//        Object result = point.proceed();
//        System.out.println("around结束了");
//        return result;
//    }
}
