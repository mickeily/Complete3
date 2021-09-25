package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Rellenar extends AppCompatActivity {
    Save save;
    String archivo[][]=  new String[200][200];
    String contenido[];
    String contenidoCopia[];
    List<String> palabras = new ArrayList<>();
    List<String> archivoGen = new ArrayList<>();
    List<String> archivoGenCopia = new ArrayList<>();
    String cadenaDeGuion = "______";
    private String libro;
    private String unidad;
    MainActivity mainActivity;
    Uncramble uncramble = new Uncramble();
    TextView textoPrincipal;
    Button enter;
    int limite;

    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rellenar);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        textoPrincipal = (TextView)findViewById(R.id.texto_principal);
        linearLayout =(LinearLayout)findViewById(R.id.palabras);
        enter = (Button)findViewById(R.id.enter);
        enter.setEnabled(false);
        save = new Save();
        mainActivity = new MainActivity();
        cargar();
        setContenido();
        setLimite();
        seleccionarPalabras();
        proyectarTexto();
        proyectarPalabras();
    }

    public void cargar()
    {
        contenido = new String[1000];
        save.checkPermission(this);
        libro = mainActivity.getLibro();
        unidad  = mainActivity.getUnidad();
        String cuento = libro + "."+ unidad + ".xlsx";
        archivo = save.readFile(this,"/sdcard/Completing/BD.csv"); //Archivo que guarda todo el contenido

        int contador = 0;
        String linea = ""; // Extrae la linea del contenido que vamos a usar
        while (archivo[contador][0]!=null)
        {
           if(archivo[contador][3].equalsIgnoreCase(libro))
           {
               if(archivo[contador][4].equalsIgnoreCase(unidad))
               {
                   linea = archivo[contador][1];
                   setArchivoGen(linea); //Se llama al metodo donde se hara el extracto de lo que se va a trabajar
               }
           }
           contador++;
        }
    }

    public  void setArchivoGen(String linea)
    {
        String arr[] = linea.split(" ");
        for(String e : arr)
        {
            archivoGen.add(e);
        }
    }

    public void  setContenido()
    {
        contenidoCopia = new String[600];
        contenido = archivoGen.toArray(contenido);
        int contador =0;
        while (contenido[contador]!=null)
        {
            contenidoCopia[contador] = contenido[contador];
            contador++;
        }
    }

    public void setLimite()
    {
        limite=0;
        while (contenido[limite]!=null)
        {
            limite++;
        }
    }

    public void seleccionarPalabras()
    {
        int contador = 0;
        while (contador<limite/10)
        {
            int random = uncramble.setRandom(1,limite-1);
            {
                String antes = contenido[random-1];
                String actual = contenido[random];
                String siguiente = contenido[random+1];

                if(!(palabras.contains(antes)|| palabras.contains(actual)||palabras.contains(siguiente) ))
                {
                    palabras.add(actual);
                    contenidoCopia[random] = cadenaDeGuion;
                    contador++;
                }
            }
        }
    }

    public void proyectarTexto()
    {
        textoPrincipal.setText("");
        for(String e: contenidoCopia)
        {
            if (e!=null)
            {
                Character letra = e.charAt(0);
                if(letra.isUpperCase(letra))
                {
                    textoPrincipal.setText(textoPrincipal.getText() +"\n\n");
                }

                textoPrincipal.setText(textoPrincipal.getText() +" " + e);
            }
        }
    }

    public void proyectarPalabras()
    {
       for(String s: palabras)
       {
           crearBotones(s);
       }

    }

    public void crearBotones(String texto)
    {
        Button button = new Button(this);
        button.setText(texto);
        button.setAllCaps(false);
        linearLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                v.setVisibility(View.GONE);
                Button btn = (Button)v;
                comparar(btn.getText().toString());
                terminar();
            }
        });

    }

    public void comparar(String s)
    {
        int contador =0;
        while (contenidoCopia[contador]!=null)
        {
            if(contenidoCopia[contador].equalsIgnoreCase(cadenaDeGuion))
            {
                contenidoCopia[contador]= s;
                break;
            }
            contador++;
        }
        proyectarTexto();
    }

    public void terminar()
    {
        int contador =0;
        boolean teminar = true;
        while (contenidoCopia[contador]!=null)
        {
            if(contenidoCopia[contador].equalsIgnoreCase(cadenaDeGuion))
            {
                teminar = false;
                break;
            }
            contador++;
        }

        if(teminar==true)
        {
            enter.setEnabled(true);
        }
    }

    public void compararArreglos(View view)
    {
        palabras = new ArrayList<>();
        int contador =0;
        while (contenidoCopia[contador]!=null)
        {
          if(contenido[contador].equalsIgnoreCase(contenidoCopia[contador]))
          {
              contenidoCopia[contador]=contenido[contador];
          }
          else
          {
              palabras.add(contenido[contador]);
              contenidoCopia[contador]=cadenaDeGuion;
          }
          contador++;
        }

        proyectarTexto();
        proyectarPalabras();
    }






}
