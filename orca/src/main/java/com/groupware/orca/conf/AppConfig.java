package com.groupware.orca.conf;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${aws.s3.region}")
    private String region;
    @Value("${aws.s3.accessToken}")
    private String accessToken;
    @Value("${aws.s3.secretKey}")
    private String secretKey;

    @Bean
    public AmazonS3 m01(){
        BasicAWSCredentials credentials= new BasicAWSCredentials(accessToken , secretKey);
        AWSStaticCredentialsProvider provider= new AWSStaticCredentialsProvider(credentials);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(provider)
                .build();

    }

}

