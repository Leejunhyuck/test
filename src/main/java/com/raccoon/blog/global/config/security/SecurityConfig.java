package com.raccoon.blog.global.config.security;

import com.raccoon.blog.global.config.jwt.CustomAccessDeniedHandler;
import com.raccoon.blog.global.config.jwt.CustomAuthenticationEntryPoint;
import com.raccoon.blog.global.config.jwt.JwtAuthenticationFilter;
import com.raccoon.blog.global.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@Log
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/*/*/user/signin", "/*/*/signin/**", "/*/*/user/signup", "/*/*/signup/**", "/*/social/**","/*/board/**", "/*/*/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/exception/**", "/helloworld/**").permitAll()
                .anyRequest().hasRole("MANAGER")
                .and().exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()).and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        log.info("build Auth global........");

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

}