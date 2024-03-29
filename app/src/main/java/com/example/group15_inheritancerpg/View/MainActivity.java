package com.example.group15_inheritancerpg.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.group15_inheritancerpg.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.main_menu);

        btn1 = findViewById(R.id.turn);
        btn2 = findViewById(R.id.base);

        btn1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DialogueView.class);
            startActivity(intent);
        });

        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CombatView.class);
            startActivity(intent);
        });


    }
}