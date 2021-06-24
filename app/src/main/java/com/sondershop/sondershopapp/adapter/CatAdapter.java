package com.sondershop.sondershopapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.models.CategoryModel;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.PlateViewHolder> {


    private List<CategoryModel> categoryModelList;
    private Context context;

    public CatAdapter(List<CategoryModel> categoryModelList, Context context) {
        this.categoryModelList = categoryModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category, viewGroup, false);

        return new PlateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, int position) {

        CategoryModel categoryModel = categoryModelList.get(position);
        holder.cat_title.setText(categoryModel.getCat_title());
        Glide.with(context).load(categoryModel.getCat_image()).placeholder(R.drawable.logorobotics).into(holder.cat_img);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class PlateViewHolder extends RecyclerView.ViewHolder {

        private ImageView cat_img;
        private TextView cat_title;

        public PlateViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_img = (ImageView) itemView.findViewById(R.id.imageView2);
            cat_title = (TextView) itemView.findViewById(R.id.textViewCat);

        }
    }
}
