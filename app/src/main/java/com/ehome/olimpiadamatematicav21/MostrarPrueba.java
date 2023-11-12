package com.ehome.olimpiadamatematicav21;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MostrarPrueba extends AppCompatActivity {

    //@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_prueba);

        //ActionBar actionBar = getActionBar();
        //Seteando el icono
        //actionBar.setIcon(R.drawable.logo);
        //actionBar.setSubtitle("Prueba");

        Bundle bundle = getIntent().getExtras();
        String idPruebaimportada = bundle.getString("idPruebaMostrar");

        try {
            String RUTA_ARCHIVOSPRUEBAS = "/sdcard/Android/data/" + getPackageName() + "/file/preguntas/p";
            File imgFile = new  File(RUTA_ARCHIVOSPRUEBAS + idPruebaimportada + ".jpg");

            if(imgFile.exists()){
                ManipularImagen img = new ManipularImagen(this);
                img.setImageResource(0);

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ImageView myImage = (ImageView) findViewById(R.id.iVMostrarPrueba);
                myImage.setImageBitmap(myBitmap);

                img.setImageBitmap(myBitmap);
                img.setMaxZoom(4f);
                setContentView(img);
            } else {
                Toast.makeText(MostrarPrueba.this,"No existe la Prueba",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error en la imagen", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar_pruebas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.id_ayuda:

                Bundle bundle = getIntent().getExtras();
                String idPruebaimportada = bundle.getString("idPruebaMostrar");

                /* Se crea un Intent para llamar la actividad MostrarRespuesta y
                se le pasa como parámetro la variable idPruebaimportada que es de tipo String,
                La variable contiene el id de la prueba que necesitamos buscar.
                luego se llama la actividad con startActivity(intent);
                */

                Intent intent = new Intent(MostrarPrueba.this, MostrarRespuesta.class);
                intent.putExtra("idPruebaimportada", idPruebaimportada);
                startActivityForResult(intent, 0);
        }
        return true;
    }
}


