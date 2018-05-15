package org.vld.aop.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.vld.aop.annotation.Logged

interface Performance {
    fun perform()
}

@Component
class ConcertPerformance : Performance {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ConcertPerformance::class.java)
    }
    override fun perform() = logger.info(">> Performing concert performance")
}

interface Admirable {
    fun showAdmiration()
}

@Component
class ConcertAdmirable : Admirable {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ConcertAdmirable::class.java)
    }

    override fun showAdmiration() = logger.info("@DeclareParents What a wonderful concert! Thank you very much!")
}

interface CompactDisc {
    fun playTrack(trackNumber: Int)
}

@Component
class ConcertCompactDisc : CompactDisc {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ConcertCompactDisc::class.java)
    }

    override fun playTrack(trackNumber: Int) = logger.info(">> Playing track $trackNumber")
}

interface ArithmeticCalculator {
    fun add(x: Double, y: Double): Double
    fun sub(x: Double, y: Double): Double
    fun mul(x: Double, y: Double): Double
    fun div(x: Double, y: Double): Double
}

@Component
class ArithmeticCalculatorImpl : ArithmeticCalculator {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ArithmeticCalculatorImpl::class.java)
    }

    @Logged
    override fun add(x: Double, y: Double): Double {
        val result = x + y
        logger.info(">> add($x, $y) = $result")
        return result
    }

    override fun sub(x: Double, y: Double): Double {
        val result = x - y
        logger.info(">> sub($x, $y) = $result")
        return result
    }

    override fun mul(x: Double, y: Double): Double {
        val result = x * y
        logger.info(">> mul($x, $y) = $result")
        return result
    }

    override fun div(x: Double, y: Double): Double {
        if (y == 0.0) throw IllegalArgumentException("Division by zero")
        val result = x / y
        logger.info(">> div($x, $y) = $result")
        return result
    }
}

interface MinCalculator {
    fun min(x: Double, y: Double): Double
}

@Component
class MinCalculatorImpl : MinCalculator {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(MinCalculatorImpl::class.java)
    }

    override fun min(x: Double, y: Double): Double {
        val result = if (x < y) x else y
        logger.info(">> min ($x, $y) = $result")
        return result
    }
}

interface MaxCalculator {
    fun max(x: Double, y: Double): Double
}

@Component
class MaxCalculatorImpl : MaxCalculator {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(MaxCalculatorImpl::class.java)
    }

    override fun max(x: Double, y: Double): Double {
        val result = if (x > y) x else y
        logger.info(">> max ($x, $y) = $result")
        return result
    }
}