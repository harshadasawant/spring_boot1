package com.hnd.infinite.aspect;
import com.hnd.infinite.Exception.HnDBankException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
    @Before("execution(* com.hnd.infinite.service.*Impl.*(..))")
    public void before() throws HnDBankException {
        LOGGER.info("Before advice called.");
    }
    @After("execution(* com.hnd.infinite.service.*Impl.*(..))")
    public void after() throws HnDBankException {
        LOGGER.info("After advice called.");
    }
    @Around("execution(* com.hnd.infinite.service.*Impl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        LOGGER.info("around advice called.");
        try
        {
            jp.proceed();
        }
        finally
        {

        }
        System.out.println("The method aroundAdvice() after invokation of the method " + jp.getSignature().getName() + " method");
    }
    @AfterReturning(value = "execution(* com.hnd.infinite.service.*Impl.*(..))",returning ="returnvalue")
    public void afterReturning(String returnvalue ) throws HnDBankException {
        LOGGER.info("After returning advice called."+returnvalue);
    }
    @AfterThrowing(pointcut = "execution(* com.hnd.infinite.service.*Impl.*(..))", throwing = "exception")
    public void afterThrowing(HnDBankException exception) throws HnDBankException {

        LOGGER.error(exception.getMessage(), exception);
    }
}
