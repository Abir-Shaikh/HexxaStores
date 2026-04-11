package com.HexxaStores.service;

import com.HexxaStores.exceptions.APIException;
import com.HexxaStores.exceptions.ResourceNotFoundException;
import com.HexxaStores.model.Category;
import com.HexxaStores.payload.CategoryDTO;
import com.HexxaStores.payload.CategoryResponse;
import com.HexxaStores.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty())
            throw new APIException("No category created till now.");
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category , CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public CategoryDTO CreateCategories(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO , Category.class);
        Category categoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryFromDB != null) {
            throw new APIException("Category with the name " + category.getCategoryName() + " already exist");
        }
        Category savedCategory = categoryRepository.save(category);
        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory , CategoryDTO.class);
        return savedCategoryDTO;
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category deleteCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category" , "categoryId" , categoryId));
        categoryRepository.delete(deleteCategory);
        return "Category with categoryId: " + categoryId + " is deleted successfully !!";
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category" , "categoryId" , categoryId));

        Category category = modelMapper.map(categoryDTO , Category.class);
        category.setCategoryId(categoryId);
        Category updatedCategory = categoryRepository.save(category);
        CategoryDTO updatedCategoryDTO = modelMapper.map(updatedCategory , CategoryDTO.class);
        return updatedCategoryDTO;
    }
}
