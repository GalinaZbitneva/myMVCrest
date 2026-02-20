package me.mvcRest.services;

import me.mvcRest.api.v1.mapper.CategoryMapper;
import me.mvcRest.api.v1.model.CategoryDTO;
import me.mvcRest.domain.Category;
import me.mvcRest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {
        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(),new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        //then
       assertEquals(3,categoryDTOS.size());

    }

    @Test
    void getCategoryByName() {
        Category testCategory = new Category();
        testCategory.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(testCategory);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        assertEquals(testCategory.getName(),categoryDTO.getName());


    }
}