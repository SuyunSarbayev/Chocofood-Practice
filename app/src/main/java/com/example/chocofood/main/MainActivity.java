package com.example.chocofood.main;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chocofood.R;
import com.example.chocofood.adapters.CategoryAdapter;
import com.example.chocofood.adapters.RestaurantAdapter;
import com.example.chocofood.listeners.RecycleViewClickListener;
import com.example.chocofood.listeners.PaginationScrollListener;
import com.example.chocofood.models.Banner;
import com.example.chocofood.models.Category;
import com.example.chocofood.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.chocofood.support.Constants.LIMIT;

public class MainActivity extends AppCompatActivity implements MainView, TabLayout.OnTabSelectedListener {

    private MainPresenter mMainPresenter;
    private RestaurantAdapter mRestaurantAdapter;
    private CategoryAdapter mCategoryAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private List<Restaurant> mRestaurantList;
    private List<Category> mCategoryList;

    private boolean mIsLoading = false;
    private boolean mIsLastPage = false;
    private int mOffset = 0;
    private String mFilter;
    private String mType = null;

    @BindView(R.id.imageview_main_banner) ImageView mBannerImageView;
    @BindView(R.id.tablayout_main) TabLayout mTabLayout;
    @BindView(R.id.recyclerview_main_categories) RecyclerView mCategoryRecycler;
    @BindView(R.id.recyclerview_main_restaurants) RecyclerView mRestaurantsRecycler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupTabLayout();
        setupCategory();
        initUI();

        mCategoryRecycler.addOnItemTouchListener(new RecycleViewClickListener(this, new RecycleViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println(mType);
                mType = mCategoryList.get(position).getmSlug();
                refreshData();
                loadData(mOffset);

            }
        }));


        mMainPresenter.getBanner();
        loadData(mOffset);
    }


    private void setupTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.main_tablayout_best));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.main_tablayout_popular));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.main_tablayout_favorites));
        mTabLayout.addOnTabSelectedListener(this);
    }
    private void setupCategory()
    {

        mMainPresenter = new MainPresenter(this);
        mMainPresenter.getCategory();
        mCategoryList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCategoryRecycler.setLayoutManager(layoutManager);
        mCategoryAdapter = new CategoryAdapter(this);
        mCategoryAdapter.setCategoryList(mCategoryList);
        mCategoryRecycler.setAdapter(mCategoryAdapter);

    }

    private void initUI() {
        mMainPresenter = new MainPresenter(this);
        mRestaurantList = new ArrayList<>();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRestaurantsRecycler.setLayoutManager(mLinearLayoutManager);
        mRestaurantAdapter = new RestaurantAdapter(this);
        mRestaurantAdapter.setRestaurantList(mRestaurantList);
        mRestaurantsRecycler.setAdapter(mRestaurantAdapter);
        mRestaurantsRecycler.addOnScrollListener(new PaginationScrollListener(mLinearLayoutManager) {
            @Override
            public void loadMoreItems() {
                mIsLoading = true;
                if (!mIsLastPage) {

                    loadData(mOffset);
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

    private void loadData(int offset) {
        String offsetMessage = "Loading offset " + offset + " =)";
        Toast.makeText(this, offsetMessage, Toast.LENGTH_SHORT).show();
        mMainPresenter.getRestaurantsByFilter(mFilter,mType, LIMIT, offset);
    }

    private void refreshData() {
        mOffset = 0;
        mRestaurantList.clear();
        mRestaurantAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRestaurants(List<Restaurant> restaurantList) {
        mRestaurantList.addAll(restaurantList);
        mRestaurantAdapter.notifyDataSetChanged();
        mIsLoading = false;
        if (restaurantList.size() == LIMIT) {
            mRestaurantAdapter.addRestaurants(restaurantList);
            mOffset += LIMIT;
        } else {
            mIsLastPage = true;
        }
    }

    @Override
    public void showCategory(List<Category> categoryList) {

        mCategoryList.addAll(categoryList);
        mCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void showBanner(Banner banner) {
        Glide.with(this).load(banner.getImageUrl()).into(mBannerImageView);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        refreshData();
        switch (tab.getPosition()) {
            case 0:
                mFilter = "rating";
                break;
            case 1:
                mFilter = "popular";
                break;
            case 2:
                // Need to fix to get only favorites
                mFilter = "popular";
                break;
        }
        loadData(mOffset);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
