package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CompassListen extends AppCompatActivity {

    private Button back, forward,center;
    private Hanged hanged;
    private Save save = new Save();
    private MainActivity mainActivity = new MainActivity();
    private String[][] contenido= new String[50][20];
    private String[][] archivo= new String[5000][20];
    private String libro="";
    private String unidad="";
    private int ordenReproduccion;
    private int limite=0;
    private Play play;
    private Hilo  hilo= new Hilo();
    private String botonText;
    private CompassPublishing compassPublishing = new CompassPublishing();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_listen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        back=(Button) findViewById(R.id.b_back);
        forward=(Button) findViewById(R.id.b_forward);
        center=(Button) findViewById(R.id.b_center);
        cargar();
        setLimite();
        ordenReproduccion=0;
        botonText =compassPublishing.getBotonText();
        proyectar();

    }
    public String[][] cargar()
    {
        save.checkPermission(this);
        archivo = save.readFile(this,"/sdcard/DataEssential.csv");
        int contador =0;
        int contadorInterno =0;
        libro = mainActivity.getLibro();
        unidad  = mainActivity.getUnidad();

        while (archivo[contador][0]!=null)
        {
            if(archivo[contador][7].equalsIgnoreCase(libro))
            {
                if(archivo[contador][8].equalsIgnoreCase(unidad))
                {
                    contenido[contadorInterno]= archivo[contador];
                    contadorInterno++;
                }

                if (contadorInterno >= 20)
                {
                    break;
                }
            }
            contador++;
        }

        return contenido;
    }

    public void proyectar()
    {
        if(ordenReproduccion>=0 && ordenReproduccion<limite)
        {

            String palabra = contenido[ordenReproduccion][10];
            center.setText(palabra);
            center.setTextSize(48);


        }
    }

    public void setLimite()
    {

        while (contenido[limite][0]!=null)
        {
            limite++;
        }
    }

    public void adelantar(View view)
    {
        if(ordenReproduccion>=0 && ordenReproduccion<limite)
        {
           ordenReproduccion++;
        }
        proyectar();
    }

    public void retorceder(View view)
    {
        if(ordenReproduccion>=0 && ordenReproduccion<limite)
        {
            ordenReproduccion--;
        }
        proyectar();
    }

    public void proyectarRespuestas(View view)
    {
        if(botonText.equalsIgnoreCase("Read"))
        {
            center.setText(contenido[ordenReproduccion][6] + "\n\n" + contenido[ordenReproduccion][5]);
            center.setTextSize(24);
        }
        else
        {
            reproducir();
        }
    }

    public void reproducir()
    {
        play = new Play();
        String word= "/sdcard/Music/"+contenido[ordenReproduccion][10]+".mp3";
        String wordD= "/sdcard/Music/"+contenido[ordenReproduccion][10]+"_D.mp3";
        String wordE= "/sdcard/Music/"+contenido[ordenReproduccion][10]+"_E.mp3";

        play.reproducir(word,this);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        play.reproducir(wordD,this);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        play.reproducir(wordE,this);
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}