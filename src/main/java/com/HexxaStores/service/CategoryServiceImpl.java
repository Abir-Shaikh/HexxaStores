package com.HexxaStores.service;

import com.HexxaStores.model.Category;
import com.HexxaStores.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    //private List<Category> categories = new ArrayList<>();
    //private Long nextID = 1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void CreateCategories(Category category) {

        //category.setCategoryId(nextID++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category deleteCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Resource not found"));



//        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst().
//                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Resource not found"));

//        if (category == null) {
//            return "Category not found !!" +
//                    " Try another ID";
//        }
        categoryRepository.delete(deleteCategory);
        return "Category with categoryId: " + categoryId + " is deleted successfully !!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Resource not found"));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;


//        Category existingCategory = categories.stream()
//                .filter(c -> categoryId.equals(c.getCategoryId()))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Category not found"));
//        existingCategory.setCategoryName(category.getCategoryName());
//        Category savedCategory = categoryRepository.save(existingCategory);
//        return savedCategory;
    }
}
