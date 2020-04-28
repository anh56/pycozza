package com.pyco.pycozza.model;


import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@QueryEntity
@Document(collection = "orders")
public class Order {

    @Id
    @Getter
    private ObjectId _id;

    @Getter
    private int id;


    @Getter
    @Setter
    private int userId ;

    @Getter
    @Setter
    @DBRef
    private Product product ;

    @Getter
    @Setter
    private int quantity ;

    @Getter
    @Setter
    private Date createdDate = new Date() ;

    @Getter
    @Setter
    private int totalPrice;







}
