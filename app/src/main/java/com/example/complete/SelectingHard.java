package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class SelectingHard extends AppCompatActivity
{

    private String[][] archivo = new String[5000][20];
    private List<String[]> contenido ;
    private Save save = new Save();
    private String libro="";
    private String unidad="";
    private MainActivity mainActivity = new MainActivity();
    private String path = "/sdcard/DataEssential.csv";
    private LinearLayout layout;
    private Button select,diselect;
    private static int estatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting_hard);
        layout = (LinearLayout)findViewById(R.id.contenido3);
        diselect = (Button)findViewById(R.id.diselect);
        select = (Button)findViewById(R.id.select);
        cargar();
    }

    public void cargar()//Este metodo carga la base de datos y filta y hace un arreglo con las 20 de la leccin
    {
        save.checkPermission(this);// se pide el permiso de lectura
        archivo = save.readFile(this,path);
    }

    public void setContenido(int estatus)
    {
        contenido = new ArrayList<>();
        if(estatus==0)
        {

            libro = mainActivity.getLibro();
            unidad =mainActivity.getUnidad();
            int contador=0;

            while (archivo[contador][0]!=null)// se recorre el archivo completo
            {
                if (archivo[contador][7].equalsIgnoreCase(libro) && archivo[contador][8].equalsIgnoreCase(unidad))
                {
                    contenido.add(archivo[contador]);
                }
                contador++;
            }

        } else if(estatus==1)
        {
            int contador =0;

            while (archivo[contador][0]!=null)// se recorre el archivo completo
            {
                if (archivo[contador][13].equalsIgnoreCase(estatus+""))
                {
                    contenido.add(archivo[contador]);
                }
                contador++;
            }
        }

    }

    public void proyectarContenido()
    {
        if(!contenido.isEmpty())
        {
            for(String[] s: contenido)
            {
                crearCheckbutton(s);
            }
        }

    }

    public void crearCheckbutton(String[] arr)
    {
        CheckBox btn = new CheckBox(this);
        btn.setId(Integer.parseInt(arr[0]));
        btn.setText(arr[10] +"   (" +arr[7]+":"+arr[8]+")");
        layout.addView(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                setear(id);
            }
        });
    }

    public void setear(int id)
    {

       int asignacion=0;
       if(estatus==1)
       {
           asignacion =0;
       }
       else if(estatus==0)
        {
            asignacion =1;
        }
        archivo[id][13]=asignacion+"";
        save.checkPermissionWrite(this);
        save.saveFile(archivo,path);
    }

    public void seleccionar(View view)
    {
        layout.removeAllViews();
        estatus = 0;
        setContenido(estatus);
        proyectarContenido();
    }

    public void deseleccionar(View view)
    {
        layout.removeAllViews();
        estatus = 1;
        setContenido(estatus);
        proyectarContenido();
    }

}