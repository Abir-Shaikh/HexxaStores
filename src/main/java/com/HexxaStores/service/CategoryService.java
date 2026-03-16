package com.HexxaStores.service;

import com.HexxaStores.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void CreateCategories(Category category);

    String deleteCategory(Long categoryId);

    String updateCategory(Category category, Long categoryId);
}
