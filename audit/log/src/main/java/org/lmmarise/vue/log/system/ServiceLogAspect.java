package org.lmmarise.vue.log.system;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 记录system模块下的service被调用的日志
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 4:13 下午
 */
@Component
@Aspect
public class ServiceLogAspect {
    private static final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);
    
    @Pointcut("execution(* org.lmmarise.vue.*.*.service.*.*(..))")
    public void serviceLog() {
    }

    @Before(value = "serviceLog()")
    public void before(JoinPoint jp) {
        String name = jp.getSignature().getName();
        log.info(name + "方法开始执行...");
    }

    @After(value = "serviceLog()")
    public void after(JoinPoint jp) {
        String name = jp.getSignature().getName();
        log.info(name + "方法执行结束...");
    }

    @AfterReturning(value = "serviceLog()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        String name = jp.getSignature().getName();
        log.info(name + "方法返回值为：" + result);
    }

    @AfterThrowing(value = "serviceLog()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        String name = jp.getSignature().getName();
        log.info(name + "方法抛异常了，异常是：" + e.getMessage());
    }

    @Around("serviceLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }
}
