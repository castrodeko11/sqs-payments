package com.aco.taxpayments.listener

import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Service

@Service
class QueueListener {


    private val logger = LoggerFactory.getLogger(javaClass)


    @SqsListener(value = ["tax-payments"])
    fun listener(message: String) {
        logger.info("\nReceived message <> SqsListener: $message")
    }
}