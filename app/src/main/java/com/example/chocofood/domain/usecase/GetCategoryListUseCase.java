package com.example.chocofood.domain.usecase;

import com.example.chocofood.domain.model.Category;
import com.example.chocofood.domain.repository.CategoryRepository;

import java.util.List;

import io.reactivex.Observable;

public class GetCategoryListUseCase extends UseCase<List<Category>, Void> {

    private final CategoryRepository mCategoryRepository;

    public GetCategoryListUseCase(CategoryRepository categoryRepository) {
        mCategoryRepository = categoryRepository;
    }

    @Override
    Observable<List<Category>> createObservable(Void aVoid) {
        return mCategoryRepository.getCategories();
    }
}
