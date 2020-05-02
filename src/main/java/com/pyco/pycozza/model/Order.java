package com.pyco.pycozza.model;


import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@QueryEntity
@Document(collection = "orders")
public class Order {

    @Id
    @Getter
    private ObjectId _id;

    @Getter
    @Setter
    @DBRef
    private Product product ;

    @Getter
    @Setter
    @DBRef
    private User user ;

    @Getter
    @Setter
    private int totalPrice;







}
