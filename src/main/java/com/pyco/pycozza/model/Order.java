package com.pyco.pycozza.model;


import com.pyco.pycozza.api.model.OrderProductResponseModel;
import com.querydsl.core.annotations.QueryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
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
//    @DBRef
    private List<OrderProductResponseModel> cart ;

    @Getter
    @Setter
//    @DBRef
    private User orderUserInformation ;

    @Getter
    @Setter
    private String note;

    @Getter
    @Setter
    private String paymentMethod;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String orderTime;

    @Getter
    @Setter
    private int totalPrice;







}
