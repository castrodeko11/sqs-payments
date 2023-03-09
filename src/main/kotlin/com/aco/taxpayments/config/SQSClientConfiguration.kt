package com.aco.taxpayments.config

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class SQSClientConfiguration {

    @Value("\${aws.access_key_id}")
    private val awsAccessKeyId: String? = null

    @Value("\${aws.secret_key_id}")
    private val awsSecretKeyId: String? = null

    @Value("\${aws.region}")
    private val awsRegion: String? = null

    @Bean
    fun amazonSQSClient(): AmazonSQS? {
        val awsCredentials =
            BasicAWSCredentials(awsAccessKeyId, awsSecretKeyId)
        return AmazonSQSClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .withRegion(Regions.US_EAST_1).build()
    }
}
