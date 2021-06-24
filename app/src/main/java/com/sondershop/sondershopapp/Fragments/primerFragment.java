package com.sondershop.sondershopapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.sondershop.sondershopapp.adapter.BannerAdapter;
import com.sondershop.sondershopapp.adapter.CatAdapter;
import com.sondershop.sondershopapp.adapter.GreatOffersAdapter;
import com.sondershop.sondershopapp.adapter.productosVerticalAdapter;
import com.sondershop.sondershopapp.formularioCompra;
import com.sondershop.sondershopapp.models.BannerModel;
import com.sondershop.sondershopapp.models.CategoryModel;
import com.sondershop.sondershopapp.models.GreatOffersModel;
import com.sondershop.sondershopapp.models.SimpleVerticalModel;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiClient;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiInterface;
import com.sondershop.sondershopapp.OperationRetrofitApi.Users;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.splashscreen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class primerFragment extends Fragment implements View.OnClickListener {


    DrawerLayout drawerLayout;
    ImageView navigationBar,strip_banner_image;
    NavigationView navigationView;
    private View view;
    TextView your_orders, online_ordering_help, address_book, favorite_orders, send_feedback, report_safety_emergency, rate_playstore;
    private RelativeLayout loginSignIUp;

    //////////////Categorias carga de recyclew>>>>

    private RecyclerView recyclerviewCategory;
    private CatAdapter catAdapter;
    private List<CategoryModel> categoryModelList;
    //fin rel recyclewview

    //banner slider>>>>>>>>>>>>>>>>>>>>>>>>>
    private RecyclerView recyclerViewBanner;
    private BannerAdapter bannerAdapter;
    private List<BannerModel> bannerModelList;

    //banner slider fin>>>>>>>>>>>>>>>>>>>>>


    //<<<<<<<<<simple listado vertical>>>>>>>>>>>>>>>>>><

    private RecyclerView recyclerViewSimple;
    private productosVerticalAdapter productosVerticalAdapter;
    private List<SimpleVerticalModel> simpleVerticalModelList;

    //<<<<<<<<<fin de simple listado vertical>>>>>>>>>>>>

    //<<<<<<<<<gran oferta horizontal inicio>>>>>>>>>>>>>>>>>><
    private RecyclerView greatGreatOffersHorizontal;
    private List<GreatOffersModel> greatOffersModel;
    private GreatOffersAdapter greatOffersAdapter;
    //<<<<<<<<<gran oferta horizontal fin >>>>>>>>>>>>

    //<<<<<<<<<gran oferta horizontal inicio>>>>>>>>>>>>>>>>>><
    private RecyclerView greatOffersRecyclewViewVertical;
    //<<<<<<<<<gran oferta horizontal fin >>>>>>>>>>>>

    //<<<<<<<<<gran oferta vertical inicio>>>>>>>>>>>>>>>>>><
    private RecyclerView newArrivalHorizontalRecyclewView;
    //<<<<<<<<<gran oferta vertical fin >>>>>>>>>>>>

    //<<<<<<<<<gran oferta vertical inicio>>>>>>>>>>>>>>>>>><
    private RecyclerView newArrivalVerticalRecyclewView;
    //<<<<<<<<<gran oferta vertical fin >>>>>>>>>>>>

    //<<<<<<<<<productos exclusivos horizontal inicio>>>>>>>>>>>>>>>>>><
    private RecyclerView exclusiveHorizontalRecyclewView;
    //<<<<<<<<<productos exclusivos vertical fin >>>>>>>>>>>>

    //<<<<<<<<<productos exclusivos vertical inicio>>>>>>>>>>>>>>>>>><
    private RecyclerView exclusiveVerticalRecyclewView;
    //<<<<<<<<<productos exclusivos vertical fin >>>>>>>>>>>>

    ////////////>>>>>>>>aqui mando llamar a la apirest para obtener la respuesta del json>>>>>
    public static ApiInterface apiInterface;

    TextView txtnom,txtlogin_exit;
    SharedPreferences mPref;


    public primerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_orders, container, false);
        onSetNavigationDrawerEvents();
        //llamada al apirest
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        inicia();
        mPref = this.getActivity().getSharedPreferences("typeUser", Context.MODE_PRIVATE);
        return view;
    }

    private void inicia() {

        //////////////Strip banner imagen sola////////////////

        strip_banner_image = (ImageView) view.findViewById(R.id.strip_banner_image);
        Call<Users> stripBannerCall = apiInterface.getStripBanners();
        stripBannerCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> stripBannerCall, Response<Users> response) {

                Glide.with(getContext()).load(response.body().getStrip_banner_image()).placeholder(R.drawable.logorobotics).into(strip_banner_image);

            }

            @Override
            public void onFailure(Call<Users> stripBannerCall, Throwable t) {

            }
        });
        //////////////Strip banner imagen sola////////////////

        //*****************inicia algoritmo para las categorias>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        recyclerviewCategory = (RecyclerView) view.findViewById(R.id.recyclerviewCategory);

        txtnom = (TextView) view.findViewById(R.id.txtNombreMenu);
        txtlogin_exit = (TextView) view.findViewById(R.id.login_Exit);
        SharedPreferences mPref = this.getActivity().getSharedPreferences("typeUser", Context.MODE_PRIVATE);

        String typeUser = mPref.getString("username", "");
        String typeApellido = mPref.getString("userapellido", "");
        String typeCorreo = mPref.getString("userCorreo", "");
        String typeId = mPref.getString("useridcliente", "");

        if(typeUser.equals(""))
        {
            txtlogin_exit.setVisibility(View.GONE);
            txtlogin_exit.setText("");
        }else
        {
            txtlogin_exit.setVisibility(View.VISIBLE);
            txtlogin_exit.setText("Salir");
        }

        txtnom.setText("Nombre de usuario "+typeUser+" "+typeApellido);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerviewCategory.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<>();
        Call<Users> categoryCall = apiInterface.getCategories();
        categoryCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> categoryCall, Response<Users> response) {

                categoryModelList = response.body().getCategory();
                catAdapter = new CatAdapter(categoryModelList, getContext());
                recyclerviewCategory.setAdapter(catAdapter);
                catAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Users> categoryCall, Throwable t) {

            }
        });


        //*****************finaliza algoritmo para las categorias>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        //*****************inicia algoritmo para los anuncios slider>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        recyclerViewBanner = (RecyclerView) view.findViewById(R.id.recyclerviewBanner);
        LinearLayoutManager layoutManagerBanner = new LinearLayoutManager(getContext());
        layoutManagerBanner.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewBanner.setLayoutManager(layoutManagerBanner);

        bannerModelList = new ArrayList<>();
        Call<Users> bannerCall = apiInterface.getBanners();
        bannerCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> bannerCall, Response<Users> response) {

                bannerModelList = response.body().getBanners();

                bannerAdapter = new BannerAdapter(bannerModelList, getContext());
                recyclerViewBanner.setAdapter(bannerAdapter);
                bannerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Users> bannerCall, Throwable t) {

            }
        });


        //*****************fin de algoritmo para las categorias>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


        //*****************inicia algoritmo listado simple >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        //con el gridlayoutmanager puedes manejar el numero de columnas del recyclerview sin problema
    //    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        recyclerViewSimple = (RecyclerView) view.findViewById(R.id.recyclerviewSimple);
        LinearLayoutManager layoutManagerSimpleVerticalSlider = new LinearLayoutManager(getContext());


        layoutManagerSimpleVerticalSlider.setOrientation(RecyclerView.VERTICAL); //aqui puede estar vertical o horizontal
        recyclerViewSimple.setLayoutManager(layoutManagerSimpleVerticalSlider);

      //  recyclerViewSimple.setLayoutManager(gridLayoutManager);


        simpleVerticalModelList = new ArrayList<>();
        Call<Users> random_shops = apiInterface.getRandomShops();
        random_shops.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                simpleVerticalModelList = response.body().getRandom_shops();

                productosVerticalAdapter = new productosVerticalAdapter(simpleVerticalModelList, getContext());
                recyclerViewSimple.setAdapter(productosVerticalAdapter);
                productosVerticalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });


        //*****************fin de algoritmo para listado simple>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        //*****************inicia grandes ordenes horizontal>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        greatGreatOffersHorizontal = (RecyclerView) view.findViewById(R.id.recyclerViewgreatOfferHorizontal);
        LinearLayoutManager layoutManagerGreatOffers = new LinearLayoutManager(getContext());
        layoutManagerGreatOffers.setOrientation(RecyclerView.HORIZONTAL); //aqui puede estar vertical o horizontal
        greatGreatOffersHorizontal.setLayoutManager(layoutManagerGreatOffers);

        greatOffersModel = new ArrayList<>();
        Call<Users> greatOffersCall = apiInterface.greatOffersShop();
        greatOffersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                greatOffersModel = response.body().getGreat_offers_shops();

                greatOffersAdapter = new GreatOffersAdapter(greatOffersModel, getContext());
                greatGreatOffersHorizontal.setAdapter(greatOffersAdapter);
                greatOffersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

        /* esto de aqui se sube para obtenerlo atraves del adaptador en el metodo de arriba onresponse
        greatOffersAdapter = new GreatOffersAdapter(greatOffersModel, getContext());
        greatGreatOffersHorizontal.setAdapter(greatOffersAdapter);
        greatOffersAdapter.notifyDataSetChanged();

         */

        //*****************fin de grandes ordenes horizontal>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


        //****************new great offers vertical slider start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        greatOffersRecyclewViewVertical = (RecyclerView) view.findViewById(R.id.greatOffersRecyclewViewVertical);
        LinearLayoutManager layoutManagerVerticalGreatOffers = new LinearLayoutManager(getContext());
        layoutManagerVerticalGreatOffers.setOrientation(RecyclerView.VERTICAL); //aqui puede estar vertical o horizontal
        greatOffersRecyclewViewVertical.setLayoutManager(layoutManagerVerticalGreatOffers);

        simpleVerticalModelList = new ArrayList<>();
        Call<Users> greatOffersVerticalCall = apiInterface.greatOffersVerticalShop();
        greatOffersVerticalCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                simpleVerticalModelList = response.body().getGreat_offers_shops_vertical();

                productosVerticalAdapter = new productosVerticalAdapter(simpleVerticalModelList, getContext());
                greatOffersRecyclewViewVertical.setAdapter(productosVerticalAdapter);
                productosVerticalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

        /*esto de aqui se sube para obtenerlo atraves del adaptador en el metodo de arriba onResponse
        simpleVerticalAdapter = new SimpleVerticalAdapter(simpleVerticalModelList, getContext());
        greatOffersRecyclewViewVertical.setAdapter(simpleVerticalAdapter);
        simpleVerticalAdapter.notifyDataSetChanged();

         */

        //*****************new great offers vertical slider fin>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


        //**********************************

        //*****************new arrival horizontal>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        newArrivalHorizontalRecyclewView = (RecyclerView) view.findViewById(R.id.newArrivalHorizontalRecyclewView);
        LinearLayoutManager layoutManagerhorizontalArrival = new LinearLayoutManager(getContext());
        layoutManagerhorizontalArrival.setOrientation(RecyclerView.HORIZONTAL); //aqui puede estar vertical o horizontal
        newArrivalHorizontalRecyclewView.setLayoutManager(layoutManagerhorizontalArrival);

        greatOffersModel = new ArrayList<>();
        Call<Users> arrivalCall = apiInterface.newArrivalsShops();

        arrivalCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                greatOffersModel = response.body().getNew_arrivals_shops();

                greatOffersAdapter = new GreatOffersAdapter(greatOffersModel, getContext());
                newArrivalHorizontalRecyclewView.setAdapter(greatOffersAdapter);
                greatOffersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });



        //*****************fin de grandes ordenes horizontal>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


        //*****************nuevos articulos slider>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        newArrivalVerticalRecyclewView = (RecyclerView) view.findViewById(R.id.newArrivalVerticalRecyclewView);
        LinearLayoutManager layoutManagerVerticalNewArrival = new LinearLayoutManager(getContext());
        layoutManagerVerticalNewArrival.setOrientation(RecyclerView.VERTICAL); //aqui puede estar vertical o horizontal
        newArrivalVerticalRecyclewView.setLayoutManager(layoutManagerVerticalNewArrival);


        simpleVerticalModelList = new ArrayList<>();

        Call<Users> arrivalVerticalCall = apiInterface.newArrivalsVerticalShops();
        arrivalVerticalCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                simpleVerticalModelList = response.body().getNew_arrivals_shops_vertical();
                productosVerticalAdapter = new productosVerticalAdapter(simpleVerticalModelList, getContext());
                newArrivalVerticalRecyclewView.setAdapter(productosVerticalAdapter);
                productosVerticalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

        /*
        simpleVerticalModelList.add(new SimpleVerticalModel(R.drawable.galaxy, "Samsung", "Samsung Galaxy S20+ Plus 5g/12gb Ram", "Colores: Negro / Gris / Azul.", "S/3.499,49", "Envio gratis apartir de 99 soles", "4.6"));
        simpleVerticalModelList.add(new SimpleVerticalModel(R.drawable.galaxy, "Motorola", "Samsung Galaxy S20+ Plus 5g/12gb Ram", "Colores: Negro / Gris / Azul.", "S/3.499,49", "Envio gratis apartir de 99 soles", "4.1"));
        simpleVerticalModelList.add(new SimpleVerticalModel(R.drawable.galaxy, "Huawei", "Samsung Galaxy S20+ Plus 5g/12gb Ram", "Colores: Negro / Gris / Azul.", "S/3.499,49", "Envio gratis apartir de 99 soles", "3.6"));


         */


        //*****************nuevos articulos slide del arrivalr>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        //*********************************

        //********************************productos exclusivos

        //*****************new arrival horizontal>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        exclusiveHorizontalRecyclewView = (RecyclerView) view.findViewById(R.id.exclusiveHorizontalRecyclewView);
        LinearLayoutManager layoutManagerexclusiveHorizon = new LinearLayoutManager(getContext());
        layoutManagerexclusiveHorizon.setOrientation(RecyclerView.HORIZONTAL); //aqui puede estar vertical o horizontal
        exclusiveHorizontalRecyclewView.setLayoutManager(layoutManagerexclusiveHorizon);

        greatOffersModel = new ArrayList<>();

        Call<Users> exclusiveCall = apiInterface.eightmmExclusive();
        exclusiveCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                greatOffersModel = response.body().getEightmm_exclusive();
                greatOffersAdapter = new GreatOffersAdapter(greatOffersModel, getContext());
                exclusiveHorizontalRecyclewView.setAdapter(greatOffersAdapter);
                greatOffersAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

        //*****************fin de grandes ordenes horizontal>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


        //*****************nuevos articulos slider>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        exclusiveVerticalRecyclewView = (RecyclerView) view.findViewById(R.id.exclusiveVerticalRecyclewView);
        LinearLayoutManager layoutManagerVexclusiveHorizontal = new LinearLayoutManager(getContext());
        layoutManagerVexclusiveHorizontal.setOrientation(RecyclerView.VERTICAL); //aqui puede estar vertical o horizontal
        exclusiveVerticalRecyclewView.setLayoutManager(layoutManagerVexclusiveHorizontal);


        simpleVerticalModelList = new ArrayList<>();

        Call<Users> exclusiveVerticalCall = apiInterface.eightmm_exclusive_vertical();
        exclusiveVerticalCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                simpleVerticalModelList = response.body().getEightmm_exclusive_vertical();
                productosVerticalAdapter = new productosVerticalAdapter(simpleVerticalModelList, getContext());
                exclusiveVerticalRecyclewView.setAdapter(productosVerticalAdapter);
                productosVerticalAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

        /*
        simpleVerticalModelList.add(new SimpleVerticalModel(R.drawable.galaxy, "Samsung", "Samsung Galaxy S20+ Plus 5g/12gb Ram", "Colores: Negro / Gris / Azul.", "S/3.499,49", "Envio gratis apartir de 99 soles", "4.6"));
        simpleVerticalModelList.add(new SimpleVerticalModel(R.drawable.galaxy, "Motorola", "Samsung Galaxy S20+ Plus 5g/12gb Ram", "Colores: Negro / Gris / Azul.", "S/3.499,49", "Envio gratis apartir de 99 soles", "4.1"));
        simpleVerticalModelList.add(new SimpleVerticalModel(R.drawable.galaxy, "Huawei", "Samsung Galaxy S20+ Plus 5g/12gb Ram", "Colores: Negro / Gris / Azul.", "S/3.499,49", "Envio gratis apartir de 99 soles", "3.6"));


         */


        //*****************nuevos articulos slide del arrivalr>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        //*********************************productos exclusivos
    }

    private void onSetNavigationDrawerEvents() {
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) view.findViewById(R.id.navigationView);
        navigationBar = (ImageView) view.findViewById(R.id.navigationBar);

        // aqui van los findviewid de cada elemento
        loginSignIUp = (RelativeLayout) view.findViewById(R.id.relativeLayout2);
        your_orders = (TextView) view.findViewById(R.id.your_orders);
        online_ordering_help = (TextView) view.findViewById(R.id.online_ordering_help);
        address_book = (TextView) view.findViewById(R.id.address_book);
        favorite_orders = (TextView) view.findViewById(R.id.favorite_orders);
        send_feedback = (TextView) view.findViewById(R.id.send_feedback);
        report_safety_emergency = (TextView) view.findViewById(R.id.report_safety_emergency);
        rate_playstore = (TextView) view.findViewById(R.id.rate_playstore);

        navigationBar.setOnClickListener(this);
        loginSignIUp.setOnClickListener(this);
        your_orders.setOnClickListener(this);
        online_ordering_help.setOnClickListener(this);
        address_book.setOnClickListener(this);
        favorite_orders.setOnClickListener(this);
        send_feedback.setOnClickListener(this);
        report_safety_emergency.setOnClickListener(this);
        rate_playstore.setOnClickListener(this);
        //  two.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigationBar:
                drawerLayout.openDrawer(navigationView, true);
                break;
            case R.id.relativeLayout2:

                if(txtlogin_exit.getText().toString().equals("Login"))
                {
                    Toast.makeText(getActivity(), "entre aqui", Toast.LENGTH_SHORT).show();
                    // Crear fragmento de tu clase

                }else
                {
                    Intent intent = new Intent(getActivity(), splashscreen.class);
                    startActivity(intent);
                    Animatoo.animateSlideDown(getActivity());
                    SharedPreferences settings = this.getActivity().getSharedPreferences("typeUser", Context.MODE_PRIVATE);
                    settings.edit().remove("username").apply();
                    settings.edit().remove("userapellido").apply();
                    settings.edit().remove("userCorreo").apply();
                    settings.edit().remove("useridcliente").apply();
                }



                //  Toast.makeText(getContext(), "loginSignIUp", Toast.LENGTH_SHORT).show();
                break;
            case R.id.your_orders:
              //  Toast.makeText(getContext(), "your_orders", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), formularioCompra.class);
                startActivity(intent);
                break;
            case R.id.address_book:
                Toast.makeText(getContext(), "address_book", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favorite_orders:
                Toast.makeText(getContext(), "favorite_orders", Toast.LENGTH_SHORT).show();
                break;
            case R.id.send_feedback:
                Toast.makeText(getContext(), "send_feedback", Toast.LENGTH_SHORT).show();
                break;
            case R.id.report_safety_emergency:
                Toast.makeText(getContext(), "report_safety_emergency", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}