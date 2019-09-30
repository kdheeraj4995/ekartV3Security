package com.dheeraj.ekartv3.models;

import com.dheeraj.ekartv3.enums.TOKEN;
import lombok.*;

/**
 * @author Dheeraj Reddy
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServerSecret {
    private TOKEN token;
    private String context;
}
