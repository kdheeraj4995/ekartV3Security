package com.dheeraj.ekartv3;

import com.dheeraj.ekartv3.exceptions.EkartJwtException;
import com.dheeraj.ekartv3.models.AboutMe;
import com.dheeraj.ekartv3.models.ServiceInfo;
import com.dheeraj.ekartv3.services.IHttpClient;
import com.dheeraj.ekartv3.services.ITokenGenerationService;
import com.dheeraj.ekartv3.services.ITokenVerificationService;
import com.dheeraj.ekartv3.services.implementations.TokenGenerationService;
import com.dheeraj.ekartv3.services.implementations.TokenVerificationServiceService;
import com.dheeraj.ekartv3.util.PublicPrivateKeyGenerator;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import play.libs.Json;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class ServerTokenTest {

    private Injector injector;
    private PublicPrivateKeyGenerator publicPrivateKeys;
    private AboutMe aboutMe;
    private ServiceInfo serviceInfo;
    @Mock
    private IHttpClient httpClient;

    @Before
    public void setUp() throws Exception {
        initiatePublicPrivateKeys();
        initiateAboutMe();
        initiateServiceInfo();
        mockObjects();
        initiateGoogleInjector();
    }

    @Test
    public void generateAndValidateServerToken() {
        ITokenGenerationService tokenGenerationService = injector.getInstance(ITokenGenerationService.class);
        String serverToken = tokenGenerationService.generateServerToken();
        assertNotNull(serverToken);
        ITokenVerificationService tokenVerificationService = injector.getInstance(ITokenVerificationService.class);
        tokenVerificationService.validateServerToken(serverToken);
    }

    @Test(expected = EkartJwtException.class)
    public void validateInvalidServerToken() {
        ITokenGenerationService tokenGenerationService = injector.getInstance(ITokenGenerationService.class);
        String serverToken = tokenGenerationService.generateServerToken();
        ITokenVerificationService tokenVerificationService = injector.getInstance(ITokenVerificationService.class);
        tokenVerificationService.validateServerToken(serverToken + "incorrectString");
    }

    private void initiatePublicPrivateKeys() {
        this.publicPrivateKeys = new PublicPrivateKeyGenerator(1024);
    }

    private void initiateAboutMe() {
        this.aboutMe = AboutMe.builder()
                .service("EkartV3")
                .version("1")
                .buildDate("Today")
                .environment("Test")
                .privateKey(PublicPrivateKeyGenerator.convertKeyToString(publicPrivateKeys.getPrivateKey()))
                .publicKey(PublicPrivateKeyGenerator.convertKeyToString(publicPrivateKeys.getPublicKey()))
                .apiHostDomain("dev.ekart.com")
                .serverTokenExpiry("1")
                .build();
    }

    private void initiateServiceInfo() {
        this.serviceInfo = ServiceInfo.builder()
                .service(aboutMe.getService())
                .buildDate(aboutMe.getBuildDate())
                .version(aboutMe.getVersion())
                .publicKey(aboutMe.getPrivateKey())
                .build();
    }

    private void initiateGoogleInjector() {
        this.injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(AboutMe.class).toInstance(aboutMe);
                bind(ITokenGenerationService.class).to(TokenGenerationService.class);
                bind(IHttpClient.class).toInstance(httpClient);
                bind(ITokenVerificationService.class).to(TokenVerificationServiceService.class);
            }
        });
    }

    private void mockObjects() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(httpClient.get(anyString())).thenReturn(Json.toJson(serviceInfo));
    }
}
