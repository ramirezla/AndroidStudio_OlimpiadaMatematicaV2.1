package com.ehome.olimpiadamatematicav21;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by root on 8/19/16.
 */
public class GuiasPDF extends MainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listar_guias_pdf);

    seleccionPDF();
    }

    private void seleccionPDF(){

        // selecciona la lista en pantalla segun su ID
        ListView lista1 = (ListView) findViewById(R.id.lVListadoArchivosPDF);

        // registra una accion para el evento click
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /// Obtiene el valor de la casilla elegida
                String itemSeleccionado = adapterView.getItemAtPosition(i).toString();

                final String[] archivoPDF = new String[1];
                String RUTA_ARCHIVOSPDF = "/sdcard/Android/data/" + getPackageName() + "/file/pdf/";

                switch (itemSeleccionado) {
                    case "Angulos y Congruencias de Triangulos":
                        archivoPDF[0] = "angulosycongruenciasdetriangulos";
                        break;
                    case "Conjugados Armonicos":
                        archivoPDF[0] = "conjugadosarmonicos";
                        break;
                    case "Cuadrilateros":
                        archivoPDF[0] = "cuadrilateros";
                        break;
                    case "Ejercicios de Desigualdades":
                        archivoPDF[0] = "ejerciciosdedesigualdades";
                        break;
                    case "Estrategia Basica en Solucion de Problemas Matematica":
                        archivoPDF[0] = "estrategiabasicasoluciondeproblemasmatematica";
                        break;
                    case "Estrategias de Resolucion de Problemas":
                        archivoPDF[0] = "estrategiasderesoluciondeproblemas";
                        break;
                    case "Introduccion a la Teoria de Numeros":
                        archivoPDF[0] = "introduccionteoriadenumeros";
                        break;
                    case "Juegos de Estrategia":
                        archivoPDF[0] = "juegosdeestrategia";
                        break;
                    case "Potencia y Eje Radical":
                        archivoPDF[0] = "potenciayejeradical";
                        break;
                    case "Problemas de Algebra":
                        archivoPDF[0] = "problemasdealgebra";
                        break;
                    case "Problemas de Desigualdades":
                        archivoPDF[0] = "problemasdesigualdades";
                        break;
                    case "Puntos Rectas y Circunferencias":
                        archivoPDF[0] = "puntosrectasycircunferencias";
                        break;
                    case "Semejanzas de Triangulos Teorema de Tales":
                        archivoPDF[0] = "semejanzasdetriangulosteoremadetales";
                        break;
                    case "Soluciones Angulos y Congruencias de Triangulos":
                        archivoPDF[0] = "solucionesangulosycongruenciasdetriangulos";
                        break;
                    case "Soluciones de Problemas Propuestos":
                        archivoPDF[0] = "solucionesdeproblemaspropuestos";
                        break;
                    case "Soluciones Problemas de Cuadrilateros":
                        archivoPDF[0] = "solucionesproblemasdecuadrilateros";
                        break;
                    case "Tarea de Funciones":
                        archivoPDF[0] = "tareadefunciones";
                        break;
                    case "Teorema Ceva-Menelao":
                        archivoPDF[0] = "teoremacevamenelao";
                        break;
                    case "Teorema de Stewart y Circulo de Apolonio":
                        archivoPDF[0] = "teoremadestewartycirculodeapolonio";
                        break;
                    case "Teoria de Numeros":
                        archivoPDF[0] = "teoriadenumeros";
                        break;
                    case "Trigonometria":
                        archivoPDF[0] = "trigonometria";
                        break;
                }

                File file = new File(RUTA_ARCHIVOSPDF + archivoPDF[0] + ".pdf");

                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(intent);
                    }
                    catch (ActivityNotFoundException e) {
                        Toast.makeText(GuiasPDF.this,"Instale una aplicacion que permita leer archivos PDF",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(GuiasPDF.this,"No existe el archivo pdf: " + file,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_lista_guia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}

