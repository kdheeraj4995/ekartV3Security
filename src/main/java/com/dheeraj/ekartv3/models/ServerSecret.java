package com.dheeraj.ekartv3.models;

import com.dheeraj.ekartv3.enums.TOKEN;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dheeraj Reddy
 */

@Builder
@Getter
@Setter
public class ServerSecret {
    private TOKEN token;
    private String context;
}
