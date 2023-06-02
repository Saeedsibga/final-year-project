package com.example.fyproject;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import pl.droidsonroids.gif.GifImageView;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set activity to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen);

        // Retrieve the GIF resource ID from the intent
        int gifResource = getIntent().getIntExtra("gifResource", 0);

        GifImageView gifImageView = findViewById(R.id.gif_image_view);
        Glide.with(this).asGif().load(gifResource).into(gifImageView);
    }
}
