package com.dheeraj.ekartv3.util;

import com.dheeraj.ekartv3.exceptions.EkartJwtException;
import com.dheeraj.ekartv3.exceptions.EkartJwtExceptionType;
import com.google.common.base.Preconditions;
import io.jsonwebtoken.*;

/**
 * @author Dheeraj Reddy
 */
public class JwtHelper {

    public static String generateJwtToken(String body, String key) {
        return Jwts.builder()
                .setPayload(body)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public static void validateJwtToken(String jwt, String key) throws EkartJwtException {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt);
        } catch (ExpiredJwtException e) {
            throw new EkartJwtException(EkartJwtExceptionType.EXPIRED_JWT_EXCEPTION);
        } catch (MalformedJwtException e) {
            throw new EkartJwtException(EkartJwtExceptionType.MALFORMED_JWT_EXCEPTION);
        } catch (SignatureException e) {
            throw new EkartJwtException(EkartJwtExceptionType.JWT_SIGNATURE_EXCEPTION);
        } catch (JwtException e) {
            throw new EkartJwtException(EkartJwtExceptionType.JWT_EXCEPTION);
        }
    }

    public static String getJwtPayLoad(String jwt) {
        Preconditions.checkArgument(!(jwt == null || jwt.isEmpty()), "Jwt cannot be null and empty, value:%s", jwt);
        String[] s = jwt.split(".");
        if (s.length != 3) {
            throw new IllegalArgumentException("Invalid Jwt");
        }
        return s[1];
    }
}
