package com.sondershop.sondershopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class splashscreen extends AppCompatActivity {

    Thread timer;
    CountDownTimer cTimer = null;

    TextView texto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //PARA UTILIZAR LA PANTALLA COMPLETA SE AGREGAN ESTAS DOS LINEAS ANTES DEL ONCREATE Y EN EL MANIFEST SU LINEA
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //PARA UTILIZAR LA PANTALLA COMPLETA SE AGREGAN ESTAS DOS LINEAS ANTES DEL ONCREATE Y EN EL MANIFEST SU LINEA        setContentView(R.layout.activity_inicio_splash)
        setContentView(R.layout.activity_splashscreen);

        texto2 = findViewById(R.id.cajaOculta);
        reverseTimer(3, texto2);
    }


    public void reverseTimer(int Seconds, final TextView tv) {
        cTimer = new CountDownTimer(Seconds * 1000 + 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {//aqui va el codigo para mandar o ejecutar a terminar el lapso de tiempo

                Intent intent = new Intent(splashscreen.this, homeActivity.class);
                startActivity(intent);
                finish();

                // android.os.Process.killProcess(android.os.Process.myPid());
            }
        }.start();
    }
}
