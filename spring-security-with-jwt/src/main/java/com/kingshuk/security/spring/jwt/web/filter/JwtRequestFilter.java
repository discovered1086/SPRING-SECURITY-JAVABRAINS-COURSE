package com.kingshuk.security.spring.jwt.web.filter;

import com.kingshuk.security.spring.jwt.utility.JWTHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JWTHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String jwt = null;
        String userName = null;

        //We need to check if the header actually has a token. That's why we check if it starts with 'Bearer'
        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);

            //Then we use the token to get the username
            userName = jwtHelper.extractUserName(jwt);
        }


        //Then we check if the SecurityContextHolder actually has any Authentication object or
        //if not that means this is the first request post-authentication
        if (Objects.nonNull(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            //Check if the token is valid
            if (jwtHelper.validateToken(jwt, userDetails)) {

                //The token is valid so create the Authentication object now
                //UsernamePasswordAuthenticationToken is an implementation of the Authentication class
                //In the context of username/password based authentication
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                //
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Finally we set the authentication object in the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }

        filterChain.doFilter(request, response);
    }
}
