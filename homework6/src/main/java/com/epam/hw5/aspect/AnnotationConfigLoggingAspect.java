package com.epam.hw5.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AnnotationConfigLoggingAspect {

    @Pointcut("target(com.epam.hw5.repository.AccountRepository)" +
            "|| target(com.epam.hw5.repository.UserRepository)")
    public void inRepositoryLayer() {
    }

    @After("inRepositoryLayer()")
    public void logAfterRepositoryAdvice(JoinPoint jp) {
        String name = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        log.info("------------------------------------------");
        log.info("----------AFTER REPOSITORY LAYER----------");
        log.info("------------------------------------------");
        log.info("Executed method: " + name);
        log.info("With arguments: ");

        if (args.length != 0) {
            for (Object arg : args) {
                log.info("   -> " + arg);
            }
        } else {
            log.info("   -> Method has no arguments!");
        }
    }
}
