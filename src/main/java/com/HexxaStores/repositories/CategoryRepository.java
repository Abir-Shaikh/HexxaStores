package com.HexxaStores.repositories;

import com.HexxaStores.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {
    Category findByCategoryName(@NotBlank @Size(min = 4 , message = "Category name must contain atleast 4 Characters ") String categoryName);
}
