package com.epam.hw5.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

@Slf4j
public class XmlConfigLoggingAspect {
    public void logBeforeServiceAdvice(JoinPoint jp) {
        String name = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        log.info("------------------------------------------");
        log.info("----------BEFORE SERVICE LAYER----------");
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
