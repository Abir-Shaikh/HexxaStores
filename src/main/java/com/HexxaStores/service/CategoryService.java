package com.HexxaStores.service;

import com.HexxaStores.model.Category;
import com.HexxaStores.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    void CreateCategories(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
