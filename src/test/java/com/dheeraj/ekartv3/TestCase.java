package com.dheeraj.ekartv3;

import static org.junit.Assert.assertTrue;

import com.dheeraj.ekartv3.models.AboutMe;
import com.dheeraj.ekartv3.models.ServiceInfo;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestCase {
    private AboutMe aboutMe;
    private ServiceInfo serviceInfo;

    @Before
    void init() {
        setAboutMe();
    }

    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    void setAboutMe() {
        aboutMe = AboutMe.builder()
                .service("EkartV3")
                .version("1")
                .buildDate("Today")
                .environment("Test")
                .privateKey("")
                .publicKey("")
                .apiHostDomain("dev.ekart.com")
                .build();
    }
}
