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
    private ObjectId _id;

    @Getter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int categoryId;

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

    public Product(int id, String name, int categoryId, String imgLink, String crust, String size, String description, int price) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.imgLink = imgLink;
        this.crust = crust;
        this.size = size;
        this.description = description;
        this.price = price;
    }

    public Product(int id, String name, int categoryId, String imgLink, int price) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.imgLink = imgLink;
        this.price = price;
    }

    public Product(){}
}
