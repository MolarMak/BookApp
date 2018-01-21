package com.molarmak.bookapp.modules.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.molarmak.bookapp.R;

/**
 * Class intro
 * First screen
 */
public class WelcomeActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(getApplicationContext(), InfoActivity.class));
        });
    }
}
