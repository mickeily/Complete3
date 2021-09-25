package com.example.complete.controlador;

import android.app.Activity;
import android.widget.Toast;

import com.example.complete.Contenido;
import com.example.complete.MainActivity;
import com.example.complete.data.Data;

import java.util.ArrayList;
import java.util.List;

public class Controlador
{
    List<Contenido> contenidoDepurado;
    List<Contenido> contenidoGeneral;

    Data data = new Data();
    MainActivity mainActivity;
    String path = "/sdcard/DataEssential.csv";

    public List<Contenido> getContenidoDificil(Activity activity)
    {
        contenidoGeneral = new ArrayList<>();
        contenidoGeneral = data.readFile(activity,path);
        for(Contenido contenido: contenidoGeneral)
        {
            if(contenido.getDifficulty() == 1)
            {
                contenidoDepurado.add(contenido);
            }
        }
        return  contenidoDepurado;
    }

    public List<Contenido> getContenidoPorLibro(Activity activity)
    {
        mainActivity = new MainActivity();
        int libro =0;
        int unidad =0;
        libro =Integer.parseInt(mainActivity.getLibro());
        unidad =Integer.parseInt(mainActivity.getUnidad());

        if(libro>0)
        {
            contenidoGeneral = new ArrayList<>();
            contenidoDepurado = new ArrayList<>();
            contenidoGeneral = data.readFile(activity,path);
            for(Contenido contenido: contenidoGeneral)
            {
                if(contenido.getBook()==libro && contenido.getUnit() == unidad)
                {
                    contenidoDepurado.add(contenido);
                }
            }
        }
        else
        {
            Toast.makeText(activity,"You need to select the book",Toast.LENGTH_LONG).show();
        }
        return contenidoDepurado;
    }

    public int setRandom(int min, int max)
    {
        int randomNum = (int)(Math.random() * ( max - min ));
        return randomNum;
    }

    public String convertSentence(String oracion,String palabra)
    {
        String word ="";
        String sentence = oracion.toLowerCase();
        word = sentence.replace(palabra,"______");
        return word;
    }






}
