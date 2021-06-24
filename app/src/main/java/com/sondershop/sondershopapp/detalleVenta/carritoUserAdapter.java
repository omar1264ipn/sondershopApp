package com.sondershop.sondershopapp.detalleVenta;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.detalleVenta.db.User;
import com.sondershop.sondershopapp.detalleVenta.dbDirecciones.UserDirecciones;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class carritoUserAdapter extends RecyclerView.Adapter<carritoUserAdapter.MyViewHolder> {

    private Context context;
    private List<User> userList;
    private List<User> items;
    int total = 0;

    public carritoUserAdapter(List<User> items){
        this.items = items;
    }

    public carritoUserAdapter(Context context) {
        this.context = context;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardviewcarrito, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.titulo.setText(this.userList.get(position).titulo);
        holder.marca.setText(this.userList.get(position).marca);
        holder.obtieneUrlImagen = this.userList.get(position).urlImage;
        holder.txtcosto.setText(this.userList.get(position).costoProducto);
        holder.txtcantidad.setText(this.userList.get(position).cantidadArticulos);
        holder.obtengoCosto = Integer.parseInt(holder.txtcosto.getText().toString());
        total = total+holder.obtengoCosto;
        String guardalo = String.valueOf(total);
    //    String guardalo = "0";
        carritoCompra.precioTotal.setText("S/ "+guardalo);
        Picasso.get().load(holder.obtieneUrlImagen).into(holder.imagePerfil);
        Log.d("cartera",holder.obtieneUrlImagen);
        Log.d("cartera",String.valueOf(holder.obtengoCosto));




        Log.d("fusion",String.valueOf(total));

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context, "presionado", Toast.LENGTH_SHORT).show();

                User user = new User();
                int id = userList.get(position).getUid();
                // int id = userList2.get(getAdapterPosition()).getUid();
                user.setUid(id);
                carritoCompra.roomDatabaseClaseUser.userDaoInterf().delete(user);
                notifyDataSetChanged();

                // este metodo es para usar funciones y parametros de la clase que manda a llenar a este adaptador
                // tambien puede ser creando una interface

                if (context instanceof carritoCompra) {
                    ((carritoCompra)context).actualizaProductos();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return  this.userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView marca, txtcosto,txtcantidad;

        ImageView imagePerfil;

        String obtieneUrlImagen;

        int obtengoCosto = 0;
        CircleImageView btndelete;

        public MyViewHolder(View view) {
            super(view);
            titulo = view.findViewById(R.id.textView4);
            marca = view.findViewById(R.id.textView3);
            imagePerfil = view.findViewById(R.id.imageView6);
            txtcosto = view.findViewById(R.id.textView6);
            txtcantidad = view.findViewById(R.id.txtcantidad);
            btndelete = view.findViewById(R.id.btndelete);
        }
    }
}
