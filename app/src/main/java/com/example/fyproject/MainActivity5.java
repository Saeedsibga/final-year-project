package com.example.fyproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;
    private Toast gifMessageToast;
    private boolean isGifPlayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGifPlayed) {
                    playFullScreenGif();
                } else {
                    dispatchTakePictureIntent(); // Open camera
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Upload picture from gallery
                pickImageFromGallery();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to MainActivity7
                openNextActivity();
            }
        });

        // Set an onTouchListener for the ScrollView to dismiss the GIF when the user taps anywhere on the screen
        ScrollView scrollView = findViewById(R.id.scroll_view);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && gifMessageToast != null) {
                    gifMessageToast.cancel();
                    isGifPlayed = true; // Stop the GIF animation
                    return true;
                }
                return false;
            }
        });
    }

    private void playFullScreenGif() {
        Intent intent = new Intent(MainActivity5.this, FullScreenActivity.class);
        intent.putExtra("gifResource", R.drawable.animated);
        startActivity(intent);

        // Show a toast message during the playing of the GIF
        gifMessageToast = Toast.makeText(MainActivity5.this, "Press anywhere to dismiss", Toast.LENGTH_SHORT);
        gifMessageToast.show();

        // Set isGifPlayed to true to prevent the GIF from being displayed again
        isGifPlayed = true;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_PICK);
    }

    private void openNextActivity() {
        Intent intent = new Intent(MainActivity5.this, MainActivity8.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Handle captured image from the camera
            Bundle extras = data.getExtras();
            if (extras != null && extras.containsKey(MediaStore.EXTRA_OUTPUT)) {
                // Get the captured image bitmap
                // Bitmap imageBitmap = (Bitmap) extras.get("data");
                // Process the captured image as needed

                // Set isGifPlayed to true to prevent the GIF from being displayed again
                isGifPlayed = false;
            }
        } else if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            // Handle selected image from gallery
            if (data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                // Process the selected image as needed
            }
        }

        // Reset isGifPlayed to false if the action was selecting an image from the gallery
        if (requestCode == REQUEST_IMAGE_PICK) {
            isGifPlayed = false;
        } else if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (!isGifPlayed) {
                // If the action was capturing an image and the GIF hasn't played yet,
                // start MainActivity5 again to play the GIF once more
                Intent intent = new Intent(this, MainActivity5.class);
                startActivity(intent);
            } else {
                // If the action was capturing an image and the GIF has already played,
                // navigate to MainActivity7
                openNextActivity();
            }
        }
    }
}
