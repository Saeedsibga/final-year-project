package com.example.fyproject;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {

    private Button privacyPolicyButton;
    private Button termsOfUseButton;
    private FrameLayout privacyPolicyFrameLayout;
    private FrameLayout termsOfUseFrameLayout;
    private AlertDialog alertDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        privacyPolicyButton = findViewById(R.id.privacy_policy_button);
        termsOfUseButton = findViewById(R.id.terms_of_use_button);
        privacyPolicyFrameLayout = findViewById(R.id.content_frame_privacy_policy);
        termsOfUseFrameLayout = findViewById(R.id.content_frame_terms_of_use);

        privacyPolicyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the Privacy Policy content and hide the Terms of Use content
                privacyPolicyFrameLayout.setVisibility(View.VISIBLE);
                termsOfUseFrameLayout.setVisibility(View.GONE);
            }
        });

        termsOfUseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the Terms of Use content and hide the Privacy Policy content
                termsOfUseFrameLayout.setVisibility(View.VISIBLE);
                privacyPolicyFrameLayout.setVisibility(View.GONE);
            }
        });
    }

    // Handle the "back" button press
    @Override
    public void onBackPressed() {
        // If the Terms of Use content is visible, hide it and show the Privacy Policy content
        if (termsOfUseFrameLayout.getVisibility() == View.VISIBLE) {
            termsOfUseFrameLayout.setVisibility(View.GONE);
            privacyPolicyFrameLayout.setVisibility(View.VISIBLE);
        } else {
            // Otherwise, finish the current activity and return to the previous one
            super.onBackPressed();
        }
    }

    // Handle the "accept" button press
    public void onAcceptButtonClick(View view) {
        // Start the next activity
        Intent intent = new Intent(MainActivity6.this, MainActivity2.class);
        startActivity(intent);
    }

    // Handle the "reject" button press
    public void onRejectButtonClick(View view) {
        // Show an alarming message before closing the app
        showRejectMessage();
    }

    // Show an alarming message before closing the app
    private void showRejectMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity6.this);
        builder.setTitle("Warning")
                .setMessage("Sorry! You will need to accept both our Terms of Use and Privacy Policy in order to access our services. Closing app.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the app
                        finishAffinity();
                    }
                })
                .setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getWindow().getDecorView().getRootView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && alertDialog.isShowing()) {
                    alertDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
    }
}

