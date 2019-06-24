package com.example.chocofood.presentation.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.chocofood.R;
import com.example.chocofood.domain.model.Restaurant;
import com.joooonho.SelectableRoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageview_item_restaurant) SelectableRoundedImageView mImage;
    @BindView(R.id.textview_item_restaurant_title) TextView mTitle;
    @BindView(R.id.textview_item_restaurant_cuisines) TextView mCuisines;
    @BindView(R.id.imageview_item_restaurant_logo) ImageView mLogo;
    @BindView(R.id.textview_item_restaurant_rating) TextView mRating;
    @BindView(R.id.textview_item_restaurant_delivery_time) TextView mDeliveryTime;
    @BindView(R.id.textview_item_restaurant_promotion) TextView mPromotion;
    @BindView(R.id.textview_item_restaurant_tenge) TextView mMinimumCost;

    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Restaurant restaurant, Context context) {
        String rating = Double.toString(restaurant.getRating() / 20);
        String minimumCost = Integer.toString(restaurant.getMinimumCost()) + " тг";
        mTitle.setText(restaurant.getTitle());
        mCuisines.setText(restaurant.getCuisines());
        mRating.setText(rating);
        mDeliveryTime.setText(restaurant.getDeliveryTime());
        mMinimumCost.setText(minimumCost);
        Glide.with(context).load(restaurant.getCardImageUrl()).into(mImage);
        Glide.with(context).load(restaurant.getLogoUrl()).into(mLogo);

        if (restaurant.getIsPromoted() == 0) {
            mPromotion.setVisibility(View.GONE);
        }
    }
}
