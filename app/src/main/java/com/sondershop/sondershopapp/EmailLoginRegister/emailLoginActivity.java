package com.sondershop.sondershopapp.EmailLoginRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.sondershop.sondershopapp.MainActivity;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiClient;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiInterface;
import com.sondershop.sondershopapp.OperationRetrofitApi.Users;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.homeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class emailLoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btnlogin;
    Button txtRegistra;
    ////////////>>>>>>>>aqui mando llamar a la apirest para obtener la respuesta del json>>>>>
    public static ApiInterface apiInterface;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //llamada al apirest
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        init();
        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
    }

    private void init() {
        email = (EditText) findViewById(R.id.textInputCorreo);
        password = (EditText) findViewById(R.id.textInputPassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        txtRegistra = (Button) findViewById(R.id.btnRegister);

        txtRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(emailLoginActivity.this, EmailRegisterActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(emailLoginActivity.this);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void Login() {
        final SharedPreferences.Editor editor = mPref.edit();
        String user_email = email.getText().toString().trim();
        String user_password = password.getText().toString().trim();

        if (TextUtils.isEmpty(user_email)) {
            email.setError("Email es requerido");
        } else if (TextUtils.isEmpty(user_password)) {
            password.setError("Password es requerido");
        } else {

            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Loading...");
            dialog.setMessage("Porfavor espere estamos revisando credenciales");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.performEmailLogin(user_email, user_password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {

                    if (response.body().getResponse().equals("ok")) {
                        String user_id = response.body().getUserId();
                        String user_nombre = response.body().getUserNombre();
                        String user_apellido = response.body().getUserApellido();
                        String user_email = response.body().getUserCorreo();
                        Toast.makeText(emailLoginActivity.this, user_nombre+" "+user_apellido, Toast.LENGTH_SHORT).show();
                      //  Toast.makeText(emailLoginActivity.this, "logeado con exito", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        editor.putString("username", user_nombre);
                        editor.putString("userapellido", user_apellido);
                        editor.putString("userCorreo", email.getText().toString().trim());
                        editor.putString("useridcliente", user_id);
                        editor.apply();
                        Log.d("kimbo",user_nombre);
                        Log.d("kimbo",email.getText().toString().trim());
                        Intent intent = new Intent(emailLoginActivity.this, homeActivity.class);
                        startActivity(intent);
                        // Animatoo.animateSlideUp(MainActivity.this);
                        Animatoo.animateSwipeRight(emailLoginActivity.this);

                    } else if (response.body().getResponse().equals("notienecuenta")) {
                        Toast.makeText(emailLoginActivity.this, "verifica tu cuenta o contraseña", Toast.LENGTH_SHORT).show();
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