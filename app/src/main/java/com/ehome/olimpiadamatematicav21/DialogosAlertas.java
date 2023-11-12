package com.ehome.olimpiadamatematicav21;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

public class DialogosAlertas extends MainActivity{
    private final Context mContext;

    public DialogosAlertas(Context mContext) {
        this.mContext = mContext;
    }

     /**
     * Función que permite mostrar un dialogo de alerta
     * Si se presiona Configuración lanza la aplicación de opciones del seteo del dispositivo
     * para configurar el GPS
     * */
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setear el titulo del dialogo de alerta
        alertDialog.setTitle("OJM V2.1");

        // Setear el mensaje del dialogo de alerta
        alertDialog.setMessage("Se esta trabajando en las Soluciones.. " +
                               "Se tiene soluciones solo para los años " +
                               "2010, 2012 y 2013  desde 1ero hasta " +
                               "5to año para las pruebas Nacional, " +
                               "Regional y Canguro.");

        alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                /*
                Intent intent = new Intent(v.getContext(), MostrarPrueba.class);
                intent.putExtra("idPruebaMostrar",idStExamen);
                startActivityForResult(intent, 0);
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                Intent intent = new Intent(this,Visor.class);
                String actionName= "nuestra.accion.nombreAccion";
                Intent intent = new Intent(actionName);
                activity.startActivity(intent);
                Intent intent = new Intent(activity, MiActivity.class);
                activity.start(intent);
                // Explicit Intent by specifying its class name
                Intent i = new Intent(FirstActivity.this, SecondActivity.class);
                // Starts TargetActivity
                startActivity(i);
                Intent intent = new Intent(DialogosAlertas.this, MostrarPrueba.class);
                startActivity(intent);
                Intent ListSong = new Intent(getApplicationContext(), List_song.class);
                startActivity(ListSong);
                */
                return;
            }
        });

        // Presionar la opción de cancelar
        /*
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        */
        // Mostrando el mensaje de dialogo
        alertDialog.show();
    }

    private static void start(Intent intent) {
    }
}

