package com.example.chocofood.data.mapper;

import com.example.chocofood.data.entity.BannerEntity;
import com.example.chocofood.domain.model.Banner;

import java.util.ArrayList;
import java.util.List;

public class BannerMapper {

    public BannerMapper() {
    }

    public Banner transformBanner(BannerEntity bannerEntity) {
        Banner banner = null;

        if (bannerEntity != null) {
            banner = new Banner(bannerEntity.getId(), bannerEntity.getImageUrl(),
                    bannerEntity.getTitle());
        }
        return banner;
    }

    public List<Banner> transformBannerList(List<BannerEntity> bannerEntityList) {
        List<Banner> bannerList = new ArrayList<>();
        for (BannerEntity bannerEntity : bannerEntityList) {
            if (bannerEntity != null)
                bannerList.add(transformBanner(bannerEntity));
        }
        return bannerList;
    }
}
