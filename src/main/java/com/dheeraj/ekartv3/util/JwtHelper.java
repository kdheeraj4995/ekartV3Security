package com.dheeraj.ekartv3.util;

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

    public static void validateJwtToken(String jwt, String key) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt);
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (MalformedJwtException e) {
            throw e;
        } catch (SignatureException e) {
            throw e;
        } catch (JwtException e) {
            // return Custom Invalid JWT Exception
        }
    }
}
