package com.example.demo.session1.jackson2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student2 {

    private String name;

    private Boolean gender = false;

    private Double marks = 0.0;

    private Contact contact;

    private List<String> subjects;

}
