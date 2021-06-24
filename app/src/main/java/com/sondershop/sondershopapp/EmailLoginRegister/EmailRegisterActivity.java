package com.sondershop.sondershopapp.EmailLoginRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiClient;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiInterface;
import com.sondershop.sondershopapp.OperationRetrofitApi.Users;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.homeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EmailRegisterActivity extends AppCompatActivity {

    private EditText name, email, password, apellido, numero;
    private Button regBtn,btnToken;
    TextView txtRegistra;
    public static ApiInterface apiInterface;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        init();
        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
    }

    private void init() {

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.passwordRT);
        regBtn = (Button) findViewById(R.id.button2);
        apellido = (EditText) findViewById(R.id.txtapellido);
        numero = (EditText) findViewById(R.id.txtnumero);
        btnToken = (Button) findViewById(R.id.buttontoken);

        txtRegistra = (TextView) findViewById(R.id.txtRegistraRT);

        txtRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailRegisterActivity.this, emailLoginActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(EmailRegisterActivity.this);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegistrationMysql();
            }
        });

        btnToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateToken();
            }
        });

    }

    private void generateToken() {
        /*
        $gen = md5(uniqid(mt_rand(), false));
        return $gen;
         */
    }

    private void RegistrationMysql() {
        final SharedPreferences.Editor editor = mPref.edit();
        String user_name = name.getText().toString().trim();
        String user_email = email.getText().toString().trim();
        String user_telefono = numero.getText().toString().trim();
        String user_apellido = apellido.getText().toString().trim();
        int estado = 0;
        String user_password = password.getText().toString().trim();

        if (TextUtils.isEmpty(user_name)) {
            name.setError("Nombre es requerido!");
        } else if (TextUtils.isEmpty(user_email)) {
            email.setError("Email es requerido");
        } else if (TextUtils.isEmpty(user_password)) {
            password.setError("Password es requerido");
        }  else if (TextUtils.isEmpty(user_telefono)) {
            numero.setError("Numero es requerido");
        } else if (TextUtils.isEmpty(user_apellido)) {
            apellido.setError("Apellido es requerido");
        } else {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Registrando...");
            dialog.setMessage("Porfavor espere estamos revisando credenciales");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.performEmailRegistration(user_name, user_email, user_apellido,user_telefono,estado,user_password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.body().getResponse().equals("ok")) {
                        Toast.makeText(EmailRegisterActivity.this, "Cuenta Creada en mysql", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Toast.makeText(EmailRegisterActivity.this, "password ingresado " + user_password, Toast.LENGTH_SHORT).show();

                        editor.putString("username", user_name);
                        editor.putString("userapellido", user_apellido);
                        editor.apply();
                        Intent intent = new Intent(EmailRegisterActivity.this, homeActivity.class);
                        startActivity(intent);
                        // Animatoo.animateSlideUp(MainActivity.this);
                        Animatoo.animateSwipeRight(EmailRegisterActivity.this);
                    } else if (response.body().getResponse().equals("Fallo")) {
                        Toast.makeText(EmailRegisterActivity.this, "fallo al crear cuenta", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else if (response.body().getResponse().equals("already")) {
                        Toast.makeText(EmailRegisterActivity.this, "ya existe este correo", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });
        }
    }
}