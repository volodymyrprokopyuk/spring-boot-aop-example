package org.vld.aop

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.vld.aop.service.Admirable
import org.vld.aop.service.ArithmeticCalculator
import org.vld.aop.service.CompactDisc
import org.vld.aop.service.Performance

@SpringBootApplication
open class Application : CommandLineRunner {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(Application::class.java)
    }

    @Autowired
    private lateinit var concertPerformance: Performance

    @Autowired
    @Qualifier("concertAdmirable")
    private lateinit var admirableConcertPerformance: Admirable

    @Autowired
    private lateinit var concertCompactDisc: CompactDisc

    @Autowired
    private lateinit var arithmeticCalculator: ArithmeticCalculator

    override fun run(vararg args: String?) {
        logger.debug("Starting")

        concertPerformance.perform()
        admirableConcertPerformance.showAdmiration()

        concertCompactDisc.playTrack(1)
        concertCompactDisc.playTrack(2)
        concertCompactDisc.playTrack(1)
        concertCompactDisc.playTrack(2)

        arithmeticCalculator.add(1.0, 2.0)
        arithmeticCalculator.sub(1.0, 2.0)
        arithmeticCalculator.mul(1.0, 2.0)
        arithmeticCalculator.div(1.0, 2.0)
    }
}

fun main(args: Array<String>) = SpringApplication.run(Application::class.java, *args)
