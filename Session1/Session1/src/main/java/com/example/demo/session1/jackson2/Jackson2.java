package com.example.demo.session1.jackson2;

import com.example.demo.session1.jackson2.entity.Student2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jackson2 {

    public static void main(String[] args) throws Exception {

        demo1();

        System.out.println("--------------------------------------------------------");

        demo2();

        System.out.println("--------------------------------------------------------");

        demo3();

        System.out.println("--------------------------------------------------------");

        demo4();

        System.out.println("--------------------------------------------------------");

        demo5();
        
        System.out.println("--------------------------------------------------------");

        demo6();

    }

    private static void demo6() throws Exception {

        Map<String, Object> contact = new HashMap<String, Object>();
        contact.put("email", "dungnv29@fpt.edu.vn");
        contact.put("phone", "0988876543");

        List<String> subjects = Arrays.asList("WEB3034", "WEB3021");

        Map<String, Object> student = new HashMap<String, Object>();
        student.put("name", "Nguyễn Văn Dũng");
        student.put("gender", true);
        student.put("marks", 10);
        student.put("contact", contact);
        student.put("subjects", subjects);

        ObjectMapper objectMapper = new ObjectMapper();

        // Write to String
        String json = objectMapper.writeValueAsString(student);
        System.out.println(json);

        // Write to output
        objectMapper.writeValue(System.out, student);

        // Write to File
        objectMapper.writeValue(new File("C:\\\\Users\\\\luuvi\\\\Desktop\\\\src\\\\main\\\\resources\\\\session1\\\\student.json"), student);


    }

    private static void demo5() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode student = objectMapper.createObjectNode();

        student.put("name", "Lưu Việt Xuân Ánh");
        student.put("gender", true);
        student.put("marks", 8.5);

        ObjectNode contact = student.putObject("contact");
        contact.put("email", "anhlvxph47179@fpt.edu.vn");
        contact.put("phone", "0988987071");
        contact.put("address", "Thái Bình");

        ArrayNode subjects = student.putArray("subjects");
        subjects.add("WEB2012");
        subjects.add("WEB2034");

        // Write to String
        String json = objectMapper.writeValueAsString(student);

        // Write to output
        objectMapper.writeValue(System.out, student);

        // Write to File
        objectMapper.writeValue(new File("C:\\\\Users\\\\luuvi\\\\Desktop\\\\src\\\\main\\\\resources\\\\session1\\\\student.json"), student);

    }

    private static void demo4() throws IOException {

        String path = "C:\\\\Users\\\\luuvi\\\\Desktop\\\\src\\\\main\\\\resources\\\\session1\\\\students.json";

        TypeReference<List<Student2>> typeReference = new TypeReference<List<Student2>>() {};
        ObjectMapper objectMapper = new ObjectMapper();
        List<Student2> student2s = objectMapper.readValue(new File(path), typeReference);

        student2s.forEach(student -> {
            System.out.println(">> Name: " + student.getName());
        });

    }

    private static void demo3() throws IOException {

        String path = "C:\\\\Users\\\\luuvi\\\\Desktop\\\\src\\\\main\\\\resources\\\\session1\\\\student.json";

        ObjectMapper objectMapper = new ObjectMapper();
        Student2 student2 = objectMapper.readValue(new File(path), Student2.class);

        System.out.println(">> Name: " + student2.getName());
        System.out.println(">> Gender: " + student2.getGender());
        System.out.println(">> Marks: " + student2.getMarks());

        System.out.println(">> Email: " + student2.getContact().getEmail());
        System.out.println(">> Phone: " + student2.getContact().getPhone());

        List<String> subjects = student2.getSubjects();
        subjects.forEach(subject -> {
            System.out.println(">> Subject: " + subject);
        });
    }

    private static void demo2() throws IOException {

        String path = "C:\\\\Users\\\\luuvi\\\\Desktop\\\\src\\\\main\\\\resources\\\\session1\\\\students.json";

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> students = objectMapper.readValue(new File(path), List.class);

        students.forEach(student -> {
            System.out.println(">> Name: " + student.get("name"));
        });
    }

    private static void demo1() throws Exception {

        String path = "C:\\\\Users\\\\luuvi\\\\Desktop\\\\src\\\\main\\\\resources\\\\session1\\\\student.json";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> student = objectMapper.readValue(new File(path), Map.class);

        System.out.println(">> Name: " + student.get("name"));
        System.out.println(">> Gender: " + (student.get("gender")));
        System.out.println(">> Marks: " + student.get("marks"));

        Map<String, Object> contact = (Map<String, Object>) student.get("contact");
        System.out.println(">> Email: " + contact.get("email"));
        System.out.println(">> Phone: " + contact.get("phone"));

        List<String> subjects = (List<String>) student.get("subjects");
        subjects.forEach(subject -> {
            System.out.println(">> Subject: " + subject);
        });

    }

}
