package com.dheeraj.ekartv3.controllers;

import com.dheeraj.ekartv3.helper.Constant;
import com.dheeraj.ekartv3.models.AboutMe;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/**
 * @author Dheeraj Reddy
 */
public class PublicKeyController extends Controller {

    private AboutMe aboutMe;

    @Inject
    public PublicKeyController(AboutMe aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Result aboutMe(Http.Request request) {
        return ok(getAboutMe());
    }

    private ObjectNode getAboutMe() {
        ObjectNode aboutMe = Json.newObject();
        aboutMe.put(Constant.SERVICE, this.aboutMe.getService());
        aboutMe.put(Constant.VERSION, this.aboutMe.getVersion());
        aboutMe.put(Constant.BUILD_DATE, this.aboutMe.getBuildDate());
        aboutMe.put(Constant.PUBLIC_KEY, this.aboutMe.getPublicKey());
        return aboutMe;
    }

}
