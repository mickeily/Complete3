package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;
import com.example.complete.controlador.Controlador;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompassFlashCard extends AppCompatActivity {
    Controlador controlador;
    private  int orden=0;
    List<Contenido> archivoOriginal = new ArrayList<>();
    List<Contenido> archivoModificado = new ArrayList<>();
    LinearLayout layoutContenido;
    Contenido pal;
    Contenido def;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_flash_card);
        layoutContenido = (LinearLayout) findViewById(R.id.contenido);
        setArchivo();
        setArchivoModificado();
        proyectarContenido();
    }

    public void setArchivo() {
        controlador = new Controlador();
        archivoOriginal = controlador.getContenidoPorLibro(this);
    }

    public void setArchivoModificado() {
        Random randomizer = new Random();
        while (archivoModificado.size() < archivoOriginal.size()) {
            Contenido random = archivoOriginal.get(randomizer.nextInt(archivoOriginal.size()));
            if (!archivoModificado.contains(random)) {
                archivoModificado.add(random);
            }
        }
    }

    public void proyectarContenido() {
        int contador = 0;
        while (contador < archivoOriginal.size()) {
            String oracion = controlador.convertSentence(archivoModificado.get(contador).getDefinition(),archivoModificado.get(contador).getWord());
            crearComponentes(oracion, archivoOriginal.get(contador).getWord());
            contador++;
        }
    }

    public void crearComponentes(String definicion, String palabra) {
        try {

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.removeAllViews();
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            Button txtDef = new Button(this);
            Button txtPal = new Button(this);
            txtPal.setText(palabra);
            txtPal.setAllCaps(false);
            txtDef.setText(definicion);
            txtDef.setAllCaps(false);
            linearLayout.addView(txtPal);
            linearLayout.addView(txtDef);
            layoutContenido.addView(linearLayout);

            txtDef.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button)v;
                    String texto = btn.getText().toString();
                    reubicarDefinicion(texto);
                }
            });

            txtPal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button)v;
                    String texto = btn.getText().toString();
                    buscarOrdenPalabra(texto);

                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
        }
    }

    public void reubicarDefinicion(String def)
    {

        for(Contenido cont: archivoModificado)
        {
            String oracion = controlador.convertSentence(cont.getDefinition(),cont.getWord());
            if(def.equalsIgnoreCase(oracion))
            {
                archivoModificado.remove(cont);

                archivoModificado.add(orden,cont);
                break;
            }
        }

        layoutContenido.removeAllViews();
        proyectarContenido();
    }

    public void buscarOrdenPalabra(String def)
    {
        for(Contenido cont: archivoModificado)
        {
            if(def.equalsIgnoreCase(cont.getWord()))
            {
                orden = archivoOriginal.indexOf(cont);
                break;
            }
        }

    }

    public void comparar(View view)
    {
        int acomulador=0;
        int contador =0;
        while (contador<archivoOriginal.size())
        {
            if(!archivoOriginal.get(contador).equals(archivoModificado.get(contador)))
            {
                acomulador++;
            }
            contador++;
        }

        if(acomulador==0)
        {
            layoutContenido.removeAllViews();
            TextView textView = new TextView(this);
            textView.setText("Good Job!!!");
            textView.setTextSize(48);
            layoutContenido.addView(textView);
        }
        else
        {
            Toast.makeText(this,acomulador+" Are pendding",Toast.LENGTH_LONG).show();
        }
    }
}