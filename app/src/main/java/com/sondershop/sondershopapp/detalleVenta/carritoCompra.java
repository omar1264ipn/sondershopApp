package com.sondershop.sondershopapp.detalleVenta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.sondershop.sondershopapp.MyToolbar;
import com.sondershop.sondershopapp.R;
import com.sondershop.sondershopapp.detalleVenta.db.AppDatabase;
import com.sondershop.sondershopapp.detalleVenta.db.User;
import com.sondershop.sondershopapp.detalleVenta.dbDirecciones.AppDatabaseDirecciones;
import com.sondershop.sondershopapp.detalleVenta.dbDirecciones.UserDirecciones;
import com.sondershop.sondershopapp.formularioCompra;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class carritoCompra extends AppCompatActivity {

    private carritoUserAdapter carritoUserAdapter;
    private carritoUserAdapterDirecciones carritoUserAdapterDirecciones;
    public static TextView precioTotal,direccionSeleccionada;
    String obtieneNombreUsuario,obtieneApellido,fechaActual,obtienetotal, obtienenombre,obtienecorreo,obtieneid;
    CircleImageView mbtnentrega;
    RecyclerView recyclerDirecciones;

    static AppDatabaseDirecciones roomDatabaseClase;

    static AppDatabase roomDatabaseClaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compra);
    //    Toolbar toolbar = findViewById(R.id.toolbar);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Carrito de Compras");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        roomDatabaseClase = Room.databaseBuilder(getApplicationContext(),AppDatabaseDirecciones.class,"DB_NAME2").allowMainThreadQueries().build();
        roomDatabaseClaseUser = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"DB_NAME").allowMainThreadQueries().build();


        precioTotal = findViewById(R.id.precioTotal);
        direccionSeleccionada = findViewById(R.id.direccionentrega);
        mbtnentrega = findViewById(R.id.btnagregar);

        mbtnentrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(carritoCompra.this, formularioCompra.class);
                startActivity(intent);
            }
        });

        initRecyclerView();
        loadUserList();
        loadUserDireccionesList();
        inicia();
    }

    private void inicia()
    {
        SharedPreferences mPref = getApplicationContext().getSharedPreferences("typeUser", Context.MODE_PRIVATE);
        obtieneNombreUsuario = mPref.getString("username", "");
        obtieneApellido = mPref.getString("userapellido", "");
        obtienecorreo = mPref.getString("userCorreo", "");
        obtieneid = mPref.getString("useridcliente", "");
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        carritoUserAdapter = new carritoUserAdapter(this);
        recyclerView.setAdapter(carritoUserAdapter);

        recyclerDirecciones = findViewById(R.id.recyclerViewDirecciones);
        recyclerDirecciones.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDirecciones = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerDirecciones.addItemDecoration(dividerItemDirecciones);
        carritoUserAdapterDirecciones = new carritoUserAdapterDirecciones(this);
        recyclerDirecciones.setAdapter(carritoUserAdapterDirecciones);
    }

    private void loadUserList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<User> userList =db.userDaoInterf().getAllUsers();
        carritoUserAdapter.setUserList(userList);
    }

    public void actualizaProductos()
    {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<User> userList =db.userDaoInterf().getAllUsers();
        carritoUserAdapter.setUserList(userList);
    }

    public void actualizalista()
    {
      //  carritoUserAdapterDirecciones = new carritoUserAdapterDirecciones(this);
      //  recyclerDirecciones.setAdapter(carritoUserAdapterDirecciones);
        //para actualizar el metodo de la carga de las direcciones
        AppDatabaseDirecciones dbdirecciones = AppDatabaseDirecciones.getDbInstance(this.getApplicationContext());
        List<UserDirecciones> userDirecciones = dbdirecciones.userDaoInterfdirecciones().getAllDirecciones();
        carritoUserAdapterDirecciones.setUserList2(userDirecciones);
        carritoUserAdapterDirecciones.notifyDataSetChanged();
      //  carritoUserAdapterDirecciones.notifyAll();
      //  Toast.makeText(this, "actualizado", Toast.LENGTH_SHORT).show();
    }


    private void loadUserDireccionesList() {
        AppDatabaseDirecciones dbdirecciones = AppDatabaseDirecciones.getDbInstance(this.getApplicationContext());
        List<UserDirecciones> userDirecciones = dbdirecciones.userDaoInterfdirecciones().getAllDirecciones();
        carritoUserAdapterDirecciones.setUserList2(userDirecciones);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadUserList();
            loadUserDireccionesList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //insertar datos
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.icon_add: {

                Toast.makeText(this, "Comprando", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        return true;

    }

    private void refrescaFecha() throws ParseException
    {
        TimeZone myTimeZone = TimeZone.getTimeZone("America/Mexico_City");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        simpleDateFormat.setTimeZone(myTimeZone);
        fechaActual = simpleDateFormat.format(new Date());
    }


    private void sendMailPagocontraentrega() {

        /*
        Toast.makeText(this, "Compra Realizada Correctamente"+obtienecorreo, Toast.LENGTH_SHORT).show();
        String mail = obtienecorreo;
        String message = "Compra Exitosa - Sondershop";
        String subject = "Estimado "+obtieneNombreUsuario+" Se le envian los datos de su orden de venta sondershop con el tipo de pago identificado pagocontraentrega, se le enviara su producto lo mas pronto posible ";
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, message,subject);
        javaMailAPI.execute();
        Log.d("revisa",mail);

         */
    }

    private void inyecta1()
    {
        /*
        //Datos inicializados
        int metodopago = 2;
        String status = "no";
        String metodopagoString = "2";
        String logisticaSonder = "no";
        String statusPaquete = "no entregado";
        //Datos inicializados
        String idCliente = obtieneid; //int
        String totalPago = obtienetotal; //double
        String fecha = fechaActual; //datetime
        String metodoPagoRealizado = metodopagoString; //string
        String pagado = status; //string
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Registrando...");
        dialog.setMessage("Porfavor espere estamos finalizando tu compra");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        Call<Users> call = apiInterface.performRegistraPagoEntrega(idCliente,totalPago,fecha,metodoPagoRealizado,pagado,almacenapais,txtdistritoF,txtcalleF,txtestadoF,txtcodigopostalF,obtieneid,obtieneidusuarioproducto,"1",totalPago,totalPago,logisticaSonder,statusPaquete);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.body().getResponse().equals("ok")) {
                    Toast.makeText(carritoCompra.this, "Gracias por tu pago", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    sendMailPagocontraentrega();
                    //   Intent intent = new Intent(EmailRegisterActivity.this, homeActivity.class);
                    //   startActivity(intent);
                    // Animatoo.animateSlideUp(MainActivity.this);
                    //   Animatoo.animateSwipeRight(EmailRegisterActivity.this);
                } else if (response.body().getResponse().equals("Fallo")) {
                    Toast.makeText(carritoCompra.this, "fallo al realizar la compra", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else if (response.body().getResponse().equals("already")) {
                    Toast.makeText(carritoCompra.this, "fallo al realizar la compra 2", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Users> call, Throwable t) {
            }
        });

         */
    }


    private void createPdf() throws FileNotFoundException {

        String tipoPago="Pago Contra Entrega";
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath,"sondershopDetalle.pdf");
        OutputStream outputStream= new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument= new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.setMargins(0,0,0,0);

        Drawable d1= getDrawable(R.drawable.backgroundpdf); //aqui cargo la cabecera
        // Drawable d1= getDrawable(R.color.white); //aqui cargo la cabecera
        Bitmap bitmap1 = ((BitmapDrawable)d1).getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,100,stream1);
        byte[] bitmapData1 = stream1.toByteArray();

        ImageData imageData1 = ImageDataFactory.create(bitmapData1);
        Image image1 = new Image(imageData1);

        Drawable d2= getDrawable(R.drawable.app4); //aqui cargo la cabecera
        Bitmap bitmap2 = ((BitmapDrawable)d2).getBitmap();
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.JPEG,100,stream2);
        byte[] bitmapData2 = stream2.toByteArray();

        ImageData imageData2 = ImageDataFactory.create(bitmapData2);
        Image image2 = new Image(imageData2);

        float columnWidth1[] = {120,220,120,100};
        Table table1 = new Table(columnWidth1);
        table1.setMargin(20);

        table1.addCell(new Cell().add(new Paragraph("Empresa").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Sondershop").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Numero de Contacto").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("933-002-585").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Cliente").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph(obtieneNombreUsuario+" "+obtieneApellido).setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Fecha").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph(fechaActual).setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Tipo de Pago").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph(tipoPago).setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Direccion de Entrega").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));
      //  table1.addCell(new Cell().add(new Paragraph("Distrito: "+txtdistritoF+" Calle: "+txtcalleF+" Estado: "+txtestadoF).setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(Border.NO_BORDER));


        DeviceRgb secColor = new DeviceRgb(8,106,119);
        float columnWidth2[] = {220,120,100,120};
        Table table2 = new Table(columnWidth2);
        table2.setMargin(20);

        table2.addCell(new Cell().add(new Paragraph("Item Descripcion").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)).setBackgroundColor(secColor));
        table2.addCell(new Cell().add(new Paragraph("Precio").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)).setBackgroundColor(secColor));
        table2.addCell(new Cell().add(new Paragraph("Cantidad").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)).setBackgroundColor(secColor));
        table2.addCell(new Cell().add(new Paragraph("Total").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)).setBackgroundColor(secColor));

        table2.addCell(new Cell().add(new Paragraph(obtienenombre).setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph(obtienetotal).setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("1").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph(obtienetotal).setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));

        /*
        table2.addCell(new Cell().add(new Paragraph("Motorola").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("$4,800").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("1").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("$4,800").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));

        table2.addCell(new Cell().add(new Paragraph("Nokia").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("$5,000").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("2").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("$10,000").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));

        table2.addCell(new Cell().add(new Paragraph("HTC").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("$3,000").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("1").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("$3,000").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));


         */
        table2.addCell(new Cell().add(new Paragraph("By SonderShop").setFontSize(14).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)));
        table2.addCell(new Cell().add(new Paragraph("")));
        table2.addCell(new Cell().add(new Paragraph("Total").setFontSize(18).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)).setBackgroundColor(secColor));
        table2.addCell(new Cell().add(new Paragraph(precioTotal.getText().toString()).setFontSize(18).setFontColor(ColorConstants.WHITE)).setBorder(new SolidBorder(secColor,1)).setBackgroundColor(secColor));

        document.add(image1.setFixedPosition(0,0));
        document.add(image2);
        document.add(table1);
        document.add(table2);
        document.close();
        Toast.makeText(this, "Pdf Creado y descargando en memoria con el nombre sondershopDetalle.pdf", Toast.LENGTH_SHORT).show();
    }
}