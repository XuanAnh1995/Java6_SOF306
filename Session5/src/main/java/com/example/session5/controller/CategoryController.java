package com.example.session5.controller;

import com.example.session5.entity.Category;
import com.example.session5.repository.CategoryRepository;
import org.eclipse.tags.shaded.org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/rest/categories")
    public ResponseEntity<List<Category>> getAll(Model model){
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/rest/categories/{id}")
    public ResponseEntity<Category> getOne(@PathVariable("id") String id){
        if (!categoryRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryRepository.findById(id).get());
    }

    @PostMapping("/rest/categories")
    public ResponseEntity<Category> post(@RequestBody Category category){
        if (categoryRepository.existsById(category.getId())){
            return ResponseEntity.badRequest().build();
        }
        categoryRepository.save(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/rest/categories/{id}")
    public ResponseEntity<Category> put(@PathVariable("id") String id, @RequestBody Category category){
        if (!categoryRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        categoryRepository.save(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/rest/categories/{id}")
    public ResponseEntity<Category> put(@PathVariable("id") String id){
        if (!categoryRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
