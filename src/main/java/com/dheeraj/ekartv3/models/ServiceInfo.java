package com.dheeraj.ekartv3.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dheeraj Reddy
 */

@Getter
@Setter
public class ServiceInfo {
    private String service;
    private String version;
    private String buildDate;
    private String publicKey;
}
