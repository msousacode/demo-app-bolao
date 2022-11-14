package com.msousacode.bolao.security.cognito;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CognitoConfiguration {

    private Logger logger = LoggerFactory.getLogger(CognitoConfiguration.class);

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.secret}")
    private String clientSecret;

    @Value("${aws.cognito.region}")
    private String region;

    @Bean
    public AWSCognitoIdentityProvider cognitoIdentityProvider() throws Exception {
        logger.debug("Configuring Cognito");

        //BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AWS_ACCESS_KEY_ID", "AWS_SECRET_ACCESS_KEY");

        var awsCredentials = new EnvironmentVariableCredentialsProvider();
        AWSCognitoIdentityProvider cognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder.standard()
                .withRegion(region)
                //.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
                .withCredentials(awsCredentials).build();
        logger.debug("Cognito initialized successfully");
        return cognitoIdentityProvider;
    }
}