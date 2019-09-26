package com.dheeraj.ekartv3.services.implementations;

import com.dheeraj.ekartv3.enums.TOKEN;
import com.dheeraj.ekartv3.models.AboutMe;
import com.dheeraj.ekartv3.models.ServerSecret;
import com.dheeraj.ekartv3.services.ITokenGenerationService;
import com.dheeraj.ekartv3.util.JwtHelper;
import com.dheeraj.ekartv3.util.StringHelper;
import com.google.inject.Inject;

/**
 * @author Dheeraj Reddy
 */
public class TokenGenerationService implements ITokenGenerationService {

    private AboutMe aboutMe;
    private StringHelper stringHelper;

    @Inject
    public TokenGenerationService(AboutMe aboutMe, StringHelper stringHelper) {
        this.aboutMe = aboutMe;
        this.stringHelper = stringHelper;
    }

    @Override
    public String generateServerToken() {
        ServerSecret serverSecret = getServerSecret();
        String stringServerSecret = stringHelper.convertObjectToString(serverSecret);
        return JwtHelper.generateJwtToken(stringServerSecret, aboutMe.getPrivateKey());
    }

    private ServerSecret getServerSecret() {
        return ServerSecret.builder()
                .context(aboutMe.getService())
                .token(TOKEN.SERVER)
                .build();
    }
}
