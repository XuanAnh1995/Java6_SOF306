package com.example.session4.controller;

import com.example.session4.bean.Student;
import com.example.session4.bean.StudentMap;
import com.example.session4.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping("/student/index")
    public String index(Model model){
        Student student = new Student("", "", 0.0, true, "VN");
        model.addAttribute("form", student);
        StudentMap studentMap = studentDAO.findAll();
        model.addAttribute("items", studentMap);
        return "student/index";
    }

    @RequestMapping("/student/edit/{key}")
    public String edit(Model model, @PathVariable("key") String key){
        model.addAttribute("key", key);
        Student student = studentDAO.findByKey(key);
        model.addAttribute("form", student);
        StudentMap studentMap = studentDAO.findAll();
        model.addAttribute("items", studentMap);
        return "student/index";
    }

    @RequestMapping("/student/update/{key}")
    public String update(@PathVariable("key") String key, Student student){
        studentDAO.update(key, student);
        return "riderect:/student/edit" + key;
    }

    @RequestMapping("/student/create")
    public String create(Student student){
        studentDAO.create(student);
        return "redirect:/student/index";
    }

    @RequestMapping("/student/delete/{key}")
    public String delete(@PathVariable("key") String key){
        studentDAO.delete(key);
        return "redirect:/student/index";
    }

}
