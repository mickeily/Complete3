package com.example.complete;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.FileReader;

public class Save
{
    String path = "/sdcard/DataEssential.csv";
    private static final int MY_PERMISSION_REQUEST_READ_EXTERNAL =1;


    public String[][] readFile(Activity activity)
    {
        String archivo[][] = new String[5000][30];
        String archivoTemp[]= new String[30];
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader bf = new BufferedReader(fileReader);
            String linea = "";
            int contador =0;
            while ((linea = bf.readLine()) !=null)
            {
                archivoTemp = linea.split(",");
                archivo[contador]= archivoTemp;
                contador++;
            }

        }catch (Exception e)
        {
            Toast.makeText(activity,e+"",Toast.LENGTH_LONG).show();
        }
       return archivo;
    }

    public String[][] readFile(Activity activity,String path)
    {
        String archivo[][] = new String[5000][30];
        String archivoTemp[]= new String[30];
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader bf = new BufferedReader(fileReader);
            String linea = "";
            int contador =0;
            while ((linea = bf.readLine()) !=null)
            {
                archivoTemp = linea.split(",");
                archivo[contador]= archivoTemp;
                contador++;
            }

        }catch (Exception e)
        {
            Toast.makeText(activity,e+"",Toast.LENGTH_LONG).show();
        }
        return archivo;
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

    public String[][] readFile(Activity activity,String path,String split)
    {
        String archivo[][] = new String[5000][30];
        String archivoTemp[]= new String[30];
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader bf = new BufferedReader(fileReader);
            String linea = "";
            int contador =0;
            while ((linea = bf.readLine()) !=null)
            {
                archivoTemp = linea.split(split);
                archivo[contador]= archivoTemp;
                contador++;
            }

        }catch (Exception e)
        {
            Toast.makeText(activity,e+"",Toast.LENGTH_LONG).show();
        }
        return archivo;
    }




}
