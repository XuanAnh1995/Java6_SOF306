package com.example.demo.session1.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Jackson1 {

    public static void main(String[] args) throws Exception {

        demo1();

        System.out.println("----------------------------------------------");

        demo2();

    }

    private static void demo2() throws Exception {

        String path = "C:\\\\Users\\\\luuvi\\\\Desktop\\\\src\\\\main\\\\resources\\\\session1\\\\students.json";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode students = objectMapper.readTree(new File(path));

        students.iterator().forEachRemaining(student -> {
            System.out.println(">> Name: " + student.get("name").asText());
        });
    }

    private static void demo1() throws Exception {

        String path = "C:\\\\Users\\\\luuvi\\\\Desktop\\\\src\\\\main\\\\resources\\\\session1\\\\student.json";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode student = objectMapper.readTree(new File(path));

        System.out.println(">> Name: " + student.get("name").asText());
        System.out.println(">> Gender: " + (student.get("gender").asBoolean() ? "Nam" : "Nu"));
        System.out.println(">> Marks: " + student.get("marks").asDouble());
        System.out.println(">> Email: " + student.get("contact").get("email").asText());
        System.out.println(">> Phone: " + student.get("contact").get("phone").asText());

        student.get("subjects").iterator().forEachRemaining(subject -> {
            System.out.println(">> Subject: " + subject.asText());
        });
    }

}
