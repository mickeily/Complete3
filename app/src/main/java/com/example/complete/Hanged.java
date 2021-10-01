package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Hanged extends AppCompatActivity {
    private String[][] archivo= new String[5000][10];
    private String[][] contenido= new String[50][10];
    private String[] elemento= new String[50];

    float total,buenas;

    Button q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m;
    private String[] mascara;
    private Save save;
    private String libro;
    private String unidad;
    private String palabra;
    private int limite =0;
    private MainActivity mainActivity;
    private Uncramble uncramble;

    private int contadorPulsaciones;

    private TextView oracion,resp, porciento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanged);
        oracion = (TextView)findViewById(R.id.text_oracion);
        resp = (TextView)findViewById(R.id.resp);
        porciento = (TextView)findViewById(R.id.porciento);

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

        save = new Save();
        mainActivity = new MainActivity();
        uncramble = new Uncramble();

        cargar();
        modificar();
        buscarLimite();
        seleccionarElemento();
        crearMascara();
        proyectarMascara();

    }

    public String[][] cargar()
    {
        save.checkPermission(this);
        archivo = save.readFile(this,"/sdcard/DataEssential.csv");
        int contador =0;
        int contadorInterno =0;
        libro = mainActivity.getLibro();
        unidad  = mainActivity.getUnidad();

        while (archivo[contador][0]!=null)
        {
            if(archivo[contador][7].equalsIgnoreCase(libro))
            {
                if(archivo[contador][8].equalsIgnoreCase(unidad))
                {
                    contenido[contadorInterno]= archivo[contador];
                    contadorInterno++;
                }

                if (contadorInterno >= 20)
                {
                    break;
                }
            }
            contador++;
        }

        return contenido;
    }
    public void seleccionarElemento()
    {
        int contador =0;
        int random =0;
        random = uncramble.setRandom(0,limite);
        elemento = contenido[random];
        while(contenido[contador][0]!=null)
        {

            if((Integer.parseInt(contenido[contador][1])) < (Integer.parseInt(elemento[1])))
            {
               elemento = contenido[contador];
            }

            contador++;
        }
        oracion.setText(convertSentence(elemento[6], elemento[10]));

    }
    public void buscarLimite()
    {
        while (contenido[limite][0]!=null)
        {
           limite++;
        }

    }
    public void crearMascara()
    {

        palabra = elemento[10];
        mascara = new String[palabra.length()];
        int contador =0;

        while(contador<palabra.length())
        {
            mascara[contador] = "_";
            contador ++;
        }
    }

    public void proyectarMascara()
    {
        int a =0;
        crearTextView(mascara);


    }

    public void crearTextView(String[] texto)
    {
        String pal="";

        TextView tv = (TextView)findViewById(R.id.masc);

        for(String s: texto)
        {
          pal += s + " " ;
        }
        tv.setTextSize(36);
        tv.setText(pal);

        //Toast.makeText(this,palabra,Toast.LENGTH_LONG).show();

    }

    public void comprobar(View view)
    {
        try {
            String gion = "_";
            boolean terminar = true;
            Button boton = (Button) view;
            boton.setEnabled(false);
            String letra = boton.getText().toString();

            if (palabra.indexOf(letra) != -1) {
                int contador = 0;
                while (contador < palabra.length()) {
                    if (palabra.charAt(contador) == letra.charAt(0)) {
                        mascara[contador] = letra;
                    }
                    contador++;
                }
                crearTextView(mascara);
            } else {
                contadorPulsaciones++;
            }

            if (contadorPulsaciones >= 7) {
                total ++;
                responder(palabra);
                reiniciar();
            }
            else
            {
                responder("");
            }

            int cont = 0;
            while (cont < mascara.length)
            {
                if (mascara[cont].equalsIgnoreCase("_")) {
                    terminar = false;
                }
                cont++;
            }

            if (terminar == true) {

                total ++;
                buenas ++;
                reiniciar();

            }


        }catch (Exception e)
        {
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }
    }

    public void reiniciar()
    {
        establerPociento();
        setIntentos();
        abilitarBotones();
        contadorPulsaciones =0;
        seleccionarElemento();
        crearMascara();
        proyectarMascara();
    }

    public String convertSentence(String oracion,String palabra)
    {
        String word ="";
        String sentence = oracion.toLowerCase();
        word = sentence.replace(palabra,"______");
        return word;
    }

    public void abilitarBotones()
    {

        int hol =0;

        if(!a.isEnabled())
        {
            a.setEnabled(true);
        }

        if(!b.isEnabled())
        {
            b.setEnabled(true);
        }
        if(!c.isEnabled())
        {
            c.setEnabled(true);
        }
        if(!d.isEnabled())
        {
            d.setEnabled(true);
        }
        if(!e.isEnabled())
        {
            e.setEnabled(true);
        }
        if(!f.isEnabled())
        {
            f.setEnabled(true);
        }
        if(!g.isEnabled())
        {
            g.setEnabled(true);
        }
        if(!h.isEnabled())
        {
            h.setEnabled(true);
        }
        if(!i.isEnabled())
        {
            i.setEnabled(true);
        }
        if(!j.isEnabled())
        {
            j.setEnabled(true);
        }
        if(!k.isEnabled())
        {
            k.setEnabled(true);
        }
        if(!l.isEnabled())
        {
            l.setEnabled(true);
        }
        if(!m.isEnabled())
        {
            m.setEnabled(true);
        }
        if(!n.isEnabled())
        {
            n.setEnabled(true);
        }
        if(!o.isEnabled())
        {
            o.setEnabled(true);
        }
        if(!p.isEnabled())
        {
            p.setEnabled(true);
        }
        if(!q.isEnabled())
        {
            q.setEnabled(true);
        }
        if(!r.isEnabled())
        {
            r.setEnabled(true);
        }
        if(!s.isEnabled())
        {
            s.setEnabled(true);
        }
        if(!t.isEnabled())
        {
            t.setEnabled(true);
        }
        if(!u.isEnabled())
        {
            u.setEnabled(true);
        }
        if(!x.isEnabled())
        {
            x.setEnabled(true);
        }
        if(!y.isEnabled())
        {
            y.setEnabled(true);
        }
        if(!w.isEnabled())
        {
            w.setEnabled(true);
        }
        if(!z.isEnabled())
        {
            z.setEnabled(true);
        }
        if(!v.isEnabled())
        {
            v.setEnabled(true);
        }


    }

    public void responder(String texto)
    {
        resp.setText(texto);
    }

    public void setIntentos()
    {
        int indice = Integer.parseInt(elemento[0]);
        int conteoActual = Integer.parseInt(elemento[1]);
        conteoActual ++;
        contenido[indice][1]= conteoActual+"";
    }

    public void modificar()
    {
        int contador=0;
        while (contenido[contador][0]!=null)
        {
            contenido[contador][1]="0";
            contenido[contador][0]=contador+"";
            contador++;
        }

    }

    public void establerPociento()
    {
        if (total>0)
        {
            float porciento = (buenas/total)*100;
            int intPorciento = Math.round(porciento);
            this.porciento.setText(intPorciento+"%");
        }


    }

}
