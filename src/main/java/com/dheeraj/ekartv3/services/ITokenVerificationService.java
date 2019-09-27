package com.dheeraj.ekartv3.services;

/**
 * @author Dheeraj Reddy
 */
public interface ITokenVerificationService {
    void validateServerToken(String jwtToken);
}
