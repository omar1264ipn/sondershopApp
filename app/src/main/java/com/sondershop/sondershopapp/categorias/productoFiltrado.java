package com.sondershop.sondershopapp.categorias;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sondershop.sondershopapp.OperationRetrofitApi.ApiClient;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiInterface;
import com.sondershop.sondershopapp.OperationRetrofitApi.Users;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.adapter.productosCategoriaAdapter;
import com.sondershop.sondershopapp.models.productosCategoriaModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class productoFiltrado extends AppCompatActivity {

    public static ApiInterface apiInterface;
    private RecyclerView recyclerViewSimple;
    private productosCategoriaAdapter productosInstanciaCatAdap;
    private List<productosCategoriaModel> productosCatModelList;

    EditText cajaFiltro;

    CircleImageView btnfiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_filtrado);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        inicia();
    }

    private void inicia()
    {
        cajaFiltro = (EditText) findViewById(R.id.cajafiltro);
        btnfiltro = findViewById(R.id.btnfiltro);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerViewSimple = findViewById(R.id.recyclerviewSimple);
     //   LinearLayoutManager layoutManagerSimpleVerticalSlider = new LinearLayoutManager(this);
        recyclerViewSimple.setLayoutManager(gridLayoutManager);
        String user_filtro = cajaFiltro.getText().toString().trim();

        btnfiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("kimbo8",user_filtro);

                if (TextUtils.isEmpty(user_filtro)) {
                    cajaFiltro.setError("Ingrese valores a consultar");
                }
                else
                {
                    ProgressDialog dialog = new ProgressDialog(productoFiltrado.this);
                    dialog.setTitle("Loading...");
                    dialog.setMessage("Estamos Buscando tus Productos");
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);
                    /*

                    Call<Users> call = apiInterface.performFiltro(user_filtro);
                    call.enqueue(new Callback<Users>() {
                        @Override
                        public void onResponse(Call<Users> call, Response<Users> response) {

                            productosFiltradosModelList = new ArrayList<>();
                            Call<Users> productos_filtrados = apiInterface.getproductofiltrado();
                            productos_filtrados.enqueue(new Callback<Users>() {
                                @Override
                                public void onResponse(Call<Users> call, Response<Users> response) {
                                    productosFiltradosModelList = response.body().getProductos_categoria_filtrado();
                                    productosInstanciaCatFiltroAdap = new productosCategoriaFiltroAdapter(productosFiltradosModelList, productoFiltrado.this);
                                    recyclerViewSimple.setAdapter(productosInstanciaCatFiltroAdap);
                                    productosInstanciaCatFiltroAdap.notifyDataSetChanged();
                                }
                                @Override
                                public void onFailure(Call<Users> call, Throwable t) {
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<Users> call, Throwable t) {

                        }
                    });

                     */
                }
            }
        });


        productosCatModelList = new ArrayList<>();
        Call<Users> productos_categoria = apiInterface.getProductosCategoria();
        productos_categoria.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                productosCatModelList = response.body().getProductos_categoria();
                productosInstanciaCatAdap = new productosCategoriaAdapter(productosCatModelList, productoFiltrado.this);
                recyclerViewSimple.setAdapter(productosInstanciaCatAdap);
                productosInstanciaCatAdap.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Users> call, Throwable t) {
            }
        });

    }
}