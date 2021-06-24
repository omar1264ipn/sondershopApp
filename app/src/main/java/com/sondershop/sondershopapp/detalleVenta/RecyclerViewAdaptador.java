package com.sondershop.sondershopapp.detalleVenta;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sondershop.sondershopapp.R;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.CategoryItemViewHolder> {

    private Context context;
    private List<categoriasSonder> categoryItemList;

    public RecyclerViewAdaptador(Context context, List<categoriasSonder> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.categorias_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryItemViewHolder holder, final int position) {


        holder.itemImage.setImageResource(categoryItemList.get(position).getImgCategoria());
        holder.itemNombre.setText(categoryItemList.get(position).getNombreCategoria());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Toast.makeText(context, ""+holder.itemNombre.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public static final class CategoryItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemNombre;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.imageView6);
            itemNombre = itemView.findViewById(R.id.textView3);

        }
    }
}
