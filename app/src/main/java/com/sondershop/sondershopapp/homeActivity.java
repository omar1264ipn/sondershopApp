package com.sondershop.sondershopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sondershop.sondershopapp.Fragments.categoriasFragment;
import com.sondershop.sondershopapp.Fragments.primerFragment;
import com.sondershop.sondershopapp.Fragments.favoritosFragment;
import com.sondershop.sondershopapp.Fragments.perfilFragment;
import com.sondershop.sondershopapp.detalleVenta.carritoCompra;

public class homeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    Fragment selectedFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //cambia el color del texto del status
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        selectedFragment = new primerFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();
        //termina el color del cambio del texto del status
        //     frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigation =

            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.orders:
                            selectedFragment = new primerFragment();
                         //   Toast.makeText(homeActivity.this, "PRODUCTOS", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.goout:
                            selectedFragment = new categoriasFragment();
                            Toast.makeText(homeActivity.this, "Categorias", Toast.LENGTH_SHORT).show();
                         //   Toast.makeText(homeActivity.this, "BUSQUEDA", Toast.LENGTH_SHORT).show();
                            //   Toast.makeText(homeActivity.this, "Go out", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.gold:

                            Intent intent = new Intent(homeActivity.this, carritoCompra.class);
                            startActivity(intent);
                           // selectedFragment = new carritoFragment();
                        //    Toast.makeText(homeActivity.this, "Carrito", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.videos:
                            selectedFragment = new favoritosFragment();
                         //   Toast.makeText(homeActivity.this, "Favoritos", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.perfil:
                            selectedFragment = new perfilFragment();
                         //   Toast.makeText(homeActivity.this, "Mi perfil", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    //remplazando y asignando los fragmentos
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();
                    return false;
                }
            };
}