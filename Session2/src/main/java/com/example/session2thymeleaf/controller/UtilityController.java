package com.example.session2thymeleaf.controller;

import com.example.session2thymeleaf.bean.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class UtilityController {

    @RequestMapping("/demo/utilities")
    public String utilities(Model model) throws Exception {
        File file = new ClassPathResource("static/images/students.json").getFile();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Student>> typeReference = new TypeReference<List<Student>>() {};
        List<Student> list_Student = objectMapper.readValue(file, typeReference);

        model.addAttribute("list_Student", list_Student);
        model.addAttribute("now", new Date());

        return "demo/utilities";
    }
}
