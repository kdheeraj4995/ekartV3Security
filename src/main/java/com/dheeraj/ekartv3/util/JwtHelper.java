package com.dheeraj.ekartv3.util;

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
            throw e;
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
