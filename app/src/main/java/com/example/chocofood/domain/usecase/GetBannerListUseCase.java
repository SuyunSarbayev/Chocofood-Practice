package com.example.chocofood.domain.usecase;

import com.example.chocofood.domain.model.Banner;
import com.example.chocofood.domain.repository.BannerRepository;

import java.util.List;

import io.reactivex.Observable;

public class GetBannerListUseCase extends UseCase<List<Banner>, Void> {

    private final BannerRepository mBannerRepository;

    public GetBannerListUseCase(BannerRepository bannerRepository) {
        mBannerRepository = bannerRepository;
    }

    @Override
    Observable<List<Banner>> createObservable(Void aVoid) {
        return mBannerRepository.getBanners();
    }
}
