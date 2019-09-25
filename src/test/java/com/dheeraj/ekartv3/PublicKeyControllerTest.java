package com.dheeraj.ekartv3;

import com.dheeraj.ekartv3.controllers.PublicKeyController;
import com.dheeraj.ekartv3.helper.Constant;
import com.dheeraj.ekartv3.util.PublicPrivateKeyGenerator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PublicKeyControllerTest {

    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @Before
    public void init() {
        setEnvironmentVariables();
    }

    @Test
    public void shouldAnswerWithTrue() {
        PublicKeyController publicKeyController = new PublicKeyController();
        ObjectNode aboutMe = publicKeyController.getAboutMe();
        Assert.assertTrue(aboutMe.hasNonNull(Constant.SERVICE));
        Assert.assertTrue(aboutMe.hasNonNull(Constant.VERSION));
        Assert.assertTrue(aboutMe.hasNonNull(Constant.BUILD_DATE));
        Assert.assertTrue(aboutMe.hasNonNull(Constant.PUBLIC_KEY));
    }

    public EnvironmentVariables setEnvironmentVariables() {
        environmentVariables.set(Constant.SERVICE, "ekart");
        environmentVariables.set(Constant.VERSION, "23");
        environmentVariables.set(Constant.BUILD_DATE, "Will build in future");
        environmentVariables.set(Constant.PUBLIC_KEY, getPublicKey());
        return environmentVariables;
    }

    public String getPublicKey() {
        PublicPrivateKeyGenerator publicPrivateKeyGenerator = new PublicPrivateKeyGenerator(1024);
        return PublicPrivateKeyGenerator.convertKeyToString(publicPrivateKeyGenerator.getPublicKey());
    }

}
