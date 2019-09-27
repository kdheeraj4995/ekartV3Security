package com.dheeraj.ekartv3.services.implementations;

import com.dheeraj.ekartv3.models.ServerSecret;
import com.dheeraj.ekartv3.services.IHttpClient;
import com.dheeraj.ekartv3.services.ITokenVerificationService;
import com.dheeraj.ekartv3.util.JwtHelper;
import com.dheeraj.ekartv3.util.StringHelper;
import com.google.inject.Inject;

/**
 * @author Dheeraj Reddy
 */
public class TokenVerificationServiceService implements ITokenVerificationService {

    private IHttpClient httpClient;

    @Inject
    public TokenVerificationServiceService(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void validateServerToken(String jwtToken) {
        String encodedPayloadString = JwtHelper.getJwtPayLoad(jwtToken);
        String decodedPayloadString = StringHelper.decodeBase64EncodedString(encodedPayloadString);
        ServerSecret serverSecret = StringHelper.convertStringToObject(decodedPayloadString, ServerSecret.class);
        String senderPublicKey = getSenderPublicKey(serverSecret.getContext());
        JwtHelper.validateJwtToken(jwtToken, senderPublicKey);
    }

    private String getSenderPublicKey(String context) {
        return null;
    }
}
