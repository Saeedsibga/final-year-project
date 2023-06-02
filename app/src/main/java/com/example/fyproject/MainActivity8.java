package com.example.fyproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity8 extends AppCompatActivity implements View.OnClickListener {

    private ImageButton oralMedicinesButton;
    private ImageButton homeRemediesButton;
    private Button backButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        oralMedicinesButton = findViewById(R.id.MedicinesButton);
        homeRemediesButton = findViewById(R.id.homeRemediesButton);
        backButton = findViewById(R.id.backButton);
        nextButton = findViewById(R.id.nextButton);

        oralMedicinesButton.setOnClickListener(this);
        homeRemediesButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.MedicinesButton:
                intent = new Intent(MainActivity8.this, MainActivity7.class);
                startActivity(intent);
                break;
            case R.id.homeRemediesButton:
                intent = new Intent(MainActivity8.this, MainActivity7.class);
                startActivity(intent);
                break;
            case R.id.backButton:
                onBackPressed();
                break;
            case R.id.nextButton:
                intent = new Intent(MainActivity8.this, MainActivity9.class);
                startActivity(intent);
                break;
        }
    }
}
