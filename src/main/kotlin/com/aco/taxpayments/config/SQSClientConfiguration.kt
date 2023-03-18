package com.aco.taxpayments.config

import com.amazonaws.SDKGlobalConfiguration
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class SQSClientConfiguration(
    @Value("\${aws.sqs.endpoint}")
    val endpoint: String,

    @Value("\${aws.access_key_id}")
    val awsAccessKeyId: String,

    @Value("\${aws.secret_key_id}")
    val awsSecretKeyId: String,

    @Value("\${aws.region}")
    val region: String,
) {

    @Bean
    fun amazonSQSClient(): AmazonSQS? {
        System.setProperty(SDKGlobalConfiguration.DISABLE_CERT_CHECKING_SYSTEM_PROPERTY, "true")

        return AmazonSQSClientBuilder
            .standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(endpoint, region))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(awsAccessKeyId, awsSecretKeyId)))
            .build()
    }
}
