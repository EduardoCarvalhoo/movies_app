package com.example.movies_app.presentation.splashScreen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.movies_app.databinding.ActivityInitialPresentationBinding;
import com.example.movies_app.presentation.home.HomeActivity;

public class InitialPresentationActivity extends AppCompatActivity {
private ActivityInitialPresentationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInitialPresentationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupSplashScreen();
    }

    public void setupSplashScreen() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(InitialPresentationActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}