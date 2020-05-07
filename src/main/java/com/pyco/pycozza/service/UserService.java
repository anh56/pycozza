package com.pyco.pycozza.service;


import com.pyco.pycozza.model.User;
import com.pyco.pycozza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> getUserByPage(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return userRepository.findAll(pageable);
    }


    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User createUser(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            return null;
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);

        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    public Authentication login(UserLogin userLogin) {
//        //Authentication authentication = null;
//        try{
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            userLogin.getEmail(), userLogin.getPassword()));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String token = generateToken(authentication);
//            return new ResponseEntity<String>(token, HttpStatus.OK);
//        }
//        catch (AuthenticationException e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<String>(
//                "Wrong user name or password", HttpStatus.BAD_REQUEST);
//    }

//    public Authentication login(String email, String password){
//        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
//    }
//
//
//    private String generateToken(Authentication authentication){
//        final String JWT_SECRET = "pycozza-by-Hieu-Binh-Anh";
//        final long JWT_EXPIRATION = 864000000L;
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        String token = Jwts.builder().
//                setSubject(userDetails.getUsername())
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.ES256, JWT_SECRET)
//                .compact();
//        return token;
//    }
}
