package com.example.session5.controller;

import com.example.session5.entity.Student;
import com.example.session5.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/rest/students")
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    @GetMapping("/rest/students/{email}")
    public Student getOne(@PathVariable("email") String email){
        return studentRepository.findById(email).get();
    }

    @PostMapping("/rest/students")
    public Student post(@RequestBody Student student){
        studentRepository.save(student);
        return student;
    }

    @PutMapping("/rest/students/{email}")
    public Student put(@PathVariable("email") String email,@RequestBody Student student){
        studentRepository.save(student);
        return student;
    }

    @DeleteMapping("/rest/students/{email}")
    public void delete(@PathVariable("email") String email){
        studentRepository.deleteById(email);
    }
}
