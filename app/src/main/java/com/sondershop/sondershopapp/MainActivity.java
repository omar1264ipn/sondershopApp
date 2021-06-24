package com.sondershop.sondershopapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.firebase.ui.auth.ui.email.EmailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.sondershop.sondershopapp.EmailLoginRegister.emailLoginActivity;
import com.sondershop.sondershopapp.adapter.PlateAdapter;
import com.sondershop.sondershopapp.EmailLoginRegister.EmailRegisterActivity;
import com.sondershop.sondershopapp.EmailLoginRegister.recoveryActivity;
import com.sondershop.sondershopapp.models.CategoryModel;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiClient;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiInterface;
import com.sondershop.sondershopapp.OperationRetrofitApi.Users;
import com.sondershop.sondershopapp.phoneloginRegister.PhoneRegisterActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<CategoryModel> plateModelList;
    private PlateAdapter plateAdapter;
    private LinearLayout emailContinue;
    private LinearLayout emailContinueBtn;
    TextView numeroRecovery;
    Button btnskip;
    ////////////>>>>>>>>aqui mando llamar a la apirest para obtener la respuesta del json>>>>>
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        btnskip = (Button) findViewById(R.id.buttonskip);
        numeroRecovery = (TextView) findViewById(R.id.txtrecuperaRT);
        emailContinue = (LinearLayout) findViewById(R.id.linear2);
        emailContinueBtn = (LinearLayout) findViewById(R.id.layout1);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setKeepScreenOn(true);
        recyclerView.setHasFixedSize(true);

        plateModelList = new ArrayList<>();
        Call<Users> categoryCall = apiInterface.getCategories();
        categoryCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                plateModelList = response.body().getCategory();
                plateAdapter = new PlateAdapter(plateModelList, getApplicationContext());
                recyclerView.setAdapter(plateAdapter);
                plateAdapter.notifyDataSetChanged();
                autoScroll();

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });


        emailContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, emailLoginActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(MainActivity.this);
            }
        });

        emailContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhoneRegisterActivity.class);
                startActivity(intent);
                Animatoo.animateSlideUp(MainActivity.this);
            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, homeActivity.class);
                startActivity(intent);
                // Animatoo.animateSlideUp(MainActivity.this);
                Animatoo.animateSwipeRight(MainActivity.this);
            }
        });

        numeroRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, recoveryActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeLeft(MainActivity.this);
            }
        });
    }

    public void autoScroll() {
        final int speedScroll = 0;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if (count == plateAdapter.getItemCount()) {
                    count = 0;
                }
                if (count < plateAdapter.getItemCount()) {
                    recyclerView.smoothScrollToPosition(++count);
                    handler.postDelayed(this, speedScroll);
                }

            }
        };
        handler.postDelayed(runnable, speedScroll);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences mPref = getApplicationContext().getSharedPreferences("typeUser", Context.MODE_PRIVATE);

        String typeUser = mPref.getString("username", "");

        if(typeUser.isEmpty())
        {
            Log.d("kimbo","estoy vacio");
        }else
        {
            Log.d("kimbo",typeUser);
            startActivity(new Intent(MainActivity.this, homeActivity.class));
            finish();
        }

        /*
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, homeActivity.class));
            finish();
        }

         */
    }
}