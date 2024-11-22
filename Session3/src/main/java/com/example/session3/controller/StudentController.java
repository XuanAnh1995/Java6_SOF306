package com.example.session3.controller;

import com.example.session3.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @GetMapping("/student/form")
    public String form(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/form";
    }

    @PostMapping("/student/save")
    public String save(@ModelAttribute("student") Student form) {
        return "student/success";
    }

}