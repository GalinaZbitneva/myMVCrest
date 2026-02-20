package me.mvcRest.services;

import me.mvcRest.api.v1.mapper.CategoryMapper;
import me.mvcRest.api.v1.model.CategoryDTO;
import me.mvcRest.domain.Category;
import me.mvcRest.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream().map(x->categoryMapper.categoryToCategoryDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
        return categoryDTO;
    }
}
