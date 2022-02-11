package com.ntr.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {

    private static final String SIGNATURE = "!EASYGIRLGODEATH&&GETOUTOFMYCOUNTRY!";

    // day
    private static final Integer TOKEN_LIFE_DURATION = 3;

    public static String generateToken(Map<String,Object> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,TOKEN_LIFE_DURATION);
        return JWT.create().withClaim("map",map)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGNATURE));
    }


    public static Map<String,Object> verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGNATURE)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify.getClaim("map").asMap();
    }
}
