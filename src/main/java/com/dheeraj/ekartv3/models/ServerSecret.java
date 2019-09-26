package com.dheeraj.ekartv3.models;

import com.dheeraj.ekartv3.enums.TOKEN;
import lombok.Builder;

/**
 * @author Dheeraj Reddy
 */

@Builder
public class ServerSecret {
    private TOKEN token;
    private String context;
}
