package com.example.chocofood.presentation.main;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chocofood.R;
import com.example.chocofood.data.mapper.BannerMapper;
import com.example.chocofood.data.mapper.CategoryMapper;
import com.example.chocofood.data.repository.BannerDataRepository;
import com.example.chocofood.data.repository.CategoryDataRepository;
import com.example.chocofood.data.repository.RestaurantDataRepository;
import com.example.chocofood.data.mapper.RestaurantMapper;
import com.example.chocofood.domain.model.Banner;
import com.example.chocofood.domain.model.Category;
import com.example.chocofood.domain.model.RestaurantFilteredRequest;
import com.example.chocofood.domain.usecase.GetBannerListUseCase;
import com.example.chocofood.domain.usecase.GetCategoryListUseCase;
import com.example.chocofood.domain.usecase.GetRestaurantListByFilterUseCase;
import com.example.chocofood.domain.model.Restaurant;
import com.example.chocofood.presentation.listener.OnItemClickListener;
import com.example.chocofood.presentation.listener.PaginationScrollListener;
import com.example.chocofood.presentation.adapter.CategoryAdapter;
import com.example.chocofood.presentation.adapter.RestaurantAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.chocofood.domain.Constants.INITIAL_FILTER;
import static com.example.chocofood.domain.Constants.LIMIT;


