package com.HexxaStores.service;

import com.HexxaStores.payload.CategoryDTO;
import com.HexxaStores.payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber , Integer pageSize);
    CategoryDTO CreateCategories(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
