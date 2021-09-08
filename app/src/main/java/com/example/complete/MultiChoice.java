package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MultiChoice extends AppCompatActivity {

    private MainActivity mainActivity = new MainActivity();
    private Uncramble uncramble = new Uncramble();
    private Save save = new Save();
    Hanged hanged;
    List<String[]> contenido = new ArrayList<>();
    List<String[]> contenidoDegradable = new ArrayList<>();
    List<String[]> listaRespuestas = new ArrayList<>();
    String[] respCorrecta = new String[20];
    private String[][] archivo= new String[5000][20];
    private String libro="";
    private String unidad="";
    private int limite=0;
    private LinearLayout generico;
    private RadioGroup radioGroup;
    RadioButton btn;
    LinearLayout error;
    TextView arrPregunta,respuestaCorrecta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choice);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        radioGroup = (RadioGroup)findViewById(R.id.radio_group);
        respuestaCorrecta = (TextView)findViewById(R.id.resp_correcta);

        cargar();
        setContenidoDegradable();
        setLimite();
        seleccionarListaRespuestas();
        proyectarPregunta();
        proyectarRespuestas();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btn = group.findViewById(checkedId);
            }
        });
        int a =0;
    }

    public void cargar()//Este metodo carga la base de datos y filta y hace un arreglo con las 20 de la leccin
    {
        save.checkPermission(this);// se pide el permiso de lectura
        archivo = save.readFile(this,"/sdcard/DataEssential.csv");
        int contador =0;
        libro = mainActivity.getLibro();//esta variable guarda el libro seleccionado
        unidad  = mainActivity.getUnidad();// esta variable guarda la unidad seleccinada

        while (archivo[contador][0]!=null)// se recorre el archivo completo
        {
            if(archivo[contador][7].equalsIgnoreCase(libro)) // si esa dentro del libro seleccionado pasa
            {
                if(archivo[contador][8].equalsIgnoreCase(unidad))// si esta dontro de la unidad seleccionada pasa
                {
                    contenido.add(archivo[contador]);// se guarda dontro de la lista contenido
                }
            }
            contador++;
        }
    }

    public void setLimite() // se establese el limite que sera del tamagno de la leccion
    {
        limite = contenido.size();
    }

    public void seleccionarListaRespuestas()
    {
        int random=0;
        String resp[][]= new String[4][20];
        if(contenidoDegradable.size()>0)
        {
            listaRespuestas.clear();
            String corecta[][]= new String[4][20];
            corecta=contenidoDegradable.toArray(corecta);
            random = uncramble.setRandom(0,contenidoDegradable.size());
            respCorrecta = corecta[random];
            listaRespuestas.add(respCorrecta);
            while (listaRespuestas.size()<4)
            {
                resp= contenido.toArray(resp);
                random = uncramble.setRandom(0,limite);
                if (!listaRespuestas.contains(resp[random]))
                {
                    listaRespuestas.add(resp[random]);
                }
            }
        }
        else
        {
            crearRadioButon("");
        }
    }

    public void proyectarPregunta()
    {
        generico = (LinearLayout)findViewById(R.id.respuesta);
        arrPregunta = (TextView) findViewById(R.id.txt_pregunta);
        hanged = new Hanged();
        String pregunta =  hanged.convertSentence(respCorrecta[6],respCorrecta[10]);
        arrPregunta.setText(pregunta);
    }

    public void proyectarRespuestas()
    {
        Integer[] orden = new Integer[4];
        orden = ordenRespuestas();
        String[][] respuestas = new String[4][20];
        respuestas = listaRespuestas.toArray(respuestas);
        int contador =0;

        while (contador<4)
        {
            int a = orden[contador];
            crearRadioButon(respuestas[a][10]);
            contador++;
        }

    }

    public void crearRadioButon(String texto)
    {


        if (!texto.equalsIgnoreCase(""))
        {
            RadioButton  radio= new RadioButton(this);
            radio.setText(texto);
            radioGroup.addView(radio);
        }
        else
        {
            TextView txt = new TextView(this);
            txt.setText("End");
            txt.setTextSize(48);
            radioGroup.addView(txt);
        }
    }

    public void comprobar(View view)
    {

        String texto =btn.getText().toString();

        if(texto.equalsIgnoreCase(respCorrecta[10]))
        {

            contenidoDegradable.remove(respCorrecta);
            if(contenidoDegradable.size()==0)
            {
                radioGroup.removeAllViews();
                TextView txt = new TextView(this);
                txt.setText("End");
                txt.setTextSize(48);
                arrPregunta.setText("");
                radioGroup.addView(txt);
            }
            else
            {
                reiniciar();
            }
        }
        else
        {
            respuestaCorrecta.setText(respCorrecta[10]+": "+respCorrecta[6]);
            reiniciar();
        }

    }

    public void reiniciar()
    {
        radioGroup.removeAllViews();
        seleccionarListaRespuestas();
        proyectarPregunta();
        proyectarRespuestas();
    }

    public void  setContenidoDegradable()
    {
        for(String[] c: contenido)
        {
            contenidoDegradable.add(c);
        }
    }

    public Integer[] ordenRespuestas()
    {
        List<Integer> lista = new ArrayList<>();
        Integer[]numeros = new Integer[4];
        int numero=0;
        while (lista.size()<4)
        {
            numero = uncramble.setRandom(0,4);
            if(!lista.contains(numero))
            {
                lista.add(numero);
            }
        }
      numeros= lista.toArray(numeros);
        return numeros;
    }

}