package com.sondershop.sondershopapp.detalleVenta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.sondershop.sondershopapp.MyToolbar;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.detalleVenta.db.AppDatabase;
import com.sondershop.sondershopapp.detalleVenta.db.User;
import com.sondershop.sondershopapp.formularioCompra;
import com.sondershop.sondershopapp.homeActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class detalleProducto extends AppCompatActivity {


    CircleImageView btnagregarRT,btnquitarRT;

    String titulo="";
    String marca="";
    String precio="";
    String obtienesku="";
    String obtienecategoria="";
    String obtienemodelo="";
    String obtieneimagen1="";
    String obtieneimagen2="";
    String obtieneimagen3="";
    String obtieneimagen4="";
    String obtieneinfodetallada="";
    String obtieneidproducto="";
    String obtieneidusuarioproducto="";
    String obtieneEspecificaciones="";
    String obtieneestadoProducto="";
    String obtienegarantia="";
    String obtienePais="";

    TextView txtmarca,txtprecio,txttitulo,txtmodelo,txtsku,txtDetalle,txtcantidad,txtespecifica,txtobtieneGarantia,txtobtienePais,txtobtieneEstadoProducto;
    ImageSlider imageSlider;
    Button btncompra1,btncarrito;
    List<SlideModel> slideModels;
    int cantidad = 1;
    int totalArticulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        txtmarca = (TextView) findViewById(R.id.txtmarcaRecibe);
        txtprecio = (TextView) findViewById(R.id.txtobtieneprecio);
        txttitulo = (TextView) findViewById(R.id.txtobtienetitulo);
        txtmodelo = (TextView) findViewById(R.id.txtmodeloRecibe);
        txtsku = (TextView) findViewById(R.id.txtskuRecibe);
        btncompra1 = (Button) findViewById(R.id.btnlogin);
        txtDetalle = findViewById(R.id.recibedescripcion);
        imageSlider = findViewById(R.id.slider);
        txtcantidad = findViewById(R.id.cantidad);
        btnagregarRT = findViewById(R.id.btnagregar);
        btnquitarRT = findViewById(R.id.btnquitar2);
        btncarrito = findViewById(R.id.btnadd);
        txtespecifica = findViewById(R.id.recibeespecificacion);
        txtobtieneGarantia = findViewById(R.id.txtobtieneGarantia);
        txtobtienePais = findViewById(R.id.txtobtienePais);
        txtobtieneEstadoProducto = findViewById(R.id.txtobtieneEstadoProducto);

        txtcantidad.setText(String.valueOf(cantidad));

        btncarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewUser(titulo, marca,obtieneimagen1,String.valueOf(totalArticulo),txtcantidad.getText().toString());

                Intent intent = new Intent(detalleProducto.this, carritoCompra.class);
                intent.putExtra("precioTotal",precio);
                intent.putExtra("tituloProducto",titulo);
                intent.putExtra("idproducto",obtieneidproducto);
                intent.putExtra("id_usuario_producto",obtieneidusuarioproducto);

                startActivity(intent);
            }
        });

        btnagregarRT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad = cantidad+1;
                txtcantidad.setText(String.valueOf(cantidad));
                int precioCalculo = Integer.valueOf(precio);
                totalArticulo =  precioCalculo * cantidad;
                txtprecio.setText("S/ "+String.valueOf(totalArticulo));
            }
        });

        btnquitarRT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cantidad <2)
                {
                   // Toast.makeText(detalleProducto.this, "es menor a 1", Toast.LENGTH_SHORT).show();
                }else
                {
                    cantidad = cantidad-1;
                    txtcantidad.setText(String.valueOf(cantidad));

                    int precioCalculo = Integer.valueOf(precio);

                    totalArticulo =  precioCalculo * cantidad;

                    txtprecio.setText("S/ "+String.valueOf(totalArticulo));
                }


            }
        });

        btncompra1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mPref = getApplicationContext().getSharedPreferences("typeUser", Context.MODE_PRIVATE);

                String typeUser = mPref.getString("username", "");

                if(typeUser.isEmpty())
                {
                    Toast.makeText(detalleProducto.this, "Primero Logueate porfavor", Toast.LENGTH_SHORT).show();
                }else
                {
                    Intent intent = new Intent(detalleProducto.this, formularioCompra.class);
                    intent.putExtra("precioTotal",precio);
                    intent.putExtra("tituloProducto",titulo);
                    intent.putExtra("idproducto",obtieneidproducto);
                    intent.putExtra("id_usuario_producto",obtieneidusuarioproducto);
                    startActivity(intent);
                }
            }
        });

        Bundle extras = getIntent().getExtras();

        //obtencion de datos por el metodo putextra
        if(extras != null)
        {
            Intent intent = getIntent();
            titulo = extras.getString("nombreProducto");
            marca = extras.getString("marcaProducto");
            precio = extras.getString("precioProducto");
            obtieneimagen1 = extras.getString("imagen1Producto");
            obtieneimagen2 = extras.getString("imagen2Producto");
            obtieneimagen3 = extras.getString("imagen3Producto");
            obtieneimagen4 = extras.getString("imagen4Producto");
            obtienesku = extras.getString("skuProducto");
            obtienecategoria = extras.getString("categoriaProducto");
            obtienemodelo = extras.getString("modeloProducto");
            obtieneinfodetallada = extras.getString("infodetalladaProducto");
            obtieneidproducto = extras.getString("idproducto");
            obtieneidusuarioproducto = extras.getString("id_usuario_producto");
            obtieneEspecificaciones = extras.getString("id_especificaciones");

            obtieneestadoProducto = extras.getString("id_estadoProducto");
            obtienegarantia = extras.getString("id_guardaGarantia");
            obtienePais = extras.getString("id_guardaPais");


            intent.putExtra("id_guardaPais",obtienePais);

            int almacenaPrecio = Integer.valueOf(precio);

            totalArticulo =  almacenaPrecio * 1;

            txttitulo.setText(titulo);
            txtprecio.setText("S/ "+precio);
            txtmarca.setText(marca);
            txtmodelo.setText(obtienemodelo);
            txtsku.setText(obtienesku);
            txtDetalle.setText(obtieneinfodetallada);
            txtespecifica.setText(obtieneEspecificaciones);
            txtobtieneGarantia.setText(obtienegarantia);
            txtobtieneEstadoProducto.setText(obtieneestadoProducto);
            txtobtienePais.setText(obtienePais);

            Log.d("geo",obtieneinfodetallada);
         //   txtinfodetallada.setText(obtieneinfodetallada);

            List<SlideModel> slideModels=new ArrayList<>();
            slideModels.add(new SlideModel(obtieneimagen1,""));
            slideModels.add(new SlideModel(obtieneimagen2,""));
            slideModels.add(new SlideModel(obtieneimagen3,""));
            slideModels.add(new SlideModel(obtieneimagen4,""));
            imageSlider.setImageList(slideModels,true);

           // Picasso.with(detalleProducto.this).load(obtieneimagen1).into(foto);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(detalleProducto.this, homeActivity.class);
      //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void saveNewUser(String titulo, String marca, String urlImage,String costoProducto,String cantidad) {
        AppDatabase db  = AppDatabase.getDbInstance(this.getApplicationContext());

        User user = new User();
        user.titulo = titulo;
        user.marca = marca;
        user.urlImage = urlImage;
        user.costoProducto = costoProducto;
        user.cantidadArticulos = cantidad;
        db.userDaoInterf().insertUser(user);

        finish();
    }
}