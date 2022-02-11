package com.ntr.handler;

import com.ntr.api.CommonResult;
import com.ntr.entity.User;
import com.ntr.util.IOUtil;
import com.ntr.util.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal();
        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("role",user.getRole());
        String token = JwtUtil.generateToken(map);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("token",token);
        IOUtil.sendResponse(response,CommonResult.success(map2));
    }
}
