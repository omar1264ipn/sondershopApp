package com.sondershop.sondershopapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.sondershop.sondershopapp.Contacto;
import com.sondershop.sondershopapp.CustomExpandableListAdapter;
import com.sondershop.sondershopapp.MyToolbar;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.categorias.productoFiltrado;
import com.sondershop.sondershopapp.detalleVenta.RecyclerViewAdaptador;
import com.sondershop.sondershopapp.detalleVenta.categoriasSonder;
import com.sondershop.sondershopapp.homeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class categoriasFragment extends Fragment {


    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListNombres;
    private HashMap<String, Contacto> listaContactos;
    private int lastExpandedPosition = -1;

    private View view;


    public categoriasFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_categorias, container, false);

        Toolbar myToolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.colornaranja));
        // myToolbar.setTitleColor(getResources().getColor(R.color.colornaranja));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Categorias");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        init();

        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });


        return view;
    }

    private void init() {
        this.expandableListView = view.findViewById(R.id.expandableListView);
        this.listaContactos = getContactos();
        this.expandableListNombres = new ArrayList<>(listaContactos.keySet());
        this.expandableListAdapter = new CustomExpandableListAdapter(getContext(),
                expandableListNombres, listaContactos);

    }


    private HashMap<String, Contacto> getContactos() {
        HashMap<String, Contacto> listaC = new HashMap<>();

        listaC.put("Moda Mujer", new Contacto("Moda mujer",
                "Bañadores", "Partes de abajo", "Boda y celebraciones","Lenceria y ropa interior","Accesorios","Zapatos", R.drawable.img_11,R.drawable.banner_ropa,7));
        return listaC;
    }
















    public List<categoriasSonder> obtenerCategoria()
    {
        List<categoriasSonder> instanciaCategoria = new ArrayList<>();
        instanciaCategoria.add(new categoriasSonder("Moda mujer",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Moda hombre",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Deporte",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Bebes y moda infantil",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Celulares y accesorios",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Camara,oficina,libros",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Videojuegos y consolas de videojuegos",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Electronica hogar e instrumentos musicales",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Hogar y mascotas",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Relojes,bolsos,joyeria",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Belleza y cuidado personal",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Tv,computacion,informatica",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Seguridad y proteccion",R.drawable.banner_ropa));
        instanciaCategoria.add(new categoriasSonder("Accesorios autos y motos",R.drawable.banner_ropa));

        return instanciaCategoria;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_categoria, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //insertar datos
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.icon_shopping: {
                Toast.makeText(getContext(), "Carrito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), productoFiltrado.class);
                startActivity(intent);
                // Animatoo.animateSlideUp(MainActivity.this);
                Animatoo.animateSwipeRight(getActivity());
            }
            break;
            case R.id.icon_favorito: {
                Toast.makeText(getContext(), "favorito", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return true;

    }


}