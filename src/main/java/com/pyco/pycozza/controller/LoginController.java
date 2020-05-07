package com.pyco.pycozza.controller;

import com.pyco.pycozza.api.model.CheckUserRequest;
import com.pyco.pycozza.model.User;
import com.pyco.pycozza.service.UserService;
import com.pyco.pycozza.util.ActionAspect;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS})
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(ActionAspect.class);

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody CheckUserRequest checkUserRequest){
    logger.info("User trying to log in");
        //Authentication authentication = null;
        try{
           Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            checkUserRequest.getEmail(), checkUserRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = generateToken(authentication);
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        catch (AuthenticationException e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>(
                "Wrong user name or password", HttpStatus.BAD_REQUEST);

//        return userService.login(checkUserRequest);

    }



    private String generateToken(Authentication authentication){
        final String JWT_SECRET = "pycozza-by-Hieu-Binh-Anh";
        final long JWT_EXPIRATION = 864000000L;

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User userToDeliver = userService.getUserByEmail(userDetails.getUsername());

        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("fullName", userToDeliver.getFullName())
                .claim("phone", userToDeliver.getPhone())
                .claim("email", userToDeliver.getEmail())
                .claim("address", userToDeliver.getAddress())
//                .claim("password", userToDeliver.getPassword())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        return token;
    }
}
