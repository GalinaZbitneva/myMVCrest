package me.mvcRest.controllers.v1;

import me.mvcRest.api.v1.model.CategoryDTO;
import me.mvcRest.api.v1.model.CategoryListDTO;
import me.mvcRest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {
    //@GetMapping — только для чтения (GET),
    // @RequestMapping — универсальная (GET, POST, PUT, DELETE и т.д.). и только для HTTP запросов
    public static String BASE_URL = "/api/v1/categories/";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    //@RestController объединяет в себе Controller и  Responseбоди, что позволяет избавится от ResponseEntity
    // в методе. ниже два варианта написания

//    @GetMapping
//    public ResponseEntity<CategoryListDTO> getAllCategories(){
//        return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories(){
        return new  CategoryListDTO(categoryService.getAllCategories());
    }


    @GetMapping ("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }
}
