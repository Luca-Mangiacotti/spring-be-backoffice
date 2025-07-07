package org.lessons.java.spring_be_backoffice.service;

import java.util.List;

import org.lessons.java.spring_be_backoffice.model.Category;
import org.lessons.java.spring_be_backoffice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // INDEX
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // SHOW
    public Category getById(Integer id) {
        return categoryRepository.findById(id).get();
    }

    // CREATE
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    // UPDATE
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    // DELETE BY ID
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    // DELETE
    public void delete(Category category) {
        categoryRepository.deleteById(category.getId());
    }

}
