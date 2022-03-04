package com.example.group15_inheritancerpg.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.group15_inheritancerpg.Controller.DialogueController;
import com.example.group15_inheritancerpg.R;

import java.util.Objects;

public class DialogueView extends AppCompatActivity implements View.OnClickListener{

    //Initiating Variables
    public Animation fade,fadelate,fadelate2,fadelate3,fadelate4,fadeInOut;
    public TextView textBox,popOut;
    public ImageView imageView, backView;
    public Button button,button2,button3,button4,reset;
    public ConstraintLayout popOutLayout;
    public DialogueController dc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.decision);

        //Button call
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        reset = findViewById(R.id.reset);

        //Button listener
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        reset.setOnClickListener(this);

        //TextView Call
        textBox = findViewById(R.id.txtBox);
        popOut = findViewById(R.id.popOut);

        //Image View
        imageView = findViewById(R.id.imageView);
        backView = findViewById(R.id.backView);

        //Animation call
        fade = AnimationUtils.loadAnimation(this,R.anim.fade);
        fadelate = AnimationUtils.loadAnimation(this, R.anim.fade_late);
        fadelate2 = AnimationUtils.loadAnimation(this, R.anim.fade_late);
        fadelate3 = AnimationUtils.loadAnimation(this, R.anim.fade_late);
        fadelate4 = AnimationUtils.loadAnimation(this, R.anim.fade_late);
        fadeInOut = AnimationUtils.loadAnimation(this, R.anim.fade_in_out);

        //Animation delay
        fade.setStartOffset(300);

        //Layout call
        popOutLayout = findViewById(R.id.popOutLayout);

        //Initialize class
        dc = new DialogueController(this);
        //Gets the default text
        dc.firstScene(getResources().getStringArray(R.array.a1));
        //Shows the text
        dc.changeScene(0);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                dc.changeScene(1);
                break;
            case R.id.button2:
                dc.changeScene(2);
                break;
            case R.id.button3:
                dc.changeScene(3);
                break;
            case R.id.button4:
                dc.changeScene(4);
                break;
            case R.id.reset:
                dc.reset();
                dc.changeScene(0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        dc.save();
        dc.stop();
    }
}
