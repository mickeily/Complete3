package com.example.complete.data;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.complete.Contenido;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Data
{
    String path = "/sdcard/DataEssential.csv";
    private static final int MY_PERMISSION_REQUEST_READ_EXTERNAL =1;
    private static final int MY_PERMISSION_REQUEST_WRITE_EXTERNAL =1;
    Contenido contenido;
    List<Contenido> listado= new ArrayList<>();

    public List<Contenido> readFile(Activity activity, String path)
    {
        checkPermission(activity);
        String archivoTemp[]= new String[30];
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader bf = new BufferedReader(fileReader);
            String linea = "";
            int contador =0;
            while ((linea = bf.readLine()) !=null)
            {
                archivoTemp = linea.split(",");
                contenido = new Contenido(archivoTemp);
                listado.add(contenido);
                contador++;
            }

        }catch (Exception e)
        {
            Toast.makeText(activity,e+"",Toast.LENGTH_LONG).show();
        }
        return listado;
    }

    public void checkPermission(Activity activity)
    {
        if(ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.READ_EXTERNAL_STORAGE))
            {

            }
            else
            {
                ActivityCompat.requestPermissions(activity,new String[]
                        {Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST_READ_EXTERNAL);
            }
        }

    }

}
