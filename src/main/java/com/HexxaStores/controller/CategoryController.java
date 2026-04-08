package com.HexxaStores.controller;

import com.HexxaStores.model.Category;
import com.HexxaStores.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> allCategories = categoryService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
        categoryService.CreateCategories(category);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Category added Successfully");
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status , HttpStatus.OK);
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category ,
                                                 @PathVariable Long categoryId){
            Category savedCategory = categoryService.updateCategory(category , categoryId);
            return ResponseEntity.ok(savedCategory);
    }

}