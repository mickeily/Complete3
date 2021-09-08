package com.example.complete;

import android.app.Activity;

public class Hilo extends Thread

{
    public Play play;
   public void ejecutar(Activity activity,String word,int tiempo)
   {
       play = new Play();
       play.reproducir(word,activity);

       try {
           Thread.sleep(tiempo);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }

}
