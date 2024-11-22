package com.example.session2thymeleaf.controller;

import com.example.session2thymeleaf.bean.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping("/home/index")
    public String index(Model model) throws Exception {
        model.addAttribute("message", "Wellcome to Thymeleaf");

        ObjectMapper objectMapper = new ObjectMapper();
        String path = "D:\\Java6_SOF306\\Session2\\src\\main\\resources\\static\\images\\student.json";
        Student student = objectMapper.readValue(new File(path), Student.class);
        model.addAttribute("sv", student);

        return "home/index";
    }

}
