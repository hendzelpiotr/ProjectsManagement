package com.project.java.prz.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by phendzel on 4/7/2017.
 */
@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String role;

}
