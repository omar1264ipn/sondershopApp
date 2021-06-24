package com.sondershop.sondershopapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import com.sondershop.sondershopapp.EmailLoginRegister.EmailRegisterActivity;
import com.sondershop.sondershopapp.EmailLoginRegister.emailLoginActivity;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiClient;
import com.sondershop.sondershopapp.OperationRetrofitApi.ApiInterface;
import com.sondershop.sondershopapp.OperationRetrofitApi.Users;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.RegisterActivity;
import com.sondershop.sondershopapp.SendNotificationPack.Token;
import com.sondershop.sondershopapp.homeActivity;
import com.sondershop.sondershopapp.splashscreen;
import com.sondershop.sondershopapp.ventanaLogin;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class perfilFragment extends Fragment {

    TextView miperfil,txtnombreObtiene;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, ref, reference;
    private CircleImageView mCircleImageexit,imagePerfil;
    String id, obtienegrupo, autorizagrupo;
    String getkey0; //identificacion del token de usuario
    String obtienecorreo;


    private EditText email, password;
    private Button btnlogin;
    Button txtRegistra;
    ////////////>>>>>>>>aqui mando llamar a la apirest para obtener la respuesta del json>>>>>
    public static ApiInterface apiInterface;
    SharedPreferences mPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_four, container, false);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        mPref = this.getActivity().getSharedPreferences("typeUser", Context.MODE_PRIVATE);
        String typeUser = mPref.getString("username", "");

        if(typeUser.equals(""))
        {

        }else
        {
            Toast.makeText(getActivity(), "Ya estas Logueado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), splashscreen.class);
            startActivity(intent);
            Animatoo.animateSlideDown(getActivity());
        }

        email = (EditText) root.findViewById(R.id.textInputCorreo);
        password = (EditText) root.findViewById(R.id.textInputPassword);
        btnlogin = (Button) root.findViewById(R.id.btnlogin);
        txtRegistra = (Button) root.findViewById(R.id.btnRegister);

        txtRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmailRegisterActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(getActivity());
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        return root;
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

            ProgressDialog dialog = new ProgressDialog(getActivity());
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
                        Toast.makeText(getActivity(), user_nombre+" "+user_apellido, Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(emailLoginActivity.this, "logeado con exito", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        editor.putString("username", user_nombre);
                        editor.putString("userapellido", user_apellido);
                        editor.putString("userCorreo", email.getText().toString().trim());
                        editor.putString("useridcliente", user_id);
                        editor.apply();
                        Log.d("kimbo",user_nombre);
                        Log.d("kimbo",email.getText().toString().trim());
                        Intent intent = new Intent(getActivity(), homeActivity.class);
                        startActivity(intent);
                        // Animatoo.animateSlideUp(MainActivity.this);
                        Animatoo.animateSwipeRight(getActivity());

                    } else if (response.body().getResponse().equals("notienecuenta")) {
                        Toast.makeText(getActivity(), "verifica tu cuenta o contraseña", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });
        }
    }

    /*

    private void obtenerinfoUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Token token = new Token(refreshToken);
        Log.d("estrella","si entre estrella");
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
        id = mAuth.getCurrentUser().getUid(); //aqui obtengo el id del usuario logueado
        mDatabase.child("Users").child("Clients").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    String oNombre = dataSnapshot.child("name").getValue().toString();
                    String oPais = dataSnapshot.child("pais").getValue().toString();
                    String olinkFoto = dataSnapshot.child("fotourl").getValue().toString();
                    String oPointsPlayer = dataSnapshot.child("puntosTotales").getValue().toString();
                    obtienecorreo = dataSnapshot.child("email").getValue().toString();

                    if (obtienecorreo.equals("omar1264ipn@gmail.com")) {
                        //  mButtonADMIN.setVisibility(View.VISIBLE);
                        Log.d("prueba", "si es administrador");

                    } else {
                        Log.d("prueba", "No es administrador");
                    }
                    String date = new SimpleDateFormat("dd").format(new Date());
                    int numEntero2 = Integer.parseInt(date);

                    getkey0 = dataSnapshot.getKey();
                    if (!getkey0.equals("")) {
                        Log.d("kimbo6", getkey0);
                        //actualizar parametros
                        Map<String, Object> personMap = new HashMap<>();
                        personMap.put("gkeR", getkey0.toString());
                        rootRef.child("Users").child("Clients").child(id).updateChildren(personMap);
                        Log.d("vacio", "esta vacio");
                    } else {
                        Log.d("lleno", "tiene datos");
                    }
                    //   mPointsPlayer.setText(oPointsPlayer.toString());
                    //    mTextViewNombre.setText(oNombre.toString());
                    //    mTextViewPais.setText(oPais.toString());
                    txtnombreObtiene.setText("Bienvenido "+oNombre.toString());
                    Picasso.with(getContext()).load(olinkFoto).into(imagePerfil);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    /*
    private void vamosalogin() {
        Intent i = new Intent(getActivity(), splashscreen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

     */

}