package com.example.biedronka_saver.security;

import com.example.biedronka_saver.service.implementation.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final ObjectMapper objectMapper;


    private static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/api/v1/auth/signin",
            "/api/v1/auth/register"
    );
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String path = request.getServletPath();
        log.info("Processing request for path : {}", path);

        if(PUBLIC_ENDPOINTS.contains(path)){
            filterChain.doFilter(request,response);
            return;
        }

        try{
            String header = request.getHeader("Authorization");
            String token = null;
            String username = null;

            if(header != null && header.startsWith("Bearer ")){
                token = header.substring(7);
                username = jwtUtil.getUsernameFromToken(token);
            } else {
                handleErrorAndAccessDenied(response, HttpStatus.UNAUTHORIZED, "No auth token provided.");
                return;
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                if(jwtUtil.validateToken(token)){
                    UserDetails userDetails = userService.loadUserByUsername(username);

                    if(userDetails != null && userDetails.getAuthorities() != null){
                        log.info("User permissions {}: {}", username, userDetails.getAuthorities());
                    }

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    handleErrorAndAccessDenied(response, HttpStatus.UNAUTHORIZED, "Invalid token.");
                    return;
                }
            } else {
                handleErrorAndAccessDenied(response, HttpStatus.FORBIDDEN,"Lack of permissions for user: " + username + ".");
                return;
            }
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            log.error("Error in JWTAuthFilter: {}", e.getMessage(), e);
            SecurityContextHolder.clearContext();
            handleErrorAndAccessDenied(response, HttpStatus.UNAUTHORIZED, "Authentication failed.");
        }
    }

    private void handleErrorAndAccessDenied(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(status.value());
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        Map.of("status","fail","message",message))
        );
    }
}
