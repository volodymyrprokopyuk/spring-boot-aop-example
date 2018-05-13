package org.vld.aop.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
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
    fun takeSeats() = logger.info("@Before Taking seats")

    @Before("performPerformance()")
    fun silenceCellPhones() = logger.info("@Before Silencing cell phones")

    @AfterReturning("performPerformance()")
    fun applause() = logger.info("@AfterReturning Applause")

    @AfterThrowing("performPerformance()")
    fun demandRefund() = logger.info("@AfterThrowing Demanding a refund")

    @Around("performPerformance()")
    fun watchPerformance(pjp: ProceedingJoinPoint) {
        try {
            logger.info("@Before Taking seats")
            logger.info("@Before Silencing cell phones")
            pjp.proceed()
            logger.info("@AfterReturning Applause")
        } catch (ex: Throwable) {
            logger.info("@AfterThrowing Demanding a refund")
        }
    }
}

@Aspect
@Component
class TrackCounter {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(TrackCounter::class.java)
    }

    private val trackCounts = mutableMapOf<Int, Int>()

    @Pointcut("execution(* org.vld.aop.service.CompactDisc.playTrack(int)) && args(trackNumber)")
    fun playTrackFromCompactDisc(@Suppress("UNUSED_PARAMETER") trackNumber: Int) {}

    @Before("playTrackFromCompactDisc(trackNumber)")
    fun countTrack(trackNumber: Int) {
        val trackCount = trackCounts.getOrDefault(trackNumber, 0)
        trackCounts[trackNumber] = trackCount + 1
        logger.info("@Before TrackNumber = $trackNumber, TrackCount = ${trackCounts[trackNumber]}")
    }
}