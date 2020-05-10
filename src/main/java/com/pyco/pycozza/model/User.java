package com.pyco.pycozza.model;


import com.querydsl.core.annotations.QueryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@QueryEntity
@Document(collection = "users")
public class User {

    @Id
    @Getter
    private ObjectId id;

    @Getter
    @Setter
    private String fullName;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
//    @Indexed(unique=true)
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    private  Collection<? extends GrantedAuthority> authorities;


    public User(String fullName, String phone,
                String address, String email,
                String password, Collection<? extends GrantedAuthority> authorities) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public User(String fullName, String phone, String address, String email, String password) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
