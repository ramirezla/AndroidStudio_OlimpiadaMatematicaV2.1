package com.ehome.olimpiadamatematicav21;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static android.R.attr.resource;

public class MostrarRespuesta extends AppCompatActivity {

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_respuesta);

        DialogosAlertas mostrarDialogo = new DialogosAlertas(this);

        Bundle bundle = getIntent().getExtras();
        String idPruebaimportada = bundle.getString("idPruebaimportada");

        try {
            String RUTA_ARCHIVOSPRUEBAS = "/sdcard/Android/data/" + getPackageName() + "/file/soluciones/s";
            File imgFile = new File(RUTA_ARCHIVOSPRUEBAS + idPruebaimportada + ".jpg");

            if(imgFile.exists()){
                ManipularImagen img = new ManipularImagen(this);
                img.setImageResource(0);

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ImageView myImage = (ImageView) findViewById(R.id.iVMostrarRespuesta);
                myImage.setImageBitmap(myBitmap);

                img.setImageBitmap(myBitmap);
                img.setMaxZoom(4f);
                setContentView(img);
            } else {
                mostrarDialogo.showSettingsAlert();
                //Toast.makeText(MostrarRespuesta.this,"No esta implementada la respuesta para estas preguntas...",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            mostrarDialogo.showSettingsAlert();
            //Toast.makeText(getApplicationContext(), "Error en la imagen", Toast.LENGTH_LONG).show();
        }
    }
}