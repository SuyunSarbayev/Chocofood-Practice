package com.example.chocofood.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chocofood.R;
import com.example.chocofood.domain.model.Restaurant;
import com.example.chocofood.presentation.viewholder.RestaurantViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private List<Restaurant> mRestaurantList;

    private Context mContext;

    public RestaurantAdapter(Context context) {
        mRestaurantList = new ArrayList<>();
        mContext = context;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = mRestaurantList.get(position);
        holder.bind(restaurant, mContext);
    }

    @Override
    public int getItemCount() {
        return mRestaurantList.size();
    }

    public void setRestaurantList(List<Restaurant> restaurantList){
        mRestaurantList = restaurantList;
        notifyDataSetChanged();
    }

    public void addRestaurants(List<Restaurant> restaurants){
        mRestaurantList.addAll(restaurants);
        notifyDataSetChanged();
    }

}
