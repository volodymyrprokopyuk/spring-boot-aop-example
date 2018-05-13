package org.vld.aop

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.vld.aop.service.Performance

@SpringBootApplication
open class Application : CommandLineRunner {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(Application::class.java)
    }

    @Autowired
    private lateinit var concertPerformance: Performance

    override fun run(vararg args: String?) {
        logger.debug("Starting")
        concertPerformance.perform()
    }
}

fun main(args: Array<String>) = SpringApplication.run(Application::class.java, *args)
