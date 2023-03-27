package com.aco.taxpayments.web.controller

import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.model.SendMessageRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/queue")
class QueueController(
    @Value("\${cloud.aws.queue.tax-payments}") private val queueName: String,
    var sqsClientAsycs: AmazonSQSAsync
) {

    @PostMapping
    fun sendMessage(@RequestBody message: String): ResponseEntity<String> {

        val sendMessageStandardQueue = SendMessageRequest()
            .withMessageBody(message)
            .withQueueUrl("$queueName")

        val result = sqsClientAsycs.sendMessage(sendMessageStandardQueue)

        return ResponseEntity.ok().body(result.messageId)
    }
}