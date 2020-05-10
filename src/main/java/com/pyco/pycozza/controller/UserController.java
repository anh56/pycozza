package com.pyco.pycozza.controller;

import com.pyco.pycozza.api.UserApi;
import com.pyco.pycozza.api.model.*;
import com.pyco.pycozza.model.User;
import com.pyco.pycozza.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;


@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS})
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);



    @Override
    public ResponseEntity<UserResponseModel> checkUser(@Valid CheckUserRequest checkUserRequest) {
//        User user = modelMapper.map(checkUserRequest, User.class);

        User userByEmail = userService.getUserByEmail(checkUserRequest.getEmail());
        if (userByEmail != null){
   //         if(userByEmail.getPassword().equals(passwordEncoder.matches(user.getPassword() ))){
            if(passwordEncoder.matches(checkUserRequest.getPassword(), userByEmail.getPassword())){
                UserResponseModel userResponse = modelMapper.map(userByEmail, UserResponseModel.class);
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            }
            else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> addUser(@Valid CreateUserRequest createUserRequest) {
        User user = modelMapper.map(createUserRequest, User.class);
        User persistUser = userService.createUser(user);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        if (persistUser != null){
            result.setId(persistUser.getId().toString());
            result.setStatus(HttpStatus.CREATED.value());
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        else {
            result.setId(null);
            result.setStatus(HttpStatus.CONFLICT.value());
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
    }


    @Override
    public ResponseEntity<UserResponseModelWithToken> loginUser(@Valid CheckUserRequest checkUserRequest) {

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            checkUserRequest.getEmail(), checkUserRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = generateToken(authentication);

            UserResponseModelWithToken userResponseModelWithToken = modelMapper.map(
                    userService.getUserByEmail(checkUserRequest.getEmail()),UserResponseModelWithToken.class);

            userResponseModelWithToken.setToken(token);

            return new ResponseEntity<UserResponseModelWithToken>(userResponseModelWithToken, HttpStatus.OK);
        }
        catch (AuthenticationException e){
            e.printStackTrace();
        }
        return new ResponseEntity("Wrong user name or passwrod", HttpStatus.BAD_REQUEST);
    }



    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody CheckUserRequest checkUserRequest){
//    logger.info("User trying to log in");
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
            logger.info("Wrong user name or password");
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
