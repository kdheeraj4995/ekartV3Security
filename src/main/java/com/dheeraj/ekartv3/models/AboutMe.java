package com.dheeraj.ekartv3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dheeraj Reddy
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AboutMe {

    private String service;
    private String version;
    private String buildDate;
    private String environment;
    private String publicKey;
    @JsonIgnore
    private String privateKey;
    @JsonIgnore
    private String apiHostDomain;
    @JsonIgnore
    private String serverTokenExpiry;

    public AboutMe() {
        this.service = Preconditions.checkNotNull(
                getEnvironmentVariable("service.name"),
                "Service has to be set"
        );
        this.version = Preconditions.checkNotNull(
                getEnvironmentVariable("service.version"),
                "Version has to be set"
        );
        this.buildDate = Preconditions.checkNotNull(
                getEnvironmentVariable("service.buildDate"),
                "Build date has to be set"
        );
        this.publicKey = Preconditions.checkNotNull(
                getEnvironmentVariable("service.publicKey"),
                "Public key has to be set"
        );
        this.privateKey = Preconditions.checkNotNull(
                getEnvironmentVariable("service.privateKey"),
                "Private key has to be set"
        );
        this.environment = Preconditions.checkNotNull(
                getEnvironmentVariable("service.environment"),
                "Environment has to be set"
        );
        this.apiHostDomain = Preconditions.checkNotNull(
                getEnvironmentVariable("service.apiHostDomain"),
                "Api host domain has to be set"
        );
        this.serverTokenExpiry = Preconditions.checkNotNull(
                getEnvironmentVariable("service.serverTokenExpiry"),
                "Server token expiry has to be set"
        );
    }

    private String getEnvironmentVariable(String variableName) {
        try {
            return System.getenv(variableName);
        } catch (Exception e) {
            return null;
        }
    }

}
