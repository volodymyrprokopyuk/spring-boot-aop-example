package org.vld.aop.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

interface Performance {
    fun perform()
}

@Component
class ConcertPerformance : Performance {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ConcertPerformance::class.java)
    }
    override fun perform() = logger.info("Performing concert performance")
}