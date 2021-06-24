package com.sondershop.sondershopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sondershop.sondershopapp.detalleVenta.carritoCompra;
import com.sondershop.sondershopapp.detalleVenta.dbDirecciones.AppDatabaseDirecciones;
import com.sondershop.sondershopapp.detalleVenta.dbDirecciones.UserDirecciones;
import com.sondershop.sondershopapp.detalleVenta.detallePago;
import com.sondershop.sondershopapp.detalleVenta.detalleProducto;

public class formularioCompra extends AppCompatActivity {


    Spinner spinnerPais;
    ArrayAdapter<String> mAdapter;
    public String almacenaPais;

    String obtienetotal, obtienenombre;

    Button btn2;
    EditText txtdistritoF,txtcalleF,txtestadoF,txtcodigopostalF,txtnombre,txtapellidos,txtnumeros;

    String obtieneidproducto="";
    String obtieneidusuarioproducto="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_compra);

        MyToolbar.show(this, "Direccion de entrega", true);


        txtdistritoF = findViewById(R.id.txtdistritoF);
        txtcalleF = findViewById(R.id.txtcalleF);
        txtestadoF = findViewById(R.id.txtestadoF);
        txtcodigopostalF = findViewById(R.id.txtcodigopostalF);
        txtnombre = findViewById(R.id.txtnombreF);
        txtapellidos = findViewById(R.id.txtapellidoF);
        txtnumeros = findViewById(R.id.txtnumeroF);
        btn2 = findViewById(R.id.btnpago2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewUser(txtnombre.getText().toString(),txtapellidos.getText().toString(),txtnumeros.getText().toString(),almacenaPais,txtdistritoF.getText().toString()
                        ,txtcalleF.getText().toString(),txtestadoF.getText().toString(),txtcodigopostalF.getText().toString());
                Intent intent = new Intent(formularioCompra.this, carritoCompra.class);
                startActivity(intent);
               // finish();
            }
        });

        Bundle extras = getIntent().getExtras();

        //obtencion de datos por el metodo putextra
        if(extras != null)
        {
            Intent intent = getIntent();
            obtienetotal = extras.getString("precioTotal");
            obtienenombre = extras.getString("tituloProducto");
            obtieneidproducto = extras.getString("idproducto");
            obtieneidusuarioproducto = extras.getString("id_usuario_producto");
        }



        spinnerPais = (Spinner) findViewById(R.id.sPais);

        mAdapter = new ArrayAdapter<String>(formularioCompra.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.PaisesQ));
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
                if (seleccionEdad.equals("Peru")) {
                    almacenaPais = "Peru";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void saveNewUser(String nombre, String apellido, String numero,String almacenaPais,String distrito,
                             String calle, String cantidadArticulos,String codigoPostal) {
        AppDatabaseDirecciones db  = AppDatabaseDirecciones.getDbInstance(this.getApplicationContext());

        UserDirecciones user = new UserDirecciones();
        user.nombre = nombre;
        user.apellido = apellido;
        user.numero = numero;
        user.almacenaPais = almacenaPais;
        user.distrito = distrito;
        user.calle = calle;
        user.estado = cantidadArticulos;
        user.codigoPostal = codigoPostal;
        db.userDaoInterfdirecciones().insertDirecciones(user);

        finish();
    }

}