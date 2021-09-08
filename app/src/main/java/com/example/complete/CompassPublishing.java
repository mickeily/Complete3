package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CompassPublishing extends AppCompatActivity {
    public static String botonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_publishing);
    }

    public void compassListenActivity(View view)
    {
        Button btn = (Button) view;
        botonText = btn.getText().toString();
        Intent listen = new Intent(this,CompassListen.class);
        startActivity(listen);
    }

    public void compassTestActivity(View view)
    {

        Intent test = new Intent(this,MultiChoice.class);
        startActivity(test);
    }

    public String getBotonText()
    {
        return botonText;
    }
}