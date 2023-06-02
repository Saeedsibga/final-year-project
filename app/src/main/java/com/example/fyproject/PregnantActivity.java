package com.example.fyproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PregnantActivity extends AppCompatActivity {

    private Button readMoreButton1, readMoreButton2, readMoreButton4;
    private TextView acneMedicineDescription1, acneMedicineDescription2, acneMedicineDescription4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnant);

        readMoreButton1 = findViewById(R.id.read_more_button1);
        readMoreButton2 = findViewById(R.id.read_more_button2);
        readMoreButton4 = findViewById(R.id.read_more_button4);

        acneMedicineDescription1 = findViewById(R.id.acne_medicine_description1);
        acneMedicineDescription2 = findViewById(R.id.acne_medicine_description2);
        acneMedicineDescription4 = findViewById(R.id.acne_medicine_description4);

        readMoreButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDescriptionVisibility(acneMedicineDescription1, readMoreButton1);
            }
        });

        readMoreButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDescriptionVisibility(acneMedicineDescription2, readMoreButton2);
            }
        });

        readMoreButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDescriptionVisibility(acneMedicineDescription4, readMoreButton4);
            }
        });
    }

    private void toggleDescriptionVisibility(TextView descriptionTextView, Button readMoreButton) {
        if (descriptionTextView.getVisibility() == View.GONE) {
            descriptionTextView.setVisibility(View.VISIBLE);
            readMoreButton.setText("Read Less");
        } else {
            descriptionTextView.setVisibility(View.GONE);
            readMoreButton.setText("Read More");
        }
    }
}
