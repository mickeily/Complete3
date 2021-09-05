package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Info extends AppCompatActivity {
    private String numeros[]= {"0:","1:","2:","3:","4:","5:","6:","7:","8:","9:","10:","Total","CBP"};
    private String otros[] = {"Avan:","Efec:","Active"};

    private  int[][] info = new int[8][15];
    Save save = new Save();
    Fecha fecha;
    String archivo[][] = new String[4000][20];
    LinearLayout generico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        cargar();
        general();
        menu();
        otros();
        totalEnCurso();
        fechaTermino();

    }

    public void cargar()
    {
        save.checkPermission(this);
        archivo = save.readFile(this,"/sdcard/DataEssential.csv");
    }

    public void menu()
    {
        Fecha fecha;
        fecha= new Fecha();
        int intentos;
        int contador =0;
        String puntaje="";
        int menu[] = new int[15];
        long resta = 0;

        try
        {

            while (archivo[contador][0]!=null)
            {
                puntaje=archivo[contador][2];
                intentos= Integer.parseInt(archivo[contador][3]);
                resta = fecha.getHoras(archivo[contador][4]);

                if(archivo[contador][1].equalsIgnoreCase("1"))
                {
                    switch (puntaje)
                    {
                        case "0":
                        {
                            if((resta>=24))// && (intentos>0))
                            {
                                menu[1]++;
                                menu[11]++;
                            }
                            break;
                        }
                        case "1":
                            if(resta>=72)
                            {
                                menu[2]++;
                                menu[11]++;
                            }
                            break;

                        case "2":
                            if(resta>=120)
                            {
                                menu[3]++;
                                menu[11]++;
                            }
                            break;
                        case "3":
                            if(resta>=168)
                            {
                                menu[4]++;
                                menu[11]++;
                            }
                            break;
                        case "4":
                            if(resta>=168)
                            {
                                if(intentos<5)
                                {
                                    menu[12]++;
                                    menu[11]++;
                                }
                                else
                                {
                                    menu[5]++;
                                    menu[11]++;
                                }
                            }
                            break;
                        case "5":
                            if((resta >=168)&& intentos>5)
                            {
                                if(intentos<7)
                                {
                                    menu[12]++;
                                    menu[11]++;
                                }
                                else
                                {
                                    menu[6]++;
                                    menu[11]++;
                                }
                            }

                            break;
                        case "6":
                            if((resta>=168)&& intentos>7)
                            {
                                if(intentos<9)
                                {
                                    menu[12]++;
                                    menu[11]++;
                                }
                                else
                                {
                                    menu[7]++;
                                    menu[11]++;
                                }
                            }

                            break;
                        case "7":
                            if((resta>=168)&& intentos>9)
                            {
                                if(intentos<11)
                                {
                                    menu[13]++;
                                    menu[11]++;
                                }
                                else
                                {
                                    menu[8]++;
                                    menu[11]++;
                                }
                            }
                            break;
                        case "8":
                            if((resta>=168)&&intentos>11)
                            {
                                if(intentos<13)
                                {
                                    menu[12]++;
                                    menu[11]++;
                                }
                                else
                                {
                                    menu[6]++;
                                    menu[11]++;
                                }
                            }

                            break;
                        case "9":
                            if((resta>=168)&&intentos>13)
                            {
                                menu[12]++;
                                menu[11]++;
                            }

                            break;
                        default:
                    }
                }
                contador++;
            }

        } catch (Exception e)
        {
            e.getStackTrace();
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG);
        }

        contador =0;
        String texto= "";
        generico = (LinearLayout)findViewById(R.id.today_menu);
        while (contador<menu.length)
        {
            if(menu[contador]>0)
            {
                texto = numeros[contador] + "\t" + "\t"+ menu[contador];
                crearTexview(texto,generico,24);
            }
            contador++;
        }

    }

    public void otros()
    {
        generico = (LinearLayout)findViewById(R.id.others);
        int contador =0;
        float avance =0;

        int active=0;
        String lib="";
        String unid="";
        float intentos=0;
        float puntaje=0;

        while ( archivo[contador][0]!=null)
        {
            if(archivo[contador][1].equalsIgnoreCase("1") && archivo[contador][2].equalsIgnoreCase("0") )
            {
                active++;
            }

            if(!archivo[contador][2].equalsIgnoreCase("0"))
            {
               avance += avance(archivo[contador][2],archivo[contador][1]);
            }

            if(!archivo[contador][3].equalsIgnoreCase("0"))
            {
                lib= (archivo[contador][7]);
                unid= (archivo[contador][8]);
            }


            intentos += Integer.parseInt(archivo[contador][3]);
            puntaje += Integer.parseInt(archivo[contador][2]);

            contador ++;
        }

        float efectividad = (puntaje/intentos)*100;
        int intEfectividad = Math.round(efectividad);
        String sEfectividad=("Efec:"+"\t\t"+intEfectividad+"%");
        crearTexview(sEfectividad,generico,24);


        float ava = (avance/18000)*100;
        int intAvan = Math.round(ava);
        String avanc=("Avan:"+"\t\t"+intAvan+"%");
        crearTexview(avanc,generico,24);

        String activo=("Act:  "+"\t\t"+active);


        crearTexview(activo,generico,24);

        crearTexview("Limit:"+"\t\t"+lib+":"+unid,generico,24);
        crearTexview("End:"+"\t\t"+fechaTermino(),generico,24);


    }

    public void crearTexview(String texto, LinearLayout layout,int left)
    {
        TextView textView = new TextView(this);
        textView.setPadding(left,4,0,0);
        textView.setText(texto);
        //textView.setGravity(Gravity.CENTER);
        layout.addView(textView);
    }

    public int avance(String ava,String status)
    {
        int acumulador =0;

        if(status.equalsIgnoreCase("2"))
        {
            acumulador+=5;
        }
        else
        {
            switch (ava)
            {
                case "1":
                {
                    acumulador+=1;
                    break;
                }
                case "2":
                {
                    acumulador+=2;
                    break;
                }
                case "3":
                {
                    acumulador+=3;
                    break;
                }
                case "4":
                {
                    acumulador+=4;
                    break;
                }
            }
        }

      return acumulador;
    }

    public void general()
    {
        int contador =0;
        while (archivo[contador][0]!=null)
        {
            int libro = Integer.parseInt(archivo[contador][7]);
            int puntaje = Integer.parseInt(archivo[contador][2]);
            int estatus = Integer.parseInt(archivo[contador][1]);
            if (puntaje<5)
            {
                info[libro][puntaje]++;
            }
            else
            {
                if(estatus==2)
                {
                    info[libro][10]++;
                }
                else
                {
                    info[libro][puntaje]++;
                }

            }
            contador++;
        }
        projectarInfo();
    }

    public void projectarInfo()
    {
        int t=0;
        generico = (LinearLayout)findViewById(R.id.books);
        String[] books ={"B1","B2","B3","B4","B5","B6","T"};
        int contador =0;

        while (contador<books.length)
        {
            crearTexview(books[contador],generico,4);
            contador++;
        }
        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.cero);
            crearTexview(info[contador][0]+"",generico,0);
            t+= info[contador][0];
            contador++;
        }
        crearTexview(t+"",generico,0);

        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.uno);
            crearTexview(info[contador][1]+"",generico,0);
            t+= info[contador][1];
            contador++;
        }
        crearTexview(t+"",generico,0);

        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.dos);
            crearTexview(info[contador][2]+"",generico,0);
            t+= info[contador][2];
            contador++;
        }
        crearTexview(t+"",generico,0);

        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.tres);
            crearTexview(info[contador][3]+"",generico,0);
            t+= info[contador][3];
            contador++;
        }
        crearTexview(t+"",generico,0);

        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.cuatro);
            crearTexview(info[contador][4]+"",generico,0);
            t+= info[contador][4];
            contador++;
        }
        crearTexview(t+"",generico,0);

        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.cinco);
            crearTexview(info[contador][5]+"",generico,0);
            t+= info[contador][5];
            contador++;
        }
        crearTexview(t+"",generico,0);

        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.seis);
            crearTexview(info[contador][6]+"",generico,0);
            t+= info[contador][6];
            contador++;
        }
        crearTexview(t+"",generico,0);

        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.siete);
            crearTexview(info[contador][7]+"",generico,0);
            t+= info[contador][7];
            contador++;
        }
        crearTexview(t+"",generico,0);

        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.ocho);
            crearTexview(info[contador][8]+"",generico,0);
            t+= info[contador][8];
            contador++;
        }
        crearTexview(t+"",generico,0);

        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.nueve);
            crearTexview(info[contador][9]+"",generico,0);
            t+= info[contador][9];
            contador++;
        }
        crearTexview(t+"",generico,0);

        contador=1;
        t=0;
        while (contador<books.length)
        {
            generico = (LinearLayout)findViewById(R.id.pass);
            crearTexview(info[contador][10]+"",generico,0);
            t+= info[contador][10];
            contador++;
        }
        crearTexview(t+"",generico,0);

    }

    public void totalEnCurso() {
        int contador = 0;
        int[] totals = new int[10];
        int acomulador = 0;
        generico = (LinearLayout)findViewById(R.id.total);
        while (archivo[contador][0] != null)
        {
            acomulador = Integer.parseInt(archivo[contador][7]);

            if (archivo[contador][1].equalsIgnoreCase("1" ))
            {
                if(!archivo[contador][2].equalsIgnoreCase("0"))
                {
                    totals[acomulador]++;
                    totals[7]++;
                }

            }
            contador++;
        }

        contador =1;
        while (contador<=7)
        {
            crearTexview(totals[contador]+"",generico,4);
            contador++;
        }
        int a =0 ;
    }

    public String fechaTermino()
    {
        int contador =0;
        int avance =0;
        fecha = new Fecha();
        String fechaInicio = "2021:04:15:00:00";
        float diasTranscurrido =0;
        while (archivo[contador][0]!=null)
        {
           avance+= avance(archivo[contador][2],archivo[contador][1]);
           contador++;
        }

        diasTranscurrido = fecha.getHoras(fechaInicio)/24;
        float puntosPorDias = avance/diasTranscurrido;
        float diasTotal = 18000/puntosPorDias;

        String fechaFutura = fecha.fechaFutura(diasTotal,fechaInicio);
        return fechaFutura;
    }

}
