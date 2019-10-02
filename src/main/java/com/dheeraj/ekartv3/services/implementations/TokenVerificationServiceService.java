package com.dheeraj.ekartv3.services.implementations;

import com.dheeraj.ekartv3.models.AboutMe;
import com.dheeraj.ekartv3.models.ServerSecret;
import com.dheeraj.ekartv3.models.ServiceInfo;
import com.dheeraj.ekartv3.services.IHttpClient;
import com.dheeraj.ekartv3.services.ITokenVerificationService;
import com.dheeraj.ekartv3.util.CacheHelper;
import com.dheeraj.ekartv3.util.JwtHelper;
import com.dheeraj.ekartv3.util.ObjectHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import play.libs.Json;

/**
 * @author Dheeraj Reddy
 */
public class TokenVerificationServiceService implements ITokenVerificationService {

    private IHttpClient httpClient;
    private AboutMe aboutMe;
    private final String SERVICE_INFO_URL = "http://%s/%s/about";
    private CacheHelper cacheHelper;

    @Inject
    public TokenVerificationServiceService(IHttpClient httpClient, AboutMe aboutMe) {
        this.httpClient = httpClient;
        this.aboutMe = aboutMe;
        this.cacheHelper = new CacheHelper();
    }

    @Override
    public void validateServerToken(String jwtToken) {
        String encodedPayloadString = JwtHelper.getJwtPayLoad(jwtToken);
        String decodedPayloadString = ObjectHelper.decodeBase64EncodedString(encodedPayloadString);
        ServerSecret serverSecret = ObjectHelper.convertStringToObject(decodedPayloadString, ServerSecret.class);
        String senderPublicKey = getSenderPublicKey(serverSecret.getContext());
        JwtHelper.validateJwtToken(jwtToken, senderPublicKey);
    }

    private String getSenderPublicKey(String context) {
        if (cacheHelper.getPublicKeyCache().containsKey(context)) {
            return cacheHelper.getPublicKeyFromService(context);
        } else {
            JsonNode serviceInfoJson = httpClient.get(getSenderServiceInfoUrl(context));
            ServiceInfo serviceInfo = Json.fromJson(serviceInfoJson, ServiceInfo.class);
            cacheHelper.addToPublicKeyCache(serviceInfo);
            return serviceInfo.getPublicKey();
        }
    }

    private String getSenderServiceInfoUrl(String context) {
        return String.format(SERVICE_INFO_URL, aboutMe.getApiHostDomain(), context);
    }
}
