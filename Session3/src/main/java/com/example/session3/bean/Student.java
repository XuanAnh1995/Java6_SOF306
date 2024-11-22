package com.example.session3.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student {

    private String email;

    private String fullname;

    private Double marks;

    private Boolean gender;

    private String country;

}