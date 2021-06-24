package com.sondershop.sondershopapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sondershop.sondershopapp.models.ClientM;
import com.sondershop.sondershopapp.providers.AuthProvider;
import com.sondershop.sondershopapp.providers.ClientProvider;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;
import id.zelory.compressor.Compressor;

public class RegisterActivity extends AppCompatActivity {

    //creo instancias de los objetos
    AuthProvider mAuthProvider;
    ClientProvider mClientProvider;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputPassword;
    TextInputEditText mTextInputName;
    AlertDialog mDialog;
    Spinner spinnerPais;
    ArrayAdapter<String> mAdapter;
    private CircleImageView mCircleImageBack, mCircleImageNext;
    ImageView foto;
    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;
    public Bitmap thumb_bitmap = null;
    EditText profilePpick;
    Button btnseleccionar;
    public String almacenaPais;
    private DatabaseReference mDatabase;

    String almacenatoken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        foto = findViewById(R.id.imgObtiene);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDialog = new SpotsDialog.Builder().setContext(RegisterActivity.this).setMessage("Espere Un Momento").build();
        imgref = FirebaseDatabase.getInstance().getReference().child("Fotos_subidas"); //nodo padre
        storageReference = FirebaseStorage.getInstance().getReference().child("img_comprimidas");
        cargando = new ProgressDialog(this);

        //inicializo las instancias

        profilePpick = findViewById(R.id.solucionLink);
        spinnerPais = (Spinner) findViewById(R.id.spLanguage);
        mDialog = new SpotsDialog.Builder().setContext(RegisterActivity.this).setMessage("Espere Un Momento").build();

        mTextInputName = findViewById(R.id.textInputName);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputPassword = findViewById(R.id.textInputPassword);
        mAuthProvider = new AuthProvider();
        mClientProvider = new ClientProvider();
        mCircleImageBack = findViewById(R.id.circleImageBack);
        mCircleImageNext = findViewById(R.id.circleImageNext);
        btnseleccionar = findViewById(R.id.btdSeleccionarRegister);


        btnseleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(RegisterActivity.this);
            }
        });




        mCircleImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mCircleImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRegister();
            }
        });


        mAdapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.PaisesQ));
        spinnerPais.setAdapter(mAdapter);

        spinnerPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String seleccionEdad = spinnerPais.getSelectedItem().toString();

                if (seleccionEdad.equals("Mexico")) {
                    almacenaPais = "Mexico";
                }
                if (seleccionEdad.equals("United States")) {
                    almacenaPais = "United States";
                }
                if (seleccionEdad.equals("Japan")) {
                    almacenaPais = "Japan";
                }
                if (seleccionEdad.equals("Brasil")) {
                    almacenaPais = "Brasil";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageuri = CropImage.getPickImageResultUri(this, data);

            //recortar imagen
            CropImage.activity(imageuri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setRequestedSize(640, 480)
                    .setAspectRatio(2, 1).start(RegisterActivity.this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                File url = new File(resultUri.getPath());
             //   Picasso.with(this).load(url).into(foto);

                Picasso.get().load(url).into(foto);
                //comprimiendo imagen

                try {

                    thumb_bitmap = new Compressor(this)
                            .setMaxWidth(640)
                            .setMaxHeight(480)
                            .setQuality(90)
                            .compressToBitmap(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                final byte[] thumb_byte = byteArrayOutputStream.toByteArray();

                //fin del compresor

                int p = (int) (Math.random() * 25 + 1);
                int s = (int) (Math.random() * 25 + 1);
                int t = (int) (Math.random() * 25 + 1);
                int c = (int) (Math.random() * 25 + 1);
                int numero1 = (int) (Math.random() * 1012 + 2111);
                int numero2 = (int) (Math.random() * 1012 + 2111);
                String[] elementos = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "K", "L", "M", "N", "O",
                        "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
                final String aleatoriO = elementos[p] + elementos[s] + numero1 + elementos[t] + elementos[c] + numero2 + "comprimido.jpg";
                cargando.setTitle("Procesando Imagen...");
                cargando.setMessage("Espere porfavor.....");
                cargando.show();
                final StorageReference ref = storageReference.child(aleatoriO); //
                //final StorageReference ref = storageReference.child("nombredeimagen.jpg");
                UploadTask uploadTask = ref.putBytes(thumb_byte);
                //subir imagen en firebase
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if (!task.isSuccessful()) {
                            throw Objects.requireNonNull(task.getException());
                        }
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri downloaduri = task.getResult();
                        imgref.push().child("urlfoto").setValue(downloaduri.toString()); //nodo hijo de hijo
                        cargando.dismiss();
                        Toast.makeText(RegisterActivity.this, "Imagen Procesada con Exito", Toast.LENGTH_SHORT).show();
                        profilePpick.setText(downloaduri.toString());
                    }
                });

            }
        }
    }


    //creo el registro atravez de estos metodos que traen la informacion los models y providers
    void clickRegister() {
        final String name = mTextInputName.getText().toString();
        final String email = mTextInputEmail.getText().toString();
        final String password = mTextInputPassword.getText().toString();
        final String foto = profilePpick.getText().toString();
        final String paisRegister = almacenaPais.toString();
        final String idgroup = "NA";
        final String gkey = "";
        final int registrapuntosIni = 10;
        final int registraccessCirugia = 0;
        final int registraccessMedGeneral = 0;
        final int registraccessUrgencias = 0;
        final int registraccessOftalmolo = 0;
        final int registraccessEndocrino = 0;
        final int registraccessNeurologia = 0;
        final int registraccessTraumatologia = 0;
        final int registraccessDermatologia = 0;
        final int registraccessOtorrinola = 0;
        final int registraccessEpidemiologia = 0;
        final int registraccessExamen = 0;


        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()&& !foto.isEmpty()) {
            if (password.length() >= 6) {
                mDialog.show();
                register(name, email, password, foto, paisRegister, gkey, registrapuntosIni,idgroup,registraccessCirugia, registraccessMedGeneral, registraccessUrgencias, registraccessOftalmolo, registraccessEndocrino, registraccessNeurologia, registraccessTraumatologia, registraccessDermatologia, registraccessOtorrinola, registraccessEpidemiologia, registraccessExamen);

            } else {
                Toast.makeText(this, "El Password Debe Ser Mayor a 5 Caracteres!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe llenar Todos los Campos y subir foto", Toast.LENGTH_SHORT).show();
        }
    }

    void register(final String nameR, final String emailR, final String passwordR, final String fotoR, final String paisR, final String gkeR,final int idgroupR,final String puntosTotalesR,final int accessCirugia, final int accessMedGeneral, final int accessUrgencias, final int accessOftalmolo, final int accessEndocrino, final int accessNeurologia, final int accessTraumatologia, final int accessDermatologia, final int accessOtorrinola, final int accessEpidemiologia, final int accessExamen) {
        mAuthProvider.register(emailR, passwordR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.dismiss();
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    ClientM client = new ClientM(id, nameR, emailR, fotoR, paisR, gkeR, puntosTotalesR,idgroupR, accessCirugia, accessMedGeneral, accessUrgencias, accessOftalmolo, accessEndocrino, accessNeurologia, accessTraumatologia, accessDermatologia, accessOtorrinola, accessEpidemiologia, accessExamen); //debe tener la misma posicion del constructor
                    create(client);
                } else {
                    Toast.makeText(RegisterActivity.this, "Este correo ya esta registrado!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void create(ClientM client) {
        mClientProvider.create(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Se Registro Exitosamente al Cliente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //PARA QUE EL CLIENTE NO REGRESE A REGISTRARSE
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Este correo ya esta registrado!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
