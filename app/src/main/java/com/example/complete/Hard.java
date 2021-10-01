package com.example.complete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Hard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
    }

    public void selectingHardActivity(View view)
    {

        Intent selectingHard = new Intent(this, HardSelecting.class);
        startActivity(selectingHard);

    }

    public void listHardActivity(View view)
    {

        Intent listHard = new Intent(this, HardList.class);
        startActivity(listHard);

    }

    public void hardHangedActivity(View view)
    {
        Intent hardHanged = new Intent(this, HardHanged.class);
        startActivity(hardHanged);

    }










}