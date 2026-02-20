package me.mvcRest.repositories;

import me.mvcRest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Category findByName(String categoryName);
}
