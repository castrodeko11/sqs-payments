package com.aco.taxpayments.configuration

import com.amazonaws.SDKGlobalConfiguration
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class SQSClientConfiguration(
    @Value("\${cloud.aws.queue.endpoint}")
    val endpoint: String,

    @Value("\${cloud.aws.credentials.access-key}")
    val awsAccessKeyId: String,

    @Value("\${cloud.aws.credentials.secret-key}")
    val awsSecretKeyId: String,

    @Value("\${cloud.aws.region.static}")
    val region: String
) {

    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate? {
        return QueueMessagingTemplate(amazonSQSClient())
    }

    @Bean
    @Primary
    fun amazonSQSClient(): AmazonSQSAsync? {
        System.setProperty(SDKGlobalConfiguration.DISABLE_CERT_CHECKING_SYSTEM_PROPERTY, "true")

        return AmazonSQSAsyncClientBuilder
            .standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(endpoint, region))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(awsAccessKeyId, awsSecretKeyId)))
            .build()
    }
}
