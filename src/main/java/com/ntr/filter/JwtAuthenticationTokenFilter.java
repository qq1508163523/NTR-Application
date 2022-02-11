package com.ntr.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ntr.api.CommonResult;
import com.ntr.util.IOUtil;
import com.ntr.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }

        Map<String,Object> map ;
        try{
            map = JwtUtil.verifyToken(token);
        }catch(JWTVerificationException e){
            IOUtil.sendResponse(response, CommonResult.failed("JWT token invalid"));
            return;
        }

        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+map.get("role"));

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(map.get("username"),null,auths);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
