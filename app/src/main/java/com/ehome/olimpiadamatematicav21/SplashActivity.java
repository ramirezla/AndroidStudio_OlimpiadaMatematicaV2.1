package com.ehome.olimpiadamatematicav21;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by root on 8/21/16.
 */
public class SplashActivity extends MainActivity {
    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 5000; // 5 segundos, Se define en milisegundos.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tenemos una plantilla llamada splash.xml donde mostraremos la información que queramos (logotipo, etc.)
        setContentView(R.layout.splash);
        Toast.makeText(SplashActivity.this,"Espere por favor.....",Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 5 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                //Toast.makeText(SplashActivity.this,"Espere por favor.....",Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
