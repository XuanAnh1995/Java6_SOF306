package com.example.demo.session1.lambda.service;

import com.example.demo.session1.lambda.entity.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductService {

    static List<Product> list_Products = Arrays.asList(
            new Product("SP001", "Tivi", 10.0),
            new Product("SP002", "Tu lanh", 15.0),
            new Product("SP003", "May giat", 20.0),
            new Product("SP004", "Quat dien", 5.0),
            new Product("SP005", "Ban", 10.0)
    );

    public static void main(String[] args){
        tongSPGiaLonHon10();
        SPTheoGia();
    }

    private static void tongSPGiaLonHon10() {
        long count = list_Products.stream()
                .filter(product -> product.getPrice() > 15)
                .count();
        System.out.println(count);
    }

    private static void SPTheoGia() {
        
    }

}
