package com.example.chocofood.data.mapper;

import com.example.chocofood.data.entity.CategoryEntity;
import com.example.chocofood.domain.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    public CategoryMapper() {
    }

    public Category transformCategory(CategoryEntity categoryEntity) {
        Category category = null;

        if (categoryEntity != null) {
            category = new Category(categoryEntity.getId(), categoryEntity.getTitle(), categoryEntity.getSlug());
        }
        return category;
    }

    public List<Category> transformCategoryList(List<CategoryEntity> categoryEntityList) {
        List<Category> categoryList = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntityList) {
            if (categoryEntity != null)
                categoryList.add(transformCategory(categoryEntity));
        }
        return categoryList;
    }
}
