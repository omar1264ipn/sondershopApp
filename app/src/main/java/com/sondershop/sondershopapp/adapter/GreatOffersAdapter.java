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
import com.sondershop.sondershopapp.models.GreatOffersModel;
import com.sondershop.sondershopapp.R;

import java.util.List;

public class GreatOffersAdapter extends RecyclerView.Adapter<GreatOffersAdapter.GreatOfferViewHolder> {

    List<GreatOffersModel> greatOffersModelList;
    Context context;

    public GreatOffersAdapter(List<GreatOffersModel> greatOffersModelList, Context context) {
        this.greatOffersModelList = greatOffersModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public GreatOfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_horizontal_great_offers, viewGroup, false);
        return new GreatOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GreatOfferViewHolder holder, int position) {

        GreatOffersModel greatOffersModel = greatOffersModelList.get(position);
        Glide.with(context).load(greatOffersModel.getShop_image()).placeholder(R.drawable.logorobotics).into(holder.shopImg);

        holder.shop_name.setText(greatOffersModel.getShop_name());
        holder.discount.setText(greatOffersModel.getDiscount());
        holder.rating.setText(greatOffersModel.getRating());
        holder.time.setText(greatOffersModel.getCity());
    }

    @Override
    public int getItemCount() {
        //return 8;
        return greatOffersModelList.size();
    }

    public class GreatOfferViewHolder extends RecyclerView.ViewHolder {

        private ImageView shopImg;
        private TextView shop_name, discount, rating, time;

        public GreatOfferViewHolder(@NonNull View itemView) {
            super(itemView);

            shopImg = (ImageView) itemView.findViewById(R.id.imageview7);
            shop_name = (TextView) itemView.findViewById(R.id.textView9);
            discount = (TextView) itemView.findViewById(R.id.textView11);
            rating = (TextView) itemView.findViewById(R.id.textView12);
            time = (TextView) itemView.findViewById(R.id.textView10);
        }
    }
}
