package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.complete.controlador.Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HardHanged extends AppCompatActivity {

    Controlador controlador;
    List<Contenido> archivoOriginal = new ArrayList<>();
    Contenido elemento;
    List<String> mascara ;

    float total,buenas;

    Button q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m;
    private int contadorPulsaciones=0;
    float porcentaje=0;
    int intentos =7;
    TextView txtMasc;



    private TextView oracion,resp, porciento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_hanged);
        oracion = (TextView)findViewById(R.id.text_oracion);
        resp = (TextView)findViewById(R.id.resp);
        porciento = (TextView)findViewById(R.id.porciento);
        txtMasc = (TextView)findViewById(R.id.masc);

        a = (Button)findViewById(R.id.a);
        b = (Button)findViewById(R.id.b);
        c = (Button)findViewById(R.id.c);
        d = (Button)findViewById(R.id.d);
        e = (Button)findViewById(R.id.e);
        f = (Button)findViewById(R.id.f);
        g = (Button)findViewById(R.id.g);
        h = (Button)findViewById(R.id.h);
        i = (Button)findViewById(R.id.i);
        j = (Button)findViewById(R.id.j);
        k = (Button)findViewById(R.id.k);
        l = (Button)findViewById(R.id.l);
        m = (Button)findViewById(R.id.m);
        n = (Button)findViewById(R.id.n);
        o = (Button)findViewById(R.id.o);
        p = (Button)findViewById(R.id.p);
        q = (Button)findViewById(R.id.q);
        r = (Button)findViewById(R.id.r);
        s = (Button)findViewById(R.id.s);
        t = (Button)findViewById(R.id.t);
        u = (Button)findViewById(R.id.u);
        v = (Button)findViewById(R.id.v);
        w = (Button)findViewById(R.id.w);
        x = (Button)findViewById(R.id.x);
        y = (Button)findViewById(R.id.y);
        z = (Button)findViewById(R.id.z);
        setArchivo();
        seleccionarElemento();
        crearMascara();
        proyectarMascara();
        proyectarRespuesta();


    }
    public void setArchivo()
    {
        controlador = new Controlador();
        archivoOriginal = controlador.getContenidoDificil(this);
    }

    public void seleccionarElemento()
    {
        elemento = new Contenido();
        Random randomizer = new Random();
        //elemento = archivoOriginal.get(randomizer.nextInt(archivoOriginal.size()));
        elemento = archivoOriginal.get(0);
        int a =0;
    }

    public void crearMascara()
    {
        mascara = new ArrayList<>();
        int contador = 0;
        while (contador<elemento.getWord().length())
        {
            mascara.add("_");
            contador ++;
        }
    }

    public void proyectarMascara()
    {

        String masc = "";
        for(String m: mascara)
        {
          masc += m +" ";
        }
        txtMasc.setTextSize(36);
        txtMasc.setText(masc);
    }

    public void proyectarRespuesta()
    {
        TextView txtOracion =(TextView)findViewById(R.id.text_oracion);
        String respuesta = controlador.convertSentence(elemento.getDefinition(), elemento.getWord());
        txtOracion.setTextSize(16);
        txtOracion.setPadding(24,0,0,0);
        txtOracion.setText(respuesta);

    }

    public void comprobar(View view)
    {
        Button btn = (Button) view;
        String textoBtn = btn.getText().toString();
        String textoElemento = elemento.getWord();


            if(textoElemento.contains(textoBtn))
            {
                int contador = 0;
                while (contador<textoElemento.length())
                {
                    if(textoElemento.charAt(contador)== textoBtn.charAt(0))
                    {
                        mascara.set(contador,textoBtn);
                        proyectarMascara();
                    }
                    contador++;
                }
                btn.setEnabled(false);
            }
            else
            {
                btn.setEnabled(false);
                contadorPulsaciones++;

            }

            if(!mascara.contains("_"))
            {
                buenas++;
                total++;
                reniciar();
            }

            if (contadorPulsaciones>=intentos)
            {
                total++;
                reniciar();

            }


    }

    public void reniciar()
    {
        mostrarRespuesta();
        reorganizar();
        ajustarValores();
        activarTeclas();
        seleccionarElemento();
        crearMascara();
        proyectarMascara();
        proyectarRespuesta();

    }

    public void activarTeclas()
    {
        a.setEnabled(true);
        b.setEnabled(true);
        c.setEnabled(true);
        d.setEnabled(true);
        e.setEnabled(true);
        f.setEnabled(true);
        g.setEnabled(true);
        h.setEnabled(true);
        i.setEnabled(true);
        j.setEnabled(true);
        k.setEnabled(true);
        l.setEnabled(true);
        m.setEnabled(true);
        n.setEnabled(true);
        o.setEnabled(true);
        p.setEnabled(true);
        q.setEnabled(true);
        r.setEnabled(true);
        s.setEnabled(true);
        t.setEnabled(true);
        u.setEnabled(true);
        v.setEnabled(true);
        w.setEnabled(true);
        x.setEnabled(true);
        y.setEnabled(true);
        z.setEnabled(true);
    }

    public void ajustarValores()
    {

        contadorPulsaciones=0;
        porcentaje = (buenas/total)*100;
        int rendimiento = (Math.round(porcentaje));
        porciento.setText(rendimiento+"%");
        intentos = 102-(int)porcentaje;

    }

    public void reorganizar()
    {
        archivoOriginal.remove(elemento);
        archivoOriginal.add(elemento);

    }

    public void mostrarRespuesta()
    {
        resp.setText(elemento.getWord()+":  " + elemento.getDefinition());
        String s =elemento.getDefinition();

    }









}