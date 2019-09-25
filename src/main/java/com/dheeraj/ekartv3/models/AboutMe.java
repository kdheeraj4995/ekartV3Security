package com.dheeraj.ekartv3.models;

import com.dheeraj.ekartv3.helper.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.typesafe.config.Config;

/**
 * @author Dheeraj Reddy
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AboutMe {

    @JsonIgnore
    private Config config;
    private String service;
    private String version;
    private String buildDate;
    private String environment;
    private String publicKey;
    @JsonIgnore
    private String privateKey;
    @JsonIgnore
    private String apiHostDomain;

    @Inject
    public AboutMe(Config config) {
        this.config = config;
        this.service = Preconditions.checkNotNull(config.getString("service.name"), "Service has to be set");
        this.version = Preconditions.checkNotNull(config.getString("service.version"), "Version has to be set");
        this.buildDate = Preconditions.checkNotNull(config.getString("service.buildDate"), "Build date has to be set");
        this.publicKey = Preconditions.checkNotNull(config.getString("service.publicKey"), "Public key has to be set");
        this.privateKey = Preconditions.checkNotNull(config.getString("service.privateKey"), "Private key has to be set");
        this.environment = Preconditions.checkNotNull(config.getString("service.environment"), "Environment has to be set");
        this.apiHostDomain = Preconditions.checkNotNull(config.getString("service.apiHostDomain"), "Api host domain has to be set");
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getApiHostDomain() {
        return apiHostDomain;
    }

    public void setApiHostDomain(String apiHostDomain) {
        this.apiHostDomain = apiHostDomain;
    }
}
