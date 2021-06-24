package com.sondershop.sondershopapp.detalleVenta;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.detalleVenta.dbDirecciones.AppDatabaseDirecciones;
import com.sondershop.sondershopapp.detalleVenta.dbDirecciones.UserDirecciones;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class carritoUserAdapterDirecciones extends RecyclerView.Adapter<carritoUserAdapterDirecciones.MyViewHolder> {

    private Context context2;
    private List<UserDirecciones> userList2;



    public carritoUserAdapterDirecciones(Context context2) {
        this.context2 = context2;
    }

    public void setUserList2(List<UserDirecciones> userList2) {
        this.userList2 = userList2;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context2).inflate(R.layout.cardviewdirecciones, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.almacenapostal = this.userList2.get(position).codigoPostal;
        holder.nombre = this.userList2.get(position).nombre;
        holder.apellido = this.userList2.get(position).apellido;
        holder.numero = this.userList2.get(position).numero;
        holder.almacenapais = this.userList2.get(position).almacenaPais;
        holder.distrito = this.userList2.get(position).distrito;
        holder.calle = this.userList2.get(position).calle;
        holder.estado = this.userList2.get(position).estado;
        holder.codigopostal = this.userList2.get(position).codigoPostal;
        holder.caja1.setText("Recibe "+holder.nombre+" "+holder.apellido);
        holder.caja2.setText(holder.distrito+" "+holder.calle+" "+holder.estado);

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Toast.makeText(context2, "presionado", Toast.LENGTH_SHORT).show();

                UserDirecciones userDirecciones = new UserDirecciones();
                int id = userList2.get(position).getUid();
               // int id = userList2.get(getAdapterPosition()).getUid();
                userDirecciones.setUid(id);
                carritoCompra.roomDatabaseClase.userDaoInterfdirecciones().delete(userDirecciones);
                notifyDataSetChanged();


                // este metodo es para usar funciones y parametros de la clase que manda a llenar a este adaptador
                // tambien puede ser creando una interface
                if (context2 instanceof carritoCompra) {
                    ((carritoCompra)context2).actualizalista();
                }

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fusion",holder.almacenapostal);

                carritoCompra.direccionSeleccionada.setText("Direccion Seleccionada : "+holder.distrito+" "+holder.calle+" "+holder.estado);

             //   Toast.makeText(view.getContext(), "Click en el item " + position + " Nombre "+holder.nombre.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return  this.userList2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView caja1, caja2;
        String almacenapostal,nombre,apellido,numero,almacenapais,distrito,calle,estado,codigopostal;
        CircleImageView btndelete;

        public MyViewHolder(View view) {
            super(view);
            caja1 = view.findViewById(R.id.name);
            caja2 = view.findViewById(R.id.txtPaisCard);
            btndelete = view.findViewById(R.id.btndelete);

            btndelete.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {


            /*
            UserDirecciones userDirecciones = new UserDirecciones();
            int id = userList2.get(getAdapterPosition()).getUid();
            userDirecciones.setUid(id);
            carritoCompra.roomDatabaseClase.userDaoInterfdirecciones().delete(userDirecciones);
            notifyDataSetChanged();



            // este metodo es para usar funciones y parametros de la clase que manda a llenar a este adaptador
            // tambien puede ser creando una interface
            if (context2 instanceof carritoCompra) {
                ((carritoCompra)context2).actualizalista();
            }


             */

        }

    }
}
