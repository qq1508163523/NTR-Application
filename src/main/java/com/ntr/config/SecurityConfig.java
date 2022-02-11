package com.ntr.config;

import com.ntr.filter.JwtAuthenticationTokenFilter;
import com.ntr.handler.ApiAccessDeniedHandler;
import com.ntr.handler.LoginAuthenticationEntryPointHandler;
import com.ntr.handler.LoginFailHandler;
import com.ntr.handler.LoginSuccessHandler;
import com.ntr.service.impl.auth.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl userDetailService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailHandler loginFailHandler;
    private final LoginAuthenticationEntryPointHandler loginAuthenticationEntryPointHandler;
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private final ApiAccessDeniedHandler apiAccessDeniedHandler;

    public SecurityConfig(UserDetailServiceImpl userDetailService,
                          LoginSuccessHandler loginSuccessHandler,
                          LoginFailHandler loginFailHandler,
                          LoginAuthenticationEntryPointHandler loginAuthenticationEntryPointHandler,
                          JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter,
                          ApiAccessDeniedHandler apiAccessDeniedHandler) {
        this.userDetailService = userDetailService;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailHandler = loginFailHandler;
        this.loginAuthenticationEntryPointHandler = loginAuthenticationEntryPointHandler;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.apiAccessDeniedHandler = apiAccessDeniedHandler;
    }

    // password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS))
                .and()
                .formLogin()
                .loginProcessingUrl("/api/v1/auth/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler)
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(loginAuthenticationEntryPointHandler)
                .accessDeniedHandler(apiAccessDeniedHandler);

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors(); // enable cross site
    }
}
