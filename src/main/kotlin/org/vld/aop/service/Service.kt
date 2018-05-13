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