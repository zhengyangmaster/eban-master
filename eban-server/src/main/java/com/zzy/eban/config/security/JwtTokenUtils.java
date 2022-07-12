package com.zzy.eban.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/2/21
 */
@Component
public class JwtTokenUtils {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     * @description: 根据用户信息生成token
     * @author: zzy
     * @data: 2022/2/21 20:32
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * @description: 根据Token获取用户名
     * @author: zzy
     * @data: 2022/2/22 8:58
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * @description: 检查获取的Token进行校验是否有效
     * @author: zzy
     * @data: 2022/2/22 15:57
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String nameFromToken = getUserNameFromToken(token);
        return nameFromToken.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * @description: 判断token是否能被刷新，是否过期进行判断
     * @author: zzy
     * @data: 2022/2/22 16:08
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * @description: 刷新token
     * @author: zzy
     * @data: 2022/2/22 16:10
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * @description: 根据荷载生成Jwt  TOKEN
     * @author: zzy
     * @data: 2022/2/22 8:54
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @description: 根据Token获取Jwt中负载
     * @author: zzy
     * @data: 2022/2/22 14:57
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * @description: 校验token是否过期
     * @author: zzy
     * @data: 2022/2/22 16:04
     */
    private boolean isTokenExpired(String token) {
        Date date = getExpiredDateFromToken(token);
        return date.before(new Date());
    }

    /**
     * @description: 从token中获取过期时间
     * @author: zzy
     * @data: 2022/2/22 16:05
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    /**
     * @description: 生成token的失效时间
     * @author: zzy
     * @data: 2022/2/22 8:56
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
