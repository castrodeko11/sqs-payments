package com.aco.taxpayments

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class TaxpaymentsApplication

fun main(args: Array<String>) {
    runApplication<TaxpaymentsApplication>(*args)
}
