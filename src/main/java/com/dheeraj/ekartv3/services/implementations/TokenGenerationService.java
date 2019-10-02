package com.dheeraj.ekartv3.services.implementations;

import com.dheeraj.ekartv3.enums.TOKEN;
import com.dheeraj.ekartv3.models.AboutMe;
import com.dheeraj.ekartv3.models.ServerSecret;
import com.dheeraj.ekartv3.services.ITokenGenerationService;
import com.dheeraj.ekartv3.util.JwtHelper;
import com.dheeraj.ekartv3.util.ObjectHelper;
import com.google.inject.Inject;

/**
 * @author Dheeraj Reddy
 */
public class TokenGenerationService implements ITokenGenerationService {

    private AboutMe aboutMe;

    @Inject
    public TokenGenerationService(AboutMe aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
    public String generateServerToken() {
        ServerSecret serverSecret = getServerSecret();
        String stringServerSecret = ObjectHelper.convertObjectToString(serverSecret);
        String serverToken = JwtHelper.generateJwtToken(serverSecret, aboutMe.getPrivateKey(), aboutMe.getServerTokenExpiry());
        return serverToken;
    }

    private ServerSecret getServerSecret() {
        return ServerSecret.builder()
                .context(aboutMe.getService())
                .token(TOKEN.SERVER)
                .build();
    }
}
