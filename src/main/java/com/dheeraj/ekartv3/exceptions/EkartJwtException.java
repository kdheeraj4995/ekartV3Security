package com.dheeraj.ekartv3.exceptions;

/**
 * @author Dheeraj Reddy
 */
public class EkartJwtException extends RuntimeException {
    public EkartJwtException(EkartJwtExceptionType type) {
        super(type.toString());
    }
}
