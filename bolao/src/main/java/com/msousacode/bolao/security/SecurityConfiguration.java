package com.msousacode.bolao.security;

import com.msousacode.bolao.security.filter.AwsCognitoJwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private AwsCognitoJwtAuthFilter awsCognitoJwtAuthenticationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.headers().cacheControl();//TODO Verificar o que isso faz.

        return http.csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("**/health").permitAll()
                .antMatchers("/api/**").authenticated()
                //.anyRequest().authenticated()
                .and()
                .addFilterBefore(awsCognitoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
