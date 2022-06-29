package com.yyd.yyd.constants.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Yanxu
 * @Date 2018/4/20 16:32
 */
@Aspect
@Component
public class ReadSlaveInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ReadSlaveInterceptor.class);


    @Pointcut("@annotation(com.yyd.yyd.constants.datasource.ReadSlave)")
    private void anyMethod() {
    }

    @Around("anyMethod()")
    public Object setdataSourceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        ReadSlave dataSource = null;
        try {
            dataSource = HandleDataSource.getDataSource();
            /**
             * 获取注解
             */
            ReadSlave readSlave = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(ReadSlave.class);
            logger.debug("ReadSlaveInterceptor setdataSourceAspect: readSlave is {}",readSlave);
            HandleDataSource.putDataSource(readSlave);
            //执行目标方法
            result = joinPoint.proceed();
      } catch (Exception e) {
            logger.warn("ReadSlaveInterceptor setdataSourceAspect: hava exception is {}",e.getMessage());
            throw  e;
      }finally {
            HandleDataSource.putDataSource(dataSource);
            logger.debug("ReadSlaveInterceptor setdataSourceAspect: finally ");
      }
      return result;



    }
}
