package com.example.demo.session1.lambda.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor

public class Product {

    private String id;

    private String name;

    private Double price;

}
