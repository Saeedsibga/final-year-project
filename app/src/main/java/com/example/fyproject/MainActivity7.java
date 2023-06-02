package com.example.fyproject;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity7 extends AppCompatActivity {

    private CheckBox pregnantCheckBox, diabeticCheckBox, cardiovascularCheckBox, kidneyCheckBox, othersCheckBox;
    private Button submitButton, backButton;
    private UserDbHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        // Initialize views
        pregnantCheckBox = findViewById(R.id.pregnantCheckBox);
        diabeticCheckBox = findViewById(R.id.diabeticCheckBox);
        cardiovascularCheckBox = findViewById(R.id.cardiovascularCheckBox);
        kidneyCheckBox = findViewById(R.id.kidneyCheckBox);
        othersCheckBox = findViewById(R.id.othersCheckBox);
        submitButton = findViewById(R.id.submitButton);
        backButton = findViewById(R.id.backButton);

        // Initialize DatabaseHelper
        databaseHelper = new UserDbHelper(this);

        // Submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateCheckBoxes()) {
                    // At least one checkbox is checked, save the data to the database
                    saveHealthConditionsToDatabase();
                    // Navigate to the corresponding activity based on the health condition
                    if (pregnantCheckBox.isChecked()) {
                        startActivity(new Intent(MainActivity7.this, PregnantActivity.class));
                    } else if (diabeticCheckBox.isChecked()) {
                        startActivity(new Intent(MainActivity7.this, DiabeticActivity.class));
                    } else if (cardiovascularCheckBox.isChecked()) {
                        startActivity(new Intent(MainActivity7.this, CardiovascularActivity.class));
                    } else if (kidneyCheckBox.isChecked()) {
                        startActivity(new Intent(MainActivity7.this, KidneyActivity.class));
                    } else if (othersCheckBox.isChecked()) {
                        startActivity(new Intent(MainActivity7.this, OthersActivity.class));
                    }
                } else {
                    // No checkbox is checked, display a message
                    Toast.makeText(MainActivity7.this, "Please mark your health condition", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity5
                Intent intent = new Intent(MainActivity7.this, MainActivity5.class);
                startActivity(intent);
            }
        });
    }

    // Validate if at least one checkbox is checked
    private boolean validateCheckBoxes() {
        return pregnantCheckBox.isChecked() || diabeticCheckBox.isChecked() ||
                cardiovascularCheckBox.isChecked() || kidneyCheckBox.isChecked() || othersCheckBox.isChecked();
    }

    // Save the selected health conditions to the database
    private void saveHealthConditionsToDatabase() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        if (pregnantCheckBox.isChecked()) {
            ContentValues values = new ContentValues();
            values.put(UserDbHelper.COLUMN_CONDITION, "Pregnant");
            db.insert(UserDbHelper.TABLE_NAME, null, values);
        }
        if (diabeticCheckBox.isChecked()) {
            ContentValues values = new ContentValues();
            values.put(UserDbHelper.COLUMN_CONDITION, "Diabetic");
            db.insert(UserDbHelper.TABLE_NAME, null, values);
        }
        if (cardiovascularCheckBox.isChecked()) {
            ContentValues values = new ContentValues();
            values.put(UserDbHelper.COLUMN_CONDITION, "Cardiovascular");
            db.insert(UserDbHelper.TABLE_NAME, null, values);
        }
        if (kidneyCheckBox.isChecked()) {
            ContentValues values = new ContentValues();
            values.put(UserDbHelper.COLUMN_CONDITION, "Kidney");
            db.insert(UserDbHelper.TABLE_NAME, null, values);
        }
        if (othersCheckBox.isChecked()) {
            ContentValues values = new ContentValues();
            values.put(UserDbHelper.COLUMN_CONDITION, "Others");
            db.insert(UserDbHelper.TABLE_NAME, null, values);
        }

        // Close the database connection
        db.close();
    }
}
