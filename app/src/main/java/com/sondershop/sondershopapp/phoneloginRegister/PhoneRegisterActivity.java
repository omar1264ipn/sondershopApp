package com.sondershop.sondershopapp.phoneloginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiClient;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiInterface;
import com.sondershop.sondershopapp.OperationRetrofitApi.Users;
import com.sondershop.sondershopapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneRegisterActivity extends AppCompatActivity {

    private EditText phone;
    private Button btnReg;
    TextView txtRegistra;
    public static ApiInterface apiInterface;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        init();
    }

    private void init() {
        phone = (EditText) findViewById(R.id.phone);
        btnReg = (Button) findViewById(R.id.button2);
        txtRegistra = (TextView) findViewById(R.id.txtRegistraRT);

        txtRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneRegisterActivity.this, PhoneLoginActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(PhoneRegisterActivity.this);
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register() {
        String user_phone = phone.getText().toString().trim();
        if (TextUtils.isEmpty(user_phone)) {
            phone.setError("Numero es necesario");
        } else {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Loading...");
            dialog.setMessage("Porfavor espere estamos revisando credenciales");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.performPhoneRegistration(user_phone);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.body().getResponse().equals("ok")) {

                        user_id = response.body().getUserId();
                        // Toast.makeText(PhoneRegisterActivity.this, use, Toast.LENGTH_SHORT).show();
                        Toast.makeText(PhoneRegisterActivity.this, "Cuenta Creada en mysql", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else if (response.body().getResponse().equals("Fallo")) {
                        Toast.makeText(PhoneRegisterActivity.this, "fallo al crear cuenta", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else if (response.body().getResponse().equals("already")) {
                        Toast.makeText(PhoneRegisterActivity.this, "ya existe este numero", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                    Toast.makeText(PhoneRegisterActivity.this, "No es posible por el momento registrarte", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}