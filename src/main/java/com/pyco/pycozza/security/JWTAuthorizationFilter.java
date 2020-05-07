package com.pyco.pycozza.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService _userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserDetailsService userDetailsService ){
        super(authenticationManager);
        _userDetailsService = userDetailsService;
    }

    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
    throws IOException , ServletException {
        final String JWT_SECRET = "pycozza-by-Hieu-Binh-Anh";
        // fetch token from header
        String tokenBearer = req.getHeader("Authorization");

        if (tokenBearer!= null && tokenBearer.startsWith("Bearer")){
            String token = tokenBearer.replace("Bearer", "");
            tokenBearer.trim();
            String email = Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token).getBody().getSubject();

            //get user from db
            UserDetails userDetails = _userDetailsService.loadUserByUsername(email);
            // valid user will be allow in the context
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(req, res);
    }
}
