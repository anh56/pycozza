package com.pyco.pycozza;


import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;




@SpringBootApplication
public class PycozzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PycozzaApplication.class, args);

        MongoClient mongo = new MongoClient("localhost", 27017);
        System.out.println("Connected to the database successfully");
        MongoDatabase database = mongo.getDatabase("pycozza");

        try {
            database.createCollection("users");
            database.createCollection("categories");
            database.createCollection("products");
            database.createCollection("orders");
            System.out.println("Collections users, categories, products, orders created successfully");
        } catch (MongoCommandException e) {
            System.err.println("Collections Exists");
        }


//        MongoCollection<Document> usersCollection = database.getCollection("users");
//        MongoCollection<Document> categoriesCollection = database.getCollection("categories");
//        MongoCollection<Document> productsCollection = database.getCollection("products");
//        MongoCollection<Document> ordersCollection = database.getCollection("orders");
//
//        //Data Insertion
//        List<Document> listUser = new ArrayList<Document>();
//        List<Document> listCategories = new ArrayList<Document>();
//        List<Document> listProduct = new ArrayList<Document>();
//        List<Document> listOrder = new ArrayList<Document>();
//
//        Document mockUser =
//                Document.parse("   {\"id\":1,\n" +
//                        "   \"name\":\"Nguyen The Anh\",\n" +
//                        "   \"phone\":829990408,\n" +
//                        "   \"address\":\"tan binh\",\n" +
//                        "   \"email\":\"anh.nguyenthe@pycogroup.com\",\n" +
//                        "   \"password\":\"\"}");
//
//        listUser.add(mockUser);
//        mockUser = Document.parse("{\t\"id\": 2,\n" +
//                "\t\"name\": \"Bui The Binh\",\n" +
//                "\t\"phone\": 28443241,\n" +
//                "\t\"address\": \"quan 7\",\n" +
//                "\t\"email\": \"binh.bui@pycogroup.com\",\n" +
//                "\t\"password\": \"\"}");
//
//        listUser.add(mockUser);
//        usersCollection.insertMany(listUser);
//
//        Document mockCategory = Document.parse(
//                "{\t\"id\": \"1\",\n" +
//                        "\t\"name\": \"Pyzza\"}");
//        listCategories.add(mockCategory);
//        mockCategory = Document.parse(
//                "{\t\"id\": \"2\",\n" +
//                        "\t\"name\": \"Side dish\"}");
//        listCategories.add(mockCategory);
//        mockCategory = Document.parse(
//                "{\t\"id\": \"3\",\n" +
//                        "\t\"name\": \"Dessert\"}");
//        listCategories.add(mockCategory);
//        mockCategory = Document.parse(
//                "{\t\"id\": \"4\",\n" +
//                        "\t\"name\": \"Beverage\"}");
//        listCategories.add(mockCategory);
//        categoriesCollection.insertMany(listCategories);
//
//        Document mockProduct = Document.parse(
//                "{\t\"id\": 1,\n" +
//                        "\t\"categoryId\": 1,\n" +
//                        "\t\"imgLink\": \"\",\n" +
//                        "\t\"name\": \"SINGAPORE STYLE SEAFOOD\",\n" +
//                        "\t\"price\": 199,\n" +
//                        "\t\"crust\": \"Thick\",\n" +
//                        "\t\"size\": \"Large\",\n" +
//                        "\t\"description\": \"Singapore style hot sauce, Mozzarella cheese, Shrimp, Crab Meat, Onion\"}");
//        listProduct.add(mockProduct);
//        mockProduct = Document.parse("{\t\"\uFEFFid\": 2,\n" +
//                "\t\"categoryId\": 1,\n" +
//                "\t\"imgLink\": \"\",\n" +
//                "\t\"name\": \"PRIME BEEF\",\n" +
//                "\t\"price\": 199,\n" +
//                "\t\"crust\": \"\",\n" +
//                "\t\"size\": \"\",\n" +
//                "\t\"description\": \"Mozzarella cheese, Pizza Sauce, Onion, Tomato, Cheese Sauce, Meat ball, Mexico Beef\"}");
//        listProduct.add(mockProduct);
//        productsCollection.insertMany(listProduct);
//
//        Document mockOrder = Document.parse("{\t\"\uFEFFid\": 1,\n" +
//                "\t\"userId\": 1,\n" +
//                "\t\"productId\": 1,\n" +
//                "\t\"quantity\": 1,\n" +
//                "\t\"createdDate\": {\n" +
//                "\t\t\"$date\": \"2020-04-05T17:00:00Z\"\n" +
//                "\t},\n" +
//                "\t\"total\": 199}");
//        ordersCollection.insertOne(mockOrder);

    }

}

