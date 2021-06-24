package com.sondershop.sondershopapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.models.BannerModel;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.PlateViewHolder> {


    private List<BannerModel> bannerModelList;
    private Context context;

    public BannerAdapter(List<BannerModel> bannerModelList, Context context) {
        this.bannerModelList = bannerModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_banner, viewGroup, false);

        return new PlateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, int position) {

        BannerModel bannerModel = bannerModelList.get(position);
        Glide.with(context).load(bannerModel.getBanner_image()).placeholder(R.drawable.app4).into(holder.banner_img);
    }

    @Override
    public int getItemCount() {
        return bannerModelList.size();
    }

    public class PlateViewHolder extends RecyclerView.ViewHolder {

        private ImageView banner_img;

        public PlateViewHolder(@NonNull View itemView) {
            super(itemView);

            banner_img = (ImageView) itemView.findViewById(R.id.banner_img);
        }
    }
}
