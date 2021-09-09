package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InCero extends AppCompatActivity {
    private Save save = new Save();
    LinearLayout layoutContenido;
    private List<String[]> contenido; // es el contenido con que se esta trabajando, en este caso
    // ttodo lo que se ha intentado pero esta en cero
    private String[][] archivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_cero);
        layoutContenido = (LinearLayout) findViewById(R.id.layout_contenido);
        setArchivo();
        setContenido();
        desplegarContenido();
    }
    public void setArchivo()
    {
        save.checkPermission(this);
        archivo= new String[5000][20];
        archivo = save.readFile(this,"/sdcard/DataEssential.csv");
    }
    public void setContenido()
    {
        contenido = new  ArrayList<>();

        for(String[] s: archivo)
        {
            if(s[0]==null)
            {
                break;
            }
            //s[2] es el puntaje que lleva al momento y s[3] son los intentos al momento
            else if ((s[2].equalsIgnoreCase("0"))&& (!s[3].equalsIgnoreCase("0")))
            {
                contenido.add(s);
            }
        }
    }
    public void desplegarContenido()
    {
        for(String[] s: contenido)
        {
            crearTextView(s);
        }

    }
    public void crearTextView(String[] cont)
    {
        String texto = cont[7] +":" +cont[8] +"  "+cont[6] +" (" + cont[10] +" )";
        TextView textView = new TextView(this);
        textView.setPadding(24,24,24,24);
        textView.setText(texto);
        layoutContenido.addView(textView);

    }


}