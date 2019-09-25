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
        this.version = Preconditions.checkNotNull(config.getString("service.version"), "Service has to be version");
        this.buildDate = Preconditions.checkNotNull(config.getString("service.buildDate"), "Service has to be buildDate");
        this.publicKey = Preconditions.checkNotNull(config.getString("service.publicKey"), "Service has to be publicKey");
        this.privateKey = Preconditions.checkNotNull(config.getString("service.privateKey"), "Service has to be privateKey");
        this.environment = Preconditions.checkNotNull(config.getString("service.environment"), "Service has to be environment");
        this.apiHostDomain = Preconditions.checkNotNull(config.getString("service.apiHostDomain"), "Service has to be apiHostDomain");
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
