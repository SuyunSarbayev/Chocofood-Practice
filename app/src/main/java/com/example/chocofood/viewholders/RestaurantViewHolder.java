package com.example.chocofood.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.chocofood.R;
import com.example.chocofood.models.Restaurant;
import com.joooonho.SelectableRoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageview_item_restaurant) SelectableRoundedImageView mImage;
    @BindView(R.id.textview_item_title) TextView mTitle;
    @BindView(R.id.textview_item_cuisines) TextView mCuisines;

    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Restaurant restaurant, Context context) {
        mTitle.setText(restaurant.getTitle());
        mCuisines.setText(restaurant.getCuisines());
        Glide.with(context).load(restaurant.getCardImageUrl()).into(mImage);
    }
}
