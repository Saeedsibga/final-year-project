package com.example.fyproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SigninActivity extends Activity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button submitButton;
    private Button backButton;

    private UserDbHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        dbHelper = new UserDbHelper(this);

        TextView titleTextView = findViewById(R.id.title);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        submitButton = findViewById(R.id.submit_button);
        backButton = findViewById(R.id.back_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    String username = usernameEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    // Check the credentials against the database
                    if (checkCredentials(username, password)) {
                        // Create a new intent to start the next activity
                        Intent intent = new Intent(SigninActivity.this, MainActivity5.class);

                        // Add the username as an extra to the intent
                        intent.putExtra("username", username);

                        // Start the next activity
                        startActivity(intent);
                    } else {
                        Toast.makeText(SigninActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SigninActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and go back to the previous screen
                finish();
            }
        });
    }

    private boolean validateInput() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        return !username.isEmpty() && !password.isEmpty();
    }

    private boolean checkCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = UserContract.UserEntry.COLUMN_USERNAME + " = ? AND " +
                UserContract.UserEntry.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        String[] projection = {UserContract.UserEntry.COLUMN_USERNAME};

        Cursor cursor = db.query(
                UserContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }
}
