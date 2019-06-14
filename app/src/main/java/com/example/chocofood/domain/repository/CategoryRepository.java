package com.example.chocofood.domain.repository;

import com.example.chocofood.domain.model.Category;

import java.util.List;

import io.reactivex.Observable;


public interface CategoryRepository {

    Observable<List<Category>> getCategories();
}
