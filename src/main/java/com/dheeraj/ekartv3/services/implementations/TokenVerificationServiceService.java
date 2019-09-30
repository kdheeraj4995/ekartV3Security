package com.dheeraj.ekartv3.services.implementations;

import com.dheeraj.ekartv3.models.AboutMe;
import com.dheeraj.ekartv3.models.ServerSecret;
import com.dheeraj.ekartv3.models.ServiceInfo;
import com.dheeraj.ekartv3.services.IHttpClient;
import com.dheeraj.ekartv3.services.ITokenVerificationService;
import com.dheeraj.ekartv3.util.JwtHelper;
import com.dheeraj.ekartv3.util.StringHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.libs.Json;

/**
 * @author Dheeraj Reddy
 */
public class TokenVerificationServiceService implements ITokenVerificationService {

    private IHttpClient httpClient;
    private AboutMe aboutMe;
    private final String SERVICE_INFO_URL = "http://%s/%s/about";

    @Inject
    public TokenVerificationServiceService(IHttpClient httpClient, AboutMe aboutMe) {
        this.httpClient = httpClient;
        this.aboutMe = aboutMe;
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
        JsonNode serviceInfoJson = httpClient.get(getSenderServiceInfoUrl(context));
        ServiceInfo serviceInfo = Json.fromJson(serviceInfoJson, ServiceInfo.class);
        return serviceInfo.getPublicKey();
    }

    private String getSenderServiceInfoUrl(String context) {
        return String.format(SERVICE_INFO_URL, aboutMe.getApiHostDomain(), context);
    }
}
