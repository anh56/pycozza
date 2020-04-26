package com.pyco.pycozza.model;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@QueryEntity
@Document(collection = "products")
public class Product {

    @Id
    @Getter
    private ObjectId id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @DBRef
    private Category category;

    @Getter
    @Setter
    private String imgLink;

    /*TODO: make crust and size optional since only pizza have these 2 attributes*/
    @Getter
    @Setter
    private String crust;

    @Getter
    @Setter
    private String size;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int price;

}
