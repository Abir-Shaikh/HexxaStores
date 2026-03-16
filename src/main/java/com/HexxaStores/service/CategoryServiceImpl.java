package com.HexxaStores.service;

import com.HexxaStores.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private List<Category> categories = new ArrayList<>();
    private Long nextID = 1L;


    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void CreateCategories(Category category) {

        category.setCategoryId(nextID++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Resource not found"));

//        if (category == null) {
//            return "Category not found !!" +
//                    " Try another ID";
//        }
        categories.remove(category);
        return "Category with categoryId: " + categoryId + " is deleted successfully !!";
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {
        Category existingCategory = categories.stream()
                .filter(c -> categoryId.equals(c.getCategoryId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Category not found"));
        existingCategory.setCategoryName(category.getCategoryName());
        return "Category added succesfully !";
    }
}
