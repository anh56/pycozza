package com.pyco.pycozza.model;


import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@QueryEntity
@Document(collection = "users")
public class User {

    @Id
    @Getter
    private ObjectId id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String phone;


    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String email;


}
