package com.example.fyproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity2 extends Activity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private RadioGroup genderRadioGroup;
    private EditText ageEditText;
    private EditText emailAddressEditText;
    private CheckBox conditionsCheckBox;
    private Button submitButton;
    private Button signInButton;

    private UserDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new UserDbHelper(this);

        TextView titleTextView = findViewById(R.id.title);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        genderRadioGroup = findViewById(R.id.radioGroup);
        ageEditText = findViewById(R.id.age);
        emailAddressEditText = findViewById(R.id.emailAddress);
        conditionsCheckBox = findViewById(R.id.conditions);
        submitButton = findViewById(R.id.button);
        signInButton = findViewById(R.id.signInButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    // Save the user data to the database
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(UserContract.UserEntry.COLUMN_USERNAME, usernameEditText.getText().toString());
                    values.put(UserContract.UserEntry.COLUMN_PASSWORD, passwordEditText.getText().toString());
                    int genderRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                    RadioButton genderRadioButton = findViewById(genderRadioButtonId);
                    values.put(UserContract.UserEntry.COLUMN_GENDER, genderRadioButton.getText().toString());
                    values.put(UserContract.UserEntry.COLUMN_AGE, Integer.parseInt(ageEditText.getText().toString()));
                    values.put(UserContract.UserEntry.COLUMN_EMAIL_ADDRESS, emailAddressEditText.getText().toString());
                    db.insert(UserContract.UserEntry.TABLE_NAME, null, values);
                    db.close();

                    // Create a new intent to start the fifth activity
                    Intent intent = new Intent(MainActivity2.this, MainActivity5.class);

                    // Add the user's username, gender, age, and email address as extras to the intent
                    String username = usernameEditText.getText().toString();
                    String gender = genderRadioButton.getText().toString();
                    int age = Integer.parseInt(ageEditText.getText().toString());
                    String emailAddress = emailAddressEditText.getText().toString();
                    intent.putExtra("username", username);
                    intent.putExtra("gender", gender);
                    intent.putExtra("age", age);
                    intent.putExtra("emailAddress", emailAddress);

                    // Start the fifth activity
                    startActivity(intent);
                }
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to start the sign-in activity
                Intent intent = new Intent(MainActivity2.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInput() {
        boolean isValid = true;

        // Check if the username field is empty
        String username = usernameEditText.getText().toString().trim();
        if (username.isEmpty()) {
            usernameEditText.setError("Please enter your username");
            isValid = false;
        }

        // Check if the password field is empty
        String password = passwordEditText.getText().toString().trim();
        if (password.isEmpty()) {
            passwordEditText.setError("Please enter your password");
            isValid = false;
        }

        // Check if a gender has been selected
        if (genderRadioGroup.getCheckedRadioButtonId() == -1) {
            RadioButton maleRadioButton = findViewById(R.id.radioButton);
            maleRadioButton.setError("Please select your gender");
            isValid = false;
        }

        // Check if the age field is empty or invalid
        String ageString = ageEditText.getText().toString().trim();
        if (ageString.isEmpty()) {
            ageEditText.setError("Please enter your age");
            isValid = false;
        } else {
            int age = Integer.parseInt(ageString);
            if (age < 1 || age > 120) {
                ageEditText.setError("Please enter a valid age");
                isValid = false;
            }
        }

        // Check if the email address field is empty or invalid
        String emailAddress = emailAddressEditText.getText().toString().trim();
        if (emailAddress.isEmpty()) {
            emailAddressEditText.setError("Please enter your email address");
            isValid = false;
        } else if (!isValidEmailAddress(emailAddress)) {
            emailAddressEditText.setError("Please enter a valid email address");
            isValid = false;
        }

        // Check if the conditions checkbox is checked
        if (!conditionsCheckBox.isChecked()) {
            conditionsCheckBox.setError("Please read and agree to the conditions");
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidEmailAddress(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}
