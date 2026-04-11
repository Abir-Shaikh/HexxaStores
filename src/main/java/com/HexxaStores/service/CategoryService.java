package com.HexxaStores.service;

import com.HexxaStores.model.Category;
import com.HexxaStores.payload.CategoryDTO;
import com.HexxaStores.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO CreateCategories(CategoryDTO categoryDTO);

    String deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
