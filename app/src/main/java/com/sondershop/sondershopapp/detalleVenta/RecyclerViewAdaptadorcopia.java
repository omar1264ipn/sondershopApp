package com.sondershop.sondershopapp.detalleVenta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.sondershop.sondershopapp.R;

import java.util.List;

public class RecyclerViewAdaptadorcopia extends RecyclerView.Adapter<RecyclerViewAdaptadorcopia.ViewHolder> {

    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreCat;
        ImageView imagenBanner;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreCat= itemView.findViewById(R.id.textView3);
            imagenBanner = itemView.findViewById(R.id.imageView6);
        }
    }

    public List<categoriasSonder> categoriasSonderlist;

    public RecyclerViewAdaptadorcopia(List<categoriasSonder> categoriasSonderlist) {
        this.categoriasSonderlist = categoriasSonderlist;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorias_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombreCat.setText(categoriasSonderlist.get(position).getNombreCategoria());
        holder.imagenBanner.setImageResource(categoriasSonderlist.get(position).getImgCategoria());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if(holder.itemNombre.getText().toString().equals("flashcard"))
                {
                    Intent mIntent  = new Intent(context, fullflashCard.class);
                    mIntent.putExtra("NombreFlash","Control Prenatal");
                    context.startActivity(mIntent);
                }else
                {
                    //  Toast.makeText(context, "no entre al parametro", Toast.LENGTH_SHORT).show();
                }

                 */
                Toast.makeText(context, ""+holder.nombreCat.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriasSonderlist.size();
    }
}
