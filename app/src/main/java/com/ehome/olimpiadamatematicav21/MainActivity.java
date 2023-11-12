package com.ehome.olimpiadamatematicav21;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String seleccionAlcance, seleccionAnnoExamen, seleccionAnnoGrado;
    Spinner spinnerAlcance, spinnerAnnoExamen, spinnerAnnoGrado;
    private ProgressDialog pd = null;
    // Listado de archivos en formatos pdfs para soporte de estudio, se colocan en minúsculas y sin expensión .pdf
    String[] listadoPDF = {
            "angulosycongruenciasdetriangulos","conjugadosarmonicos","cuadrilateros","ejerciciosdedesigualdades",
            "estrategiabasicasoluciondeproblemasmatematica","estrategiasderesoluciondeproblemas",
            "introduccionteoriadenumeros","juegosdeestrategia","potenciayejeradical","problemasdealgebra",
            "problemasdesigualdades","puntosrectasycircunferencias","semejanzasdetriangulosteoremadetales",
            "solucionesangulosycongruenciasdetriangulos","solucionesdeproblemaspropuestos",
            "solucionesproblemasdecuadrilateros","tareadefunciones","teoremacevamenelao",
            "teoremadestewartycirculodeapolonio","teoriadenumeros","trigonometria" };
    //Se obtiene el total de arhivos que tendrá el directorio.
    Integer totalListaPDFS = listadoPDF.length;

    // Listado de todas las preguntas desde 1er año hasta 5to año para cada una de las competencias: Canguro, Regional y Nacional.desde 2004 hasta 2016
    // Nomenclatura del nombre del archivo ejemplo: pABCXXXX
    // p:    Pregunta.
    // A:    Alcance de la Prueba: 1->Canguro, 2->Regional y 3->Nacional
    // BC:   Grado de la prueba 7->7 1er año, 8-> 2do año, 9->3er año, 10->4to año, 11->5to año,
    // XXXX: Año de la prueba, desde 2004 hasta 2016
    String[] listadoPREGUNTAS = {
            "p1102004","p1102005","p1102006","p1102007","p1102008","p1102009","p1102010","p1102011","p1102012",
            "p1102013","p1102014","p1102015","p1102016","p1102017","p1112004","p1112005","p1112006","p1112007","p1112008",
            "p1112009","p1112010","p1112011","p1112012","p1112013","p1112014","p1112015","p1112016","p1112017",
            "p172004","p172005","p172006","p172007","p172008","p172009","p172010","p172011","p172012","p172013","p172014",
            "p172015","p172016","p172017","p182004","p182005","p182006","p182007","p182008","p182009","p182010","p182011",
            "p182012","p182013","p182014","p182015","p182016","p182017","p192004","p192005","p192006","p192007","p192008",
            "p192009","p192010","p192011","p192012","p192013","p192014","p192015","p192016","p192017","p2102004","p2102005",
            "p2102006","p2102007","p2102008","p2102009","p2102010","p2102011","p2102012","p2102013","p2102014",
            "p2102015","p2102016","p2102017","p2112004","p2112005","p2112006","p2112007","p2112008","p2112009","p2112010",
            "p2112011","p2112012","p2112013","p2112014","p2112015","p2112016","p2112017","p272004","p272005","p272006","p272007",
            "p272008","p272009","p272010","p272011","p272012","p272013","p272014","p272015","p272016","p272017","p282004",
            "p282005","p282006","p282007","p282008","p282009","p282010","p282011","p282012","p282013","p282014",
            "p282015","p282016","p282017","p292004","p292005","p292006","p292007","p292008","p292009","p292010","p292011",
            "p292012","p292013","p292014","p292015","p292016","p292017","p3102004","p3102005","p3102006","p3102007","p3102008",
            "p3102009","p3102010","p3102011","p3102012","p3102013","p3102014","p3102015","p3102016","p3102017","p3112004",
            "p3112005","p3112006","p3112007","p3112008","p3112009","p3112010","p3112011","p3112012","p3112013",
            "p3112014","p3112015","p3112016","p3112017","p372004","p372005","p372006","p372007","p372008","p372009","p372010",
            "p372011","p372012","p372013","p372014","p372015","p372016","p372017","p382004","p382005","p382006","p382007",
            "p382008","p382009","p382010","p382011","p382012","p382013","p382014","p382015","p382016","p382017","p392004","p392005",
            "p392006","p392007","p392008","p392009","p392010","p392011","p392012","p392013","p392014","p392015","p392016","p392017" };
    //Se obtiene el total de arhivos que tendrá el directorio.
    Integer totalListaPREGUNTAS = listadoPREGUNTAS.length;

    // Listado de todas las soluciones disponibles desde 1er año hasta 5to año para cada una de las competencias: Canguro, Regional y Nacional.desde 2004 hasta 2016
    // Nomenclatura del nombre del archivo ejemplo: sABCXXXX
    // s:    Solución.
    // A:    Alcance de la Solucion: 1->Canguro, 2->Regional y 3->Nacional
    // BC:   Grado de la solucion 7->7 1er año, 8-> 2do año, 9->3er año, 10->4to año, 11->5to año,
    // XXXX: Año de la solucion, desde 2004 hasta 2016
    String[] listadoSOLUCIONES = {
            "s172010","s172012","s172013","s182010","s182012","s182013","s192010","s192012","s192013","s1102010",
            "s1102012","s1102013","s1112010","s1112012","s1112013","s272010","s272012","s272013","s282010","s282012",
            "s282013","s292010","s292012","s292013","s2102010","s2102012","s2102013","s2112010","s2112012","s2112013",
            "s372010","s372012","s372013","s382010","s382012","s382013","s392010","s392012","s392013","s3102010",
            "s3102012","s3102013","s3112010","s3112012","s3112013" };
    //Se obtiene el total de arhivos que tendrá el directorio.
    Integer totalListaSOLUCIONES = listadoSOLUCIONES.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se copian los archivos pdf y jpg, desde el directorio raw hacia el directorio
        // interno para ser leído por la aplicación en:
        // /sdcard/Android/data/com.ehome.olimpiadamatematicav13/file/
        // Crea el directorio para los archivos JPGs si no existe y copia los archivos al almacenamiento local.
        try {
            copiarArchivosDirectorios();
        } catch (IOException e) {
            e.printStackTrace();
        }

        spinnerAlcance = (Spinner) findViewById(R.id.sPAlcance);
        ArrayAdapter adapterAlcane = ArrayAdapter.createFromResource(this, R.array.arregloAlcance, R.layout.texto_spinner);
        spinnerAlcance.setAdapter(adapterAlcane);
        spinnerAlcance.setOnItemSelectedListener(this);

        spinnerAnnoGrado = (Spinner) findViewById(R.id.sPAnnoE);
        ArrayAdapter adapterRango = ArrayAdapter.createFromResource(this, R.array.arregloAnnoEstudio, R.layout.texto_spinner);
        spinnerAnnoGrado.setAdapter(adapterRango);
        spinnerAnnoGrado.setOnItemSelectedListener(this);

        spinnerAnnoExamen = (Spinner) findViewById(R.id.sPAnnosExamen);
        ArrayAdapter adapterAnnoExamen = ArrayAdapter.createFromResource(this, R.array.arregloAnnoExamen, R.layout.texto_spinner);
        spinnerAnnoExamen.setAdapter(adapterAnnoExamen);
        spinnerAnnoExamen.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        final Integer idAlcance, idAnnoGrado;
        final String idStExamen;
        Button botonProceder = null;

        seleccionAlcance = String.valueOf(spinnerAlcance.getSelectedItem());
        seleccionAnnoGrado = String.valueOf(spinnerAnnoGrado.getSelectedItem());
        seleccionAnnoExamen = String.valueOf(spinnerAnnoExamen.getSelectedItem());

        switch (seleccionAlcance) {
            case "Canguro":
                idAlcance = 1;
                break;
            case "Regional":
                idAlcance = 2;
                break;
            default:
                idAlcance = 3;
                break;
        }

        // Se coloca desde 1er grado hasta 6to grado pensando en un futuro colocar las pruebas y soluciones que corresponden a primaria,
        // a pesar que en esta versión de la aplicación no es utilizado.
        // Se le asigna un valor consecutivo desde primaria hasta bachillerato, siendo 7->7to año, 8->8vo año, 9->9no año, 10->4to año y 11->5to año.
        switch (seleccionAnnoGrado) {
            case "1er Grado":
                idAnnoGrado = 1;
                break;
            case "2do Grado":
                idAnnoGrado = 2;
                break;
            case "3er Grado":
                idAnnoGrado = 3;
                break;
            case "4to Grado":
                idAnnoGrado = 4;
                break;
            case "5to Grado":
                idAnnoGrado = 5;
                break;
            case "6to Grado":
                idAnnoGrado = 6;
                break;
            case "1er Año":
                idAnnoGrado = 7;
                break;
            case "2do Año":
                idAnnoGrado = 8;
                break;
            case "3er Año":
                idAnnoGrado = 9;
                break;
            case "4to Año":
                idAnnoGrado = 10;
                break;
            default:
                idAnnoGrado = 11;
                break;
        }

        idStExamen = String.valueOf(idAlcance) + String.valueOf(idAnnoGrado) + seleccionAnnoExamen;

        botonProceder = (Button) findViewById(R.id.bTProceder);
        assert botonProceder != null;
        botonProceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MostrarPrueba.class);
                intent.putExtra("idPruebaMostrar",idStExamen);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Accion cuando se selecciona acercade
            case R.id.id_acercade:
                Intent i = new Intent(this, AcercaDe.class);
                startActivity(i);
                break;
            // Accion cuando se selecciona las Guias
            case R.id.id_guias:
                Intent iG = new Intent(this, GuiasPDF.class);
                startActivity(iG);
                break;
            // Por default no hay accion
            default:
                break;
        }
        return true;
    }

    private void copiarArchivosDirectorios() throws IOException {

        // Se copian los archivos pdf y jpg, desde el directorio raw hacia el directorio
        // interno para ser leído por la aplicación en:
        // /sdcard/Android/data/com.ehome.olimpiadamatematicav13/file/
        // Crea el directorio para los archivos JPGs si no existe y copia los archivos al almacenamiento local.

        String RUTA_ARCHIVOSPDF = "/sdcard/Android/data/" + getPackageName() + "/file/pdf/";
        String RUTA_ARCHIVOSPREGUNTAS = "/sdcard/Android/data/" + getPackageName() + "/file/preguntas/";
        String RUTA_ARCHIVOSSOLUCIONES = "/sdcard/Android/data/" + getPackageName() + "/file/soluciones/";

        try {
            // Borrar los archivos cuando sea necesario limpiar el directorio, se descomenta y
            // luego de limpiar se comenta nuevamente, es para prueba manual, no es usado ni llamado
            // directamente desde la programación.

            //borrarArchivosPDFS();
            //borrarArchivosPREGUNTAS();
            //borrarArchivosSOLUCIONES();

            File folderPDF = new File(RUTA_ARCHIVOSPDF);
            if (!folderPDF.isDirectory()) { // Si no existe el directorio lo crea
                folderPDF.mkdirs();
            }
            //Se creo el array de tipo File con el contenido del directorio para obtener la cantidad de archivos en el directorio.
            File[] totalArchivosPDF = folderPDF.listFiles();

            // Total de archivos que deberian de estar en el directoio pdf se guarda en la variable global totalListaPDFS
            if (totalArchivosPDF.length !=  totalListaPDFS) { // Si hay diferencia entre el total de archivos en el listado y en el directorio, entonces se copian los archivos.
                copiarArchivosPDFS();
            }

            File folderPREGUNTAS = new File(RUTA_ARCHIVOSPREGUNTAS);
            if (!folderPREGUNTAS.isDirectory()) { // Si no existe el directorio lo crea
                folderPREGUNTAS.mkdirs();
            }
            //Se creo el array de tipo File con el contenido del directorio para obtener la cantidad de archivos en el directorio.
            File[] totalArchivosPREGUNTAS = folderPREGUNTAS.listFiles();

            // Total de archivos que deberian de estar en el directoio pdf se guarda en la variable global totalListaPREGUNTAS
            if (totalArchivosPREGUNTAS.length != totalListaPREGUNTAS) { // Si hay diferencia entre el total de archivos en el listado y en el directorio, entonces se copian los archivos.
                copiarArchivosPREGUNTAS();
            }

            File folderSOLUCIONES = new File(RUTA_ARCHIVOSSOLUCIONES);
            if (!folderSOLUCIONES.isDirectory()) { // Si no existe el directorio lo crea
                folderSOLUCIONES.mkdirs();
            }
            //Se creo el array de tipo File con el contenido del directorio para obtener la cantidad de archivos en el directorio.
            File[] totalArchivosSOLUCIONES = folderSOLUCIONES.listFiles();

            // Total de archivos que deberian de estar en el directoio pdf se guarda en la variable global totalListaSOLUCIONES
            if (totalArchivosSOLUCIONES.length != totalListaSOLUCIONES) { // Si hay diferencia entre el total de archivos en el listado y en el directorio, entonces se copian los archivos.
                copiarArchivosSOLUCIONES();
            }

        } catch (Exception ex) {
            Toast.makeText(this, "No existen los archivos Preguntas, Soluciones y/o guías en el directorio res/raw", Toast.LENGTH_LONG).show();
        }
    }

    // los archivos PDFs se reciven en el directorio raw y se copian al directorio
    // donde la aplicación los pueda utilizar.
    private void copiarArchivosPDFS() throws IOException {
        String RUTA_ARCHIVOSPDF = "/sdcard/Android/data/" + getPackageName() + "/file/pdf/";

        totalListaPDFS = listadoPDF.length;

        // resource tendrá el ID del directorio raw donde se encuentran los archivos pdf originales sin extensión.
        int resource;
        String nombreDestino;
        String tipoArchivo = ".pdf";

        for( int i = 0 ; i < listadoPDF.length ; i++ ){
            nombreDestino = listadoPDF[i];
            resource = getResources().getIdentifier(nombreDestino, "raw", "com.ehome.olimpiadamatematicav21");
            InputStream is = getResources().openRawResource(resource);
            OutputStream os = new FileOutputStream(RUTA_ARCHIVOSPDF + nombreDestino + tipoArchivo);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
            os.close();
            is.close();
        }
    }

    // los archivos PDFs se reciven en el directorio raw y se copian al directorio
    // donde la aplicación los pueda utilizar.
    private void copiarArchivosPREGUNTAS() throws IOException {
        String RUTA_ARCHIVOSPREGUNTAS = "/sdcard/Android/data/" + getPackageName() + "/file/preguntas/";

        totalListaPREGUNTAS = listadoPREGUNTAS.length;

        // resource tendrá el ID del directorio raw donde se encuentran los archivos jpg originales sin extensión.
        int resource;
        String nombreDestino;
        String tipoArchivo = ".jpg";


        for (int i = 0; i < listadoPREGUNTAS.length; i++) {
            nombreDestino = listadoPREGUNTAS[i];
            resource = getResources().getIdentifier(nombreDestino, "raw", "com.ehome.olimpiadamatematicav21");
            InputStream is = getResources().openRawResource(resource);
            OutputStream os = new FileOutputStream(RUTA_ARCHIVOSPREGUNTAS + nombreDestino + tipoArchivo);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
            os.close();
            is.close();
        }
    }

    // los archivos PDFs se reciven en el directorio raw y se copian al directorio
    // donde la aplicación los pueda utilizar.
    private void copiarArchivosSOLUCIONES() throws IOException {
        String RUTA_ARCHIVOSSOLUCIONES = "/sdcard/Android/data/" + getPackageName() + "/file/soluciones/";

        totalListaSOLUCIONES = listadoSOLUCIONES.length;

        // resource tendrá el ID del directorio raw donde se encuentran los archivos jpg originales sin extensión.
        int resource;
        String nombreDestino;
        String tipoArchivo = ".jpg";

        for( int i = 0 ; i < listadoSOLUCIONES.length ; i++ ){
            nombreDestino = listadoSOLUCIONES[i];
            resource = getResources().getIdentifier(nombreDestino, "raw", "com.ehome.olimpiadamatematicav21");
            InputStream is = getResources().openRawResource(resource);
            OutputStream os = new FileOutputStream(RUTA_ARCHIVOSSOLUCIONES + nombreDestino + tipoArchivo);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
            os.close();
            is.close();
        }
    }

    private void borrarArchivosPDFS() throws IOException {
        String RUTA_ARCHIVOSPDF = "/sdcard/Android/data/" + getPackageName() + "/file/pdf/";
        String tipoArchivo = ".pdf";

        for( int i = 0 ; i < listadoPDF.length ; i++ ) {
            File nombreDestino = new File(RUTA_ARCHIVOSPDF + listadoPDF[i] + tipoArchivo);
            if (nombreDestino.delete())
                System.out.println("El Archivo ha sido borrado satisfactoriamente");
            else
                System.out.println("El Archivo no puede ser borrado");
        }
    }

    private void borrarArchivosPREGUNTAS() throws IOException {
        String RUTA_ARCHIVOSPREGUNTAS = "/sdcard/Android/data/" + getPackageName() + "/file/preguntas/";
        String tipoArchivo = ".jpg";

        for( int i = 0 ; i < listadoPREGUNTAS.length ; i++ ) {
            File nombreDestino = new File(RUTA_ARCHIVOSPREGUNTAS + listadoPREGUNTAS[i] + tipoArchivo);
            if (nombreDestino.delete())
                System.out.println("El Archivo ha sido borrado satisfactoriamente");
            else
                System.out.println("El Archivo no puede ser borrado");
        }
    }

    private void borrarArchivosSOLUCIONES() throws IOException {
        String RUTA_ARCHIVOSSOLUCIONES = "/sdcard/Android/data/" + getPackageName() + "/file/soluciones/";
        String tipoArchivo = ".jpg";

        for( int i = 0 ; i < listadoSOLUCIONES.length ; i++ ) {
            File nombreDestino = new File(RUTA_ARCHIVOSSOLUCIONES + listadoSOLUCIONES[i] + tipoArchivo);
            if (nombreDestino.delete())
                System.out.println("El Archivo ha sido borrado satisfactoriamente");
            else
                System.out.println("El Archivo no puede ser borrado");
        }
    }
}
