package com.example.chocofood.data.repository;

import com.example.chocofood.data.entity.BannerEntity;
import com.example.chocofood.data.mapper.BannerMapper;
import com.example.chocofood.data.network.NetworkClient;
import com.example.chocofood.domain.model.Banner;
import com.example.chocofood.domain.repository.BannerRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class BannerDataRepository implements BannerRepository {

    private final BannerMapper mBannerMapper;

    public BannerDataRepository(BannerMapper bannerMapper) {
        mBannerMapper = bannerMapper;
    }

    @Override
    public Observable<List<Banner>> getBanners() {
        return NetworkClient.getInstance()
                .getNetworkApi()
                .getBanners()
                .map(new Function<List<BannerEntity>, List<Banner>>() {
                    @Override
                    public List<Banner> apply(List<BannerEntity> bannerEntityList) throws Exception {
                        return mBannerMapper.transformBannerList(bannerEntityList);
                    }
                });
    }
}
