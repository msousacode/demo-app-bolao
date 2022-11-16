package com.msousacode.bolao.security.filter;

import com.msousacode.bolao.security.cognito.CognitoIdTokenProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AwsCognitoJwtAuthFilter extends GenericFilter {

    private static final Logger logger = LoggerFactory.getLogger(AwsCognitoJwtAuthFilter.class);

    @Autowired
    private CognitoIdTokenProcessor awsCognitoIdTokenProcessor;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        Authentication authentication;
        try {
            authentication = this.awsCognitoIdTokenProcessor.authenticate((HttpServletRequest) request);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception var6) {
            logger.error("Cognito ID Token processing error", var6);
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
