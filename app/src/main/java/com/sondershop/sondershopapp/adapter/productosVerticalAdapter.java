package com.sondershop.sondershopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sondershop.sondershopapp.detalleVenta.detalleProducto;
import com.sondershop.sondershopapp.models.SimpleVerticalModel;
import com.sondershop.sondershopapp.R;

import java.util.List;

public class productosVerticalAdapter extends RecyclerView.Adapter<productosVerticalAdapter.PlateViewHolder> {


    private List<SimpleVerticalModel> simpleVerticalModelList;
    private Context context;

    public productosVerticalAdapter(List<SimpleVerticalModel> simpleVerticalModelList, Context context) {
        this.simpleVerticalModelList = simpleVerticalModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardviewproductos, viewGroup, false);

        return new PlateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, int position) {

        SimpleVerticalModel simpleVerticalModel = simpleVerticalModelList.get(position);

        Glide.with(context).load(simpleVerticalModel.getShop_image()).into(holder.proImg);
        holder.pro_title.setText(simpleVerticalModel.getShop_name());
        holder.pro_desc.setText("Marca : "+simpleVerticalModel.getMarca());
        holder.almacenaStatus.setText("Estado : "+simpleVerticalModel.getEstado_producto());
        holder.pro_coupon.setText("Precio : S/ "+simpleVerticalModel.getPrecioProducto());
        holder.pro_status.setText(simpleVerticalModel.getDescription());
        holder.pro_rating.setText(simpleVerticalModel.getRating());
        holder.pro_marca = simpleVerticalModel.getMarca();
        holder.pro_imagen1 = simpleVerticalModel.getShop_image();
        holder.pro_imagen2 = simpleVerticalModel.getPro_imagen2();
        holder.pro_imagen3 = simpleVerticalModel.getPro_imagen3();
        holder.pro_imagen4 = simpleVerticalModel.getPro_imagen4();
        holder.almacenaPresentacion = simpleVerticalModel.getNombre_proveedor();
        holder.almacenasku = simpleVerticalModel.getSku();
        holder.almacenaPrecio = simpleVerticalModel.getPrecioProducto();
        holder.almacenamodelo = simpleVerticalModel.getModelo();
        holder.infoDetallada = simpleVerticalModel.getDescripcion_detallada();
        holder.idUsuarioProducto = String.valueOf(simpleVerticalModel.getId_usuario()); //ES PARA OBTENER EL ID DEL PRODUCTO
        holder.idProducto = String.valueOf(simpleVerticalModel.getId_producto()); //ES PARA OBTENER EL ID DEL PRODUCTO
        holder.guardaEspecifica = simpleVerticalModel.getEspecificaciones();
        holder.guardaGarantia = simpleVerticalModel.getGarantia();
        holder.guardaestadoProducto = simpleVerticalModel.getEstado_producto();
        holder.guardaPais = simpleVerticalModel.getCity();


        //aqui voy a declarar la forma de saber que item se le dio click para enviar esos parametros a la ventana detalle producto

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("cartera",holder.pro_title.getText().toString());
                Log.d("cartera",holder.pro_coupon.getText().toString());
                Log.d("cartera",holder.pro_marca);
                Log.d("estrella",holder.guardaEspecifica);
                Log.d("estrella",holder.guardaestadoProducto);
                Log.d("estrella",holder.guardaGarantia);
                Log.d("estrella",holder.guardaPais);
                Log.d("zeuxis",holder.almacenaPresentacion);
                try{
                    Log.d("cartera",holder.idUsuarioProducto);
                }catch (Exception e)
                {
                    Log.d("cartera",e.toString());
                }
             //   Log.d("cartera",holder.obtienegarantia);

                Log.d("micki","foto 2 "+holder.pro_imagen2);
                Log.d("micki","foto 3 "+holder.pro_imagen3);
                Log.d("micki","foto 4 "+holder.pro_imagen4);


                Intent mIntent  = new Intent(context, detalleProducto.class);
                mIntent.putExtra("nombreProducto",holder.pro_title.getText().toString());
                mIntent.putExtra("precioProducto",holder.almacenaPrecio);
                mIntent.putExtra("marcaProducto",holder.pro_marca);
                mIntent.putExtra("modeloProducto",holder.almacenamodelo);
                mIntent.putExtra("skuProducto",holder.almacenasku);
                mIntent.putExtra("categoriaProducto",holder.almacenaPresentacion);
                mIntent.putExtra("imagen1Producto",holder.pro_imagen1);
                mIntent.putExtra("imagen2Producto",holder.pro_imagen2);
                mIntent.putExtra("imagen3Producto",holder.pro_imagen3);
                mIntent.putExtra("imagen4Producto",holder.pro_imagen4);
                mIntent.putExtra("infodetalladaProducto",holder.infoDetallada);
                mIntent.putExtra("idproducto",holder.idProducto);
                mIntent.putExtra("id_usuario_producto",holder.idUsuarioProducto);
                mIntent.putExtra("id_especificaciones",holder.guardaEspecifica);
                mIntent.putExtra("id_estadoProducto",holder.guardaestadoProducto);
                mIntent.putExtra("id_guardaGarantia",holder.guardaGarantia);
                mIntent.putExtra("id_guardaPais",holder.guardaPais);


              //  Log.d("estrella",holder.guardaestadoProducto);
             //   Log.d("estrella",holder.guardaGarantia);
             //   Log.d("estrella",holder.guardaPais);

                context.startActivity(mIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return simpleVerticalModelList.size();
    }

    public class PlateViewHolder extends RecyclerView.ViewHolder {

        private ImageView proImg;
        private TextView pro_title, pro_desc, pro_coupon, pro_status, pro_rating,almacenaStatus;

        String pro_marca,pro_imagen1,pro_imagen2,pro_imagen3,pro_imagen4,
                almacenaPresentacion,almacenasku,almacenamodelo,infoDetallada,almacenaPrecio;

        String idProducto; //ES PARA OBTENER EL ID DEL PRODUCTO
        String idUsuarioProducto; //ES PARA OBTENER EL ID DEL PRODUCTO
        String guardaEspecifica,guardaGarantia,guardaestadoProducto,guardaPais;

        public PlateViewHolder(@NonNull View itemView) {
            super(itemView);

            proImg = (ImageView) itemView.findViewById(R.id.imageView6);
            pro_title = (TextView) itemView.findViewById(R.id.textView3);
            pro_desc = (TextView) itemView.findViewById(R.id.textView4);
            almacenaStatus = (TextView) itemView.findViewById(R.id.textView5);
           // pro_quantity = (TextView) itemView.findViewById(R.id.textView5); es el de colores del producto
            pro_coupon = (TextView) itemView.findViewById(R.id.textView6);
            pro_status = (TextView) itemView.findViewById(R.id.textView7);
            pro_rating = (TextView) itemView.findViewById(R.id.textView8);

        }
    }
}
