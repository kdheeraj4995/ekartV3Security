package com.dheeraj.ekartv3.models;

import lombok.*;

/**
 * @author Dheeraj Reddy
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInfo {
    private String service;
    private String version;
    private String buildDate;
    private String publicKey;
}
