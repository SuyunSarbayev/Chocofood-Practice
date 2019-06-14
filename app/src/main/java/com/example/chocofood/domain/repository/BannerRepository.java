package com.example.chocofood.domain.repository;

import com.example.chocofood.domain.model.Banner;

import java.util.List;

import io.reactivex.Observable;

public interface BannerRepository {

    Observable<List<Banner>> getBanners();
}
