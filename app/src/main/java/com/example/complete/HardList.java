package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.complete.controlador.Controlador;

import java.util.ArrayList;
import java.util.List;

public class HardList extends AppCompatActivity {
    Controlador controlador;
    List<Contenido> archivoOriginal = new ArrayList<>();
    List<Contenido> archivoModificado = new ArrayList<>();
    LinearLayout layoutContenido;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_list);
        layoutContenido = (LinearLayout) findViewById(R.id.contenido_hard);
        setArchivo();
        setContenido();

    }

    public void setArchivo() {
        controlador = new Controlador();
        archivoOriginal = controlador.getContenidoDificil(this);
    }

    public void setContenido()
    {
        for(Contenido s: archivoOriginal)
        {
            if(s.getDifficulty()==1)
            {
                crearTextView(s);
            }
        }
    }

    public void crearTextView(Contenido c)
    {
        //String texto = cont[7] +":" +cont[8] +"  "+cont[6] +" (" + cont[10] +" )";
        String texto =c.getWord()+":  "+ c.getDefinition()+ "("+c.getBook()+":"+c.getLesson()+")";
        TextView textView = new TextView(this);

        textView.setPadding(24,24,24,24);
        textView.setText(texto);
        layoutContenido.addView(textView);

    }
}