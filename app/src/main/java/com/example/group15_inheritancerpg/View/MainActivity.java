package com.example.group15_inheritancerpg.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.group15_inheritancerpg.R;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        btn1 = findViewById(R.id.turn);
        btn2 = findViewById(R.id.base);

        btn1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, decision.class);
            startActivity(intent);
            finish();
        });

        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, turn.class);
            startActivity(intent);
            finish();
        });


    }
}