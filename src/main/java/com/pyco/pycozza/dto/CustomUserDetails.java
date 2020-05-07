package com.pyco.pycozza.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails extends User implements UserDetails {

    public CustomUserDetails(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
    }
//    private static long final serialVersionUID = 1;


//    @Override
//    public Collection<GrantedAuthority> getAuthorities() {
//        return null;
//    }

//    @Override
//    public String getUsername() {
//        return getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

//    public CustomUserDetail(String email, String password,
//                            Collection<? extends GrantedAuthority> authorities){
//        super(email, password, authorities);
//    }

//    public CustomUserDetail(String fullName, String phone,
//                            String email, String password,
//                            String address,
//                            Collection<? extends GrantedAuthority> authorities) {
//        super(fullName, phone, email, password, address, authorities);
//    }


//    public CustomUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }
//
//    public CustomUserDetail(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    }
}
