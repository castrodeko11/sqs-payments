package com.aco.taxpayments.service

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.*
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
internal class MessageQueueService {
    @Value("\${app.config.message.queue.topic}")
    private val messageQueueTopic: String? = null

    @Autowired
    private val amazonSQSClient: AmazonSQS? = null

    @Scheduled(fixedDelay = 5000) // executes on every 5 second gap.
    fun receiveMessages() {
        try {
            val queueUrl = amazonSQSClient!!.getQueueUrl(messageQueueTopic)


            println("Reading SQS Queue done: URL $queueUrl")
            val receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl.queueUrl)
            if (!receiveMessageResult.messages.isEmpty()) {
                val message = receiveMessageResult.messages[0]
                println("Incoming Message From SQS: " + message.messageId)
                println("Message Body: " +  message.body)
                processInvoice(message.body)
                amazonSQSClient.deleteMessage(queueUrl.queueUrl, message.receiptHandle)
            }
        } catch (e: QueueDoesNotExistException) {
            println("Queue does not exist" +  e.errorMessage)
        }
    }

    private fun processInvoice(body: String) {
        println("Messages: $body")
    }


}