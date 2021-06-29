package com.sondershop.sondershopapp.categorias;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sondershop.sondershopapp.OperationRetrofitApi.ApiClient;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiInterface;
import com.sondershop.sondershopapp.OperationRetrofitApi.Users;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.adapter.productosCategoriaAdapter;
import com.sondershop.sondershopapp.adapter.productosFiltrosAdapter;
import com.sondershop.sondershopapp.models.filtroProducModel;
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

    private productosFiltrosAdapter productosInstanciaFiltroAdap;
    private List<filtroProducModel> productosFiltroModelList;
    EditText cajaFiltro;
    CircleImageView btnfiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_filtrado);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
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

                Toast.makeText(productoFiltrado.this, ""+cajaFiltro.getText().toString(), Toast.LENGTH_SHORT).show();

                Log.d("kimbo8",cajaFiltro.getText().toString());
                if (cajaFiltro.getText().toString().isEmpty()) {
                    Toast.makeText(productoFiltrado.this, "llena datos", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ProgressDialog dialog = new ProgressDialog(productoFiltrado.this);
                    dialog.setTitle("Loading...");
                    dialog.setMessage("Estamos Buscando tus productos");
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);
                    productosFiltroModelList = new ArrayList<>();
                    Call<Users> call = apiInterface.performFiltrados(cajaFiltro.getText().toString());
                    call.enqueue(new Callback<Users>() {
                        @Override
                        public void onResponse(Call<Users> call, Response<Users> response) {
                            try {
                                productosFiltroModelList = response.body().getProductosFiltrados();
                                productosInstanciaFiltroAdap = new productosFiltrosAdapter(productosFiltroModelList, productoFiltrado.this);
                                recyclerViewSimple.setAdapter(productosInstanciaFiltroAdap);
                                productosInstanciaFiltroAdap.notifyDataSetChanged();
                                dialog.dismiss();
                            }catch (Exception e)
                            {
                                Log.d("samsung",e.toString());
                            }
                        }
                        @Override
                        public void onFailure(Call<Users> call, Throwable t) {
                        }
                    });


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