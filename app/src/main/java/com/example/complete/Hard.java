package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Hard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
    }

    public void selectingHardActivity(View view)
    {

        Intent selectingHard = new Intent(this,SelectingHard.class);
        startActivity(selectingHard);

    }

    public void listHardActivity(View view)
    {

        Intent listHard = new Intent(this,ListHard.class);
        startActivity(listHard);

    }










}