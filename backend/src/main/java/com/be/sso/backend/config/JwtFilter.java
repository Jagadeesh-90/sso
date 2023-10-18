package com.be.sso.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {



    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String requestURI = request.getRequestURI();

        if (isExcluded(requestURI)) {
            filterChain.doFilter(request, response);
        } else {
            final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
            if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }

            final String token = authHeader.substring(BEARER_PREFIX.length());
            Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
            request.setAttribute("user", servletRequest.getParameter("id"));
            filterChain.doFilter(request, response);
        }
    }

    private boolean isExcluded(String requestURI) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return pathMatcher.match("/users/login/**", requestURI) || pathMatcher.match("/users/save/**", requestURI);
    }
}
