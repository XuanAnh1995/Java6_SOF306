package com.example.session2thymeleaf.controller;

import com.example.session2thymeleaf.bean.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @RequestMapping("/student")
    public String student(Model model,
                          @RequestParam("index")Optional<Integer> index
                          ) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = "D:\\Java6_SOF306\\Session2\\src\\main\\resources\\static\\images\\students.json";
        TypeReference<List<Student>> type = new TypeReference<List<Student>>() {};
        List<Student> list = objectMapper.readValue(new File(path), type);

        Student student = list.get(index.orElse(0));
        model.addAttribute("sv", student);
        model.addAttribute("index", index.orElse(0));

        return "student";
    }


}