public class MainActivity extends AppCompatActivity implements MainView,
        TabLayout.OnTabSelectedListener,
        OnItemClickListener {

    private RestaurantMapper mRestaurantMapper;
    private BannerMapper mBannerMapper;
    private CategoryMapper mCategoryMapper;
    private RestaurantDataRepository mRestaurantDataRepository;
    private BannerDataRepository mBannerDataRepository;
    private CategoryDataRepository mCategoryDataRepository;
    private GetRestaurantListByFilterUseCase mGetRestaurantListByFilterUseCase;
    private GetBannerListUseCase mGetBannerListUseCase;
    private GetCategoryListUseCase mGetCategoryListUseCase;
    private MainPresenter mMainPresenter;

    private List<Restaurant> mRestaurantList;
    private List<Category> mCategoryList;
    private LinearLayoutManager mLinearLayoutManagerRestaurant;
    private LinearLayoutManager mLinearLayoutManagerCategory;
    private RestaurantAdapter mRestaurantAdapter;
    private CategoryAdapter mCategoryAdapter;

    private RestaurantFilteredRequest mRestaurantFilteredRequest;

    private boolean mIsLoading = false;
    private boolean mIsLastPage = false;



    @BindView(R.id.imageview_main_banner) ImageView mImageViewBanner;
    @BindView(R.id.tablayout_main) TabLayout mTabLayout;
    @BindView(R.id.recyclerview_main_restaurants) RecyclerView mRecyclerViewRestaurant;
    @BindView(R.id.recyclerview_main_categories) RecyclerView mRecyclerViewCategory;
    @BindView(R.id.shimmerframe_main_restaurants) ShimmerFrameLayout mShimmerFrameLayoutRestaurant;
    @BindView(R.id.shimmerframe_main_categories) ShimmerFrameLayout mShimmerFrameLayoutCategory;
    @BindView(R.id.progressbar_main_banner) ProgressBar mProgressBarBanner;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initPresenter();
        setupTabLayout();
        initUI();

        loadRestaurantListByFilter();
        loadCategoryList();
        loadBanner();
    }

    private void setupTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.main_tablayout_best));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.main_tablayout_popular));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.main_tablayout_favorites));
        mTabLayout.addOnTabSelectedListener(this);
    }

    private void initUI() {
        mRestaurantFilteredRequest = new RestaurantFilteredRequest(INITIAL_FILTER,
                null, null, LIMIT, 0);
        mRestaurantList = new ArrayList<>();
        mCategoryList = new ArrayList<>();
        mLinearLayoutManagerRestaurant = new LinearLayoutManager(this);
        mLinearLayoutManagerCategory = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewRestaurant.setLayoutManager(mLinearLayoutManagerRestaurant);
        mRecyclerViewCategory.setLayoutManager(mLinearLayoutManagerCategory);
        initCategoryAdapter();
        initRestaurantAdapter();
        mRecyclerViewCategory.setAdapter(mCategoryAdapter);
        mRecyclerViewRestaurant.setAdapter(mRestaurantAdapter);
        mRecyclerViewRestaurant.addOnScrollListener(new PaginationScrollListener(mLinearLayoutManagerRestaurant) {
            @Override
            public void loadMoreItems() {
                mIsLoading = true;
                if (!mIsLastPage) {
                    loadRestaurantListByFilter();
                }
            }

            @Override
            public boolean isLastPage() {
                return mIsLastPage;
            }

            @Override
            public boolean isLoading() {
                return mIsLoading;
            }
        });
    }

    private void initCategoryAdapter() {
        mCategoryAdapter = new CategoryAdapter();
        mCategoryAdapter.setCategoryList(mCategoryList);
        mCategoryAdapter.setOnItemClickListener(this);
    }

    private void initRestaurantAdapter() {
        mRestaurantAdapter = new RestaurantAdapter(this);
        mRestaurantAdapter.setRestaurantList(mRestaurantList);
    }

    private void loadRestaurantListByFilter() {
        mShimmerFrameLayoutRestaurant.setVisibility(View.VISIBLE);
        mShimmerFrameLayoutRestaurant.startShimmer();
        mMainPresenter.getRestaurantListByFilter(mRestaurantFilteredRequest);
    }

    private void loadCategoryList() {
        mShimmerFrameLayoutCategory.startShimmer();
        mMainPresenter.getCategoryList();
    }

    private void loadBanner() {
        mMainPresenter.getBannerList();
    }

    private void clearRestaurantList() {
        mRestaurantList.clear();
        mRestaurantAdapter.notifyDataSetChanged();
        mRestaurantFilteredRequest.setOffset(0);
        mRecyclerViewRestaurant.setVisibility(View.GONE);
    }

    @Override
    public void initPresenter() {
        initMappers();
        initRepositories();
        initUseCases();
        mMainPresenter = new MainPresenter(mGetRestaurantListByFilterUseCase,
                mGetBannerListUseCase, mGetCategoryListUseCase);
        mMainPresenter.setMainView(this);
    }

    @Override
    public void initRepositories() {
        mRestaurantDataRepository = new RestaurantDataRepository(mRestaurantMapper);
        mBannerDataRepository = new BannerDataRepository(mBannerMapper);
        mCategoryDataRepository = new CategoryDataRepository(mCategoryMapper);
    }

    @Override
    public void initMappers() {
        mRestaurantMapper = new RestaurantMapper();
        mBannerMapper = new BannerMapper();
        mCategoryMapper = new CategoryMapper();
    }

    @Override
    public void initUseCases() {
        mGetRestaurantListByFilterUseCase = new GetRestaurantListByFilterUseCase(mRestaurantDataRepository);
        mGetBannerListUseCase = new GetBannerListUseCase(mBannerDataRepository);
        mGetCategoryListUseCase = new GetCategoryListUseCase(mCategoryDataRepository);
    }

    @Override
    public void displayRestaurants(List<Restaurant> restaurantList) {
        mRestaurantList.addAll(restaurantList);
        mRestaurantAdapter.notifyDataSetChanged();

        mIsLoading = false;
        if (restaurantList.size() == LIMIT) {
            mRestaurantAdapter.addRestaurants(restaurantList);
            mRestaurantFilteredRequest.setOffset(mRestaurantFilteredRequest.getOffset() + LIMIT);
        } else {
            mIsLastPage = true;
        }

        mShimmerFrameLayoutRestaurant.stopShimmer();
        mShimmerFrameLayoutRestaurant.setVisibility(View.GONE);
        mRecyclerViewRestaurant.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayBanners(List<Banner> bannerList) {
        Banner banner = bannerList.get(0);
        Glide.with(this).load(banner.getImageUrl()).into(mImageViewBanner);
        mProgressBarBanner.setVisibility(View.GONE);
        mImageViewBanner.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayCategories(List<Category> categoryList) {
        mCategoryList.addAll(categoryList);
        mCategoryAdapter.notifyDataSetChanged();
        mShimmerFrameLayoutCategory.stopShimmer();
        mShimmerFrameLayoutCategory.setVisibility(View.GONE);
        mRecyclerViewCategory.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        clearRestaurantList();
        switch (tab.getPosition()) {
            case 0:
                mRestaurantFilteredRequest.setFilter("rating");
                mRestaurantFilteredRequest.setFavoriteMode(null);
                break;
            case 1:
                mRestaurantFilteredRequest.setFilter("popular");
                mRestaurantFilteredRequest.setFavoriteMode(null);
                break;
            case 2:
                mRestaurantFilteredRequest.setFilter(null);
                mRestaurantFilteredRequest.setFavoriteMode("on");
                break;
        }
        loadRestaurantListByFilter();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onItemClick(int position) {
        Category category = mCategoryList.get(position);
        mRestaurantFilteredRequest.setFoodType(category.getSlug());
        clearRestaurantList();
        loadRestaurantListByFilter();
    }
}
