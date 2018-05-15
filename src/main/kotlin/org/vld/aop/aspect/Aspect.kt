package org.vld.aop.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.DeclareParents
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.vld.aop.service.Admirable
import org.vld.aop.service.ConcertAdmirable

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
            logger.info("@Around @Before Taking seats")
            logger.info("@Around @Before Silencing cell phones")
            pjp.proceed()
            logger.info("@Around @AfterReturning Applause")
        } catch (ex: Throwable) {
            logger.info("@Around @AfterThrowing Demanding a refund")
        }
    }
}

@Aspect
@Component
class AdmirableIntroducer {

    @DeclareParents("org.vld.aop.service.Performance+", defaultImpl = ConcertAdmirable::class)
    lateinit var admirable: Admirable
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

@Aspect
@Order(10)
@Component
class ArithmeticCalculatorLoggingAspect {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ArithmeticCalculatorLoggingAspect::class.java)
    }

    @Before("execution(* org.vld.aop.service.ArithmeticCalculator.*(..))")
    fun beforeOperation(jp: JoinPoint) = logger.info("@Before method = ${jp.signature.name}, args = ${jp.args.toList()}"
            + ", declaringType = ${jp.signature.declaringTypeName}, target = ${jp.target.javaClass.name}")

    @AfterReturning(pointcut = "execution(double org.vld.aop.service.ArithmeticCalculator.*(..))", returning = "result")
    fun afterReturningFromOperation(result: Double) = logger.info("@AfterReturning result = $result")

    @AfterThrowing(pointcut = "execution(* org.vld.aop.service.ArithmeticCalculator.div(..))", throwing = "ex")
    fun afterThrowingFromOperation(ex: IllegalArgumentException) = logger.error("@AfterThrowing ex = $ex")
}

@Aspect
@Order(20)
@Component
class ArithmeticCalculatorValidationAspect {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ArithmeticCalculatorValidationAspect::class.java)
    }

    @Before("execution(* org.vld.aop.service.ArithmeticCalculator.div(double, double)) && args(x, y)")
    fun validateBeforeDiv(jp: JoinPoint, x: Double, y: Double) {
        logger.info("@Before validating method = ${jp.signature.name}, x = $x, y = $y")
        if (y == 0.0) throw IllegalArgumentException("Validation: division by zero")
    }
}