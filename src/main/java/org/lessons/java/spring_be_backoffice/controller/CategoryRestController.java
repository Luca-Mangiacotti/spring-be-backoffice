package org.lessons.java.spring_be_backoffice.controller;

import java.util.List;

import org.lessons.java.spring_be_backoffice.model.Category;
import org.lessons.java.spring_be_backoffice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> index() {
        return categoryService.findAll();
    }
}