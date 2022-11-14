package com.msousacode.bolao.security.cognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
import com.msousacode.bolao.enuns.ServiceErrorsType;
import com.msousacode.bolao.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
public class CognitoService {

    @Autowired
    private AWSCognitoIdentityProvider cognitoIdentityProvider;

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.secret}")
    private String clientSecret;

    public String login(String userName, String password) {

        var authParams = Map.of(
                "USERNAME", userName,
                "PASSWORD", password,
                "SECRET_HASH", calculateSecretHash(clientId, clientSecret, userName)
        );

        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_USER_PASSWORD_AUTH)
                .withClientId(clientId)
                .withUserPoolId(userPoolId)
                .withAuthParameters(authParams);

        try {

            var authResult = cognitoIdentityProvider.adminInitiateAuth(authRequest);

            if (authResult.getChallengeName() == ChallengeNameType.NEW_PASSWORD_REQUIRED.name()) {
                throw new ServiceException(ServiceErrorsType.NEW_PASS_WORD_REQUIRED);
            }
            //Todo Adicinar logs infos
            return authResult.getAuthenticationResult().getIdToken();

        } catch (Exception ex) {
            ex.printStackTrace();
            //Todo Adicionar logs erros
            throw new ServiceException(ServiceErrorsType.INVALID_ACCESS);
        }
    }

    private String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) {

        var HMAC_SHA256_ALGORITHM = "HmacSHA256";
        var signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM
        );

        var secretHash = "";

        try {

            var mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            var rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));

            secretHash = Base64.getEncoder().encodeToString(rawHmac);

        } catch (Exception ex) {
            throw new ServiceException(ServiceErrorsType.INVALID_ACCESS);
        }

        return secretHash;
    }
}
