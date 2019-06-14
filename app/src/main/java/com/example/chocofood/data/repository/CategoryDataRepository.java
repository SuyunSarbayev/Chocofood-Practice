package com.example.chocofood.data.repository;

import com.example.chocofood.data.entity.CategoryEntity;
import com.example.chocofood.data.mapper.CategoryMapper;
import com.example.chocofood.data.network.NetworkClient;
import com.example.chocofood.data.network.NetworkConstants;
import com.example.chocofood.domain.model.Category;
import com.example.chocofood.domain.repository.CategoryRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class CategoryDataRepository implements CategoryRepository {

    private final CategoryMapper mCategoryMapper;

    public CategoryDataRepository(CategoryMapper categoryMapper) {
        mCategoryMapper = categoryMapper;
    }


    @Override
    public Observable<List<Category>> getCategories() {
        return NetworkClient.getInstance()
                .getNetworkApi()
                .getCategories()
                .map(new Function<List<CategoryEntity>, List<Category>>() {
                    @Override
                    public List<Category> apply(List<CategoryEntity> categoryEntityList) throws Exception {
                        return mCategoryMapper.transformCategoryList(categoryEntityList);
                    }
                });
    }
}
