package com.example.chocofood.presentation.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chocofood.R;
import com.example.chocofood.domain.model.Category;
import com.example.chocofood.presentation.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.imageview_item_category) ImageView mImage;
    @BindView(R.id.textview_item_category_title) TextView mTitle;

    private OnItemClickListener mOnItemClickListener;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void bind(Category category, OnItemClickListener onItemClickListener) {
        mTitle.setText(category.getTitle());
        mOnItemClickListener = onItemClickListener;
        switch (category.getTitle()) {
            case "Пицца":
                mImage.setImageResource(R.drawable.category_pizza);
                break;
            case "Фаст-фуд":
                mImage.setImageResource(R.drawable.category_fastfood);
                break;
            case "Сладенькое":
                mImage.setImageResource(R.drawable.category_sweet);
                break;
            case "Шашлыки":
                mImage.setImageResource(R.drawable.category_shashlyk);
                break;
            case "Суши/роллы":
                mImage.setImageResource(R.drawable.category_sushi);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClick(getAdapterPosition());
    }
}
