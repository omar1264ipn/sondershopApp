package com.sondershop.sondershopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class ventanaLogin extends AppCompatActivity {

    //creo instancias de los objetos
    EditText mTextInputEmail;
    EditText mTextInputPassword;
    Button mButtonLogin, mButtonRegister;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    AlertDialog mDialog;
    SharedPreferences mPref;

    TextView cajarecupera;

    //autentication Google

    private CircleImageView mCircleAutenticationGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;

    /*
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(getApplicationContext(),homeActivity.class);
            startActivity(intent);
        }

    }

     */

    //fin Autentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //PARA UTILIZAR LA PANTALLA COMPLETA SE AGREGAN ESTAS DOS LINEAS ANTES DEL ONCREATE Y EN EL MANIFEST SU LINEA
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //PARA UTILIZAR LA PANTALLA COMPLETA SE AGREGAN ESTAS DOS LINEAS ANTES DEL ONCREATE Y EN EL MANIFEST SU LINEA        setContentView(R.layout.activity_inicio_splash)
        setContentView(R.layout.activity_ventana_login);

        mPref = getApplicationContext().getSharedPreferences("validadmiRT", MODE_PRIVATE);
        //inicializo los parametros en el oncreate

        mTextInputEmail = findViewById(R.id.textInputCorreo);
        mTextInputPassword = findViewById(R.id.textInputPassword);
        mButtonLogin = findViewById(R.id.btnlogin);
        mButtonRegister = findViewById(R.id.btnRegister);
        mTextInputEmail.setHintTextColor(getResources().getColor(R.color.colorWhite));
        mTextInputPassword.setHintTextColor(getResources().getColor(R.color.colorWhite));
        mCircleAutenticationGoogle = findViewById(R.id.circleGoogle);
        cajarecupera = findViewById(R.id.txtrecupera);
        //   stopService(new Intent(ventanaLogin.this, contructorMusica.class)); //detener musica de fondo

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        cajarecupera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ventanaLogin.this, "Recupera contraseña en construccion", Toast.LENGTH_SHORT).show();
            }
        });

        createRequest();

        mCircleAutenticationGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



        mDialog = new SpotsDialog.Builder().setContext(ventanaLogin.this).setMessage("Espere Un Momento").build();

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        //en los botones creo evenetos asociados con un click
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = mTextInputEmail.getText().toString();
                if (email.equals("omar1264ipn@gmail.com")) {

                    login();
                    mTextInputEmail.setText("");
                    mTextInputPassword.setText("");
                } else {
                    login();
                    mTextInputEmail.setText("");
                    mTextInputPassword.setText("");
                }
            }
        });


        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  stopService(new Intent(ventanaLogin.this, MyService.class)); //detener musica de fondo

                Registro();


            }
        });
    }

    //aqui va el codigo para autenticacion por google


    private void createRequest() {

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = (GoogleSignInAccount) task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),homeActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(ventanaLogin.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }



    //fin del codigo por autenticacion por google



    //creo el registro con este metodo
    private void Registro() {
        Intent intent = new Intent(ventanaLogin.this, RegisterActivity.class);
        startActivity(intent);

    }


    //con este metodo realizo el login con lo que recopile en las carpetas que contienen las clases de providers y models
    private void login() {
        String email = mTextInputEmail.getText().toString();
        String password = mTextInputPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {

            if (password.length() >= 6) {

                mDialog.show();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(ventanaLogin.this, homeActivity.class);
                            //  Intent intent = new Intent(ventanaLogin.this, SendNotif.class);
                            startActivity(intent);
                            //     Toast.makeText(loginActivity.this, "El login Se Realizo Correctamente", Toast.LENGTH_SHORT).show();
                            //  Intent intent = new Intent(ventanaLogin.this, Principal.class);
                        } else {
                            Toast.makeText(ventanaLogin.this, "Email o Password Incorrectos!", Toast.LENGTH_SHORT).show();
                        }
                        mDialog.dismiss();
                    }
                });
            } else {
                Toast.makeText(this, "El Password Debe Ser Mayor a 5 Caracteres!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "El Correo y Password Son Obligatorios!", Toast.LENGTH_SHORT).show();
        }
    }
}
