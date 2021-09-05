package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Uncramble extends AppCompatActivity {

    private MainActivity mainActivity;
    private Save save;
    private String libro;
    private String unidad;
    private String[][] archivo= new String[5000][10];
    private String[][] contenido= new String[50][10];
    private String[][] titulos = new String[500][20];
    private String[] sentence = new String[500];
    private List<Integer> newSentence = new ArrayList<>();
    private int orden =0;
    private int limite=0;
    private LinearLayout primerLayout,segundoLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncramble);
        mainActivity = new MainActivity();
        primerLayout = (LinearLayout) findViewById(R.id.primer_layout);
        primerLayout = (LinearLayout) findViewById(R.id.segundo_layout);

        save = new Save();
        cargar();
        buscarLimite();
        cargarOracion();
    }

    public void cargar()
    {
        save.checkPermission(this);
        archivo = save.readFile(this,"/sdcard/Completing/BD.csv");
        int contador =0;
        int contadorInterno =0;
        libro = mainActivity.getLibro();
        unidad  = mainActivity.getUnidad();

        while (archivo[contador][0]!=null)
        {
           if(archivo[contador][3].equalsIgnoreCase(libro))
           {
               if(archivo[contador][4].equalsIgnoreCase(unidad))
               {
                  contenido[contadorInterno]= archivo[contador];
                  contadorInterno++;
               }
           }
           contador++;
        }
    }

    public void cargarOracion()
    {
        if(orden<=limite)
        {
          sentence = contenido[orden][1].split(" ");
        }
        int contador =0;
        int random =0;
        while(newSentence.size()<sentence.length)
        {
           random = setRandom(0,sentence.length);

           if(!newSentence.contains(random))
           {
             newSentence.add(random);
             crearTextView(sentence[random]);
           }
        }
    }

    public void buscarLimite()
    {

        while (contenido[limite][0]!=null)
        {
          limite ++;
        }
    }

    public int setRandom(int min, int max )
    {
        int randomNum = (int)(Math.random() * ( max - min ));
        return randomNum;
    }

    public void crearTextView(String s)
    {
        TextView textView = new TextView(this);
        textView.setText(s);
        textView.setTextSize(18);
        textView.setPadding(24,0,0,24);
        primerLayout.addView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                prueba();

                //crearTextView2(v);
            }
        });


    }

    public void crearTextView2(View view)
    {
        segundoLayout.addView(view);
    }

    private void prueba()
    {
        Toast.makeText(this, "Hola mundo",Toast.LENGTH_LONG).show();
    }



}
