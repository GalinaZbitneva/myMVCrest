package me.mvcRest.api.v1.model;

import lombok.Data;

import java.util.List;

@Data
public class CategoryListDTO {
    List<CategoryDTO> categories;
}
