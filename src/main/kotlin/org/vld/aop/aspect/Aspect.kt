package org.vld.aop.aspect

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class Audience {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(Audience::class.java)
    }

    @Pointcut("execution(* org.vld.aop.service.Performance.perform(..))")
    fun performPerformance() {}

    @Before("performPerformance()")
    fun takeSeats() = logger.info("Taking seats")

    @Before("performPerformance()")
    fun silenceCellPhones() = logger.info("Silencing cell phones")

    @AfterReturning("performPerformance()")
    fun applause() = logger.info("Applause")

    @AfterThrowing("performPerformance()")
    fun demandRefund() = logger.info("Demanding a refund")
}