package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[][] archivo = new String[400][10];
    private LinearLayout linearLayout;
    private Button boton;
    private Save save = new Save();
    private Button uncramble;

    private static String libro;
    private static String unidad;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        linearLayout = (LinearLayout) findViewById(R.id.layout_titulos);
        uncramble = (Button) findViewById(R.id.uncramble);
        linearLayout.setScrollBarFadeDuration(1);
        save.checkPermission(this);
        cargar();
        proyectarTitulos();
    }

    public void cargar()
    {
        archivo = save.readFile(this,"/sdcard/Completing/ListadoDeUnidades.csv");
    }

    public void proyectarTitulos()
    {
        for (String[] e : archivo)
        {
            if (e[0]!=null)
            {
                crearBotones(e);
            }

        }
    }

    public void crearBotones(String[] titulo)
    {
        int id = Integer.parseInt(titulo[0] + titulo[1]);
        boton= new Button(this);
        boton.setId(id);
        boton.setText("B "+titulo[0] +" U "+ titulo[1] +"\t\t"+titulo[2]);
        boton.setTextSize(16);
        boton.setGravity(0);
        boton.setAllCaps(false);
        linearLayout.addView(boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                setLibroAndUnid(id+"");

            }
        });
    }
    private void setLibroAndUnid(String lib)
    {
        libro = lib.substring(0,1);
        unidad = lib.substring(1,lib.length());
    }

    public void uncrambleActivity(View view)
    {
        if(libro!=null)
        {
            Intent uncramble = new Intent(this,Uncramble.class);
            startActivity(uncramble);
        }
        else
        {
            Toast.makeText(this,"Please select the contest",Toast.LENGTH_LONG).show();
        }

    }

    public String getLibro()
    {
        return libro;
    }

    public String getUnidad()
    {
        return unidad;
    }

    public void hangedActivity(View view)
    {
        if(libro!=null)
        {
            Intent hanged = new Intent(this,Hanged.class);
            startActivity(hanged);
        }
        else
        {
            Toast.makeText(this,"Please select the contest",Toast.LENGTH_LONG).show();
        }

    }

    public void rellenarActivity(View view)
    {
        if(libro!=null)
        {
            Intent rellenar = new Intent(this,Rellenar.class);
            startActivity(rellenar);
        }
        else
        {
            Toast.makeText(this,"Please select the contest",Toast.LENGTH_LONG).show();
        }

    }

    public void infoActivity(View view)
    {

        Intent info = new Intent(this,Info.class);
        startActivity(info);

    }

}
