package com.msousacode.bolao.security.cognito;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class CognitoConfiguration {

    private Logger logger = LoggerFactory.getLogger(CognitoConfiguration.class);

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.secret}")
    private String clientSecret;

    @Value("${aws.cognito.region}")
    private String region;

    @Value("${aws.cognito.jwkUrl}")
    private String jwkUrl;

    @Bean
    public AWSCognitoIdentityProvider cognitoIdentityProvider() {
        logger.debug("Configuring Cognito");

        //Outra maneira de capturar as variáveis do Cognito, pode-se armazenar os valores no application.properties.
        //BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AWS_ACCESS_KEY_ID", "AWS_SECRET_ACCESS_KEY");

        var awsCredentials = new EnvironmentVariableCredentialsProvider();
        AWSCognitoIdentityProvider cognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder.standard()
                .withRegion(region)
                //Aqui incorma as credenciais
                //.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
                .withCredentials(awsCredentials).build();
        logger.debug("Cognito initialized successfully");
        return cognitoIdentityProvider;
    }

    @Bean
    public ConfigurableJWTProcessor configurableJWTProcessor() throws MalformedURLException {
        ResourceRetriever resourceRetriever =
                new DefaultResourceRetriever(2000, 2000);
        URL jwkURL = new URL(jwkUrl);
        JWKSource keySource = new RemoteJWKSet(jwkURL, resourceRetriever);
        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
        JWSKeySelector keySelector = new JWSVerificationKeySelector(JWSAlgorithm.RS256, keySource);
        jwtProcessor.setJWSKeySelector(keySelector);
        return jwtProcessor;
    }
}
