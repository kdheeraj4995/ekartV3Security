package com.dheeraj.ekartv3.controllers;

import com.dheeraj.ekartv3.helper.Constant;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Preconditions;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/**
 * @author Dheeraj Reddy
 */
public class PublicKeyController extends Controller {

    private String publicKey;
    private String service;
    private String buildDate;
    private String version;

    public PublicKeyController() {
        service = Preconditions.checkNotNull(getEnvironmentVariable(Constant.SERVICE), "Service has to be set");
        version = Preconditions.checkNotNull(getEnvironmentVariable(Constant.VERSION), "Version has to be set");
        buildDate = Preconditions.checkNotNull(getEnvironmentVariable(Constant.BUILD_DATE), "build date has to be set");
        publicKey = Preconditions.checkNotNull(getEnvironmentVariable(Constant.PUBLIC_KEY), "Public key has to set");
    }

    public Result aboutMe(Http.Request request) {
        return ok(getAboutMe());
    }

    public ObjectNode getAboutMe() {
        ObjectNode aboutMe = Json.newObject();
        aboutMe.put(Constant.SERVICE, service);
        aboutMe.put(Constant.VERSION, version);
        aboutMe.put(Constant.BUILD_DATE, buildDate);
        aboutMe.put(Constant.PUBLIC_KEY, publicKey);
        return aboutMe;
    }

    private String getEnvironmentVariable(String variableName) {
        try {
            return System.getenv(variableName);
        } catch (Exception e) {
            return null;
        }
    }
}
