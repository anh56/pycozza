package com.pyco.pycozza.service;

import com.pyco.pycozza.dto.CustomUserDetails;
import com.pyco.pycozza.model.User;
import com.pyco.pycozza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.core.userdetails.User.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) throw new UsernameNotFoundException("No user found with email "+ email);

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        // for simplicity, all users' roles are CUSTOMER
            authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));

            return withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
    }
}
