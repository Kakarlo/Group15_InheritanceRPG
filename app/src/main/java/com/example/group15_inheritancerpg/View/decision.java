package com.example.group15_inheritancerpg.View;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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
import androidx.core.text.HtmlCompat;

import com.example.group15_inheritancerpg.Controller.DecisionMaking;
import com.example.group15_inheritancerpg.Model.Dialogue;
import com.example.group15_inheritancerpg.R;

import java.util.Objects;

public class decision extends AppCompatActivity implements View.OnClickListener{

    Dialogue d;
    DecisionMaking dec;
    Animation fade,fadelate,fadelate2,fadelate3,fadelate4,fadeInOut;
    TextView textBox,popOut;
    ImageView imageView, backView;
    Button button,button2,button3,button4,back;
    ConstraintLayout popOutLayout;
    CountDownTimer timer;

    public void textChange() {
        if (d.getNumOfChoice() == 4){ //for 4 choices scenario
            choices4();
        } else if (d.getNumOfChoice() == 3) { //for 3 choice scenario
            choices3();
        } else if (d.getNumOfChoice() == 2) { // for 2 choice scenario
            choices2();
        } else if (d.getNumOfChoice() == 1) { // for 1 choice scenario
            choices1();
        }
        imageView.setImageDrawable(dec.imgChange());
        d.setPopOut(true);
        popOut();
    }

    public void popOutText() {
        switch (d.getPopOutText()) {
            case 1:
                popOut.setText(R.string.popOut1);
                popOut.setVisibility(View.VISIBLE);
                break;
            case 2:
                popOut.setText(R.string.popOut2);
                popOut.setVisibility(View.VISIBLE);
        }
        popOutLayout.setVisibility(View.VISIBLE);
        popOutLayout.startAnimation(fadeInOut);
        popOutLayout.setClickable(true);
        fadeInOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                popOut.setVisibility(View.GONE);
                popOutLayout.setVisibility(View.GONE);
                popOutLayout.setClickable(false);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        d.setPopOutText(0);
    }

    public void popOut() {
        popOutLayout.setVisibility(View.VISIBLE);
        popOutLayout.setClickable(true);
        if (d.getPopOut()) {
            timer = new CountDownTimer(d.getDelay() + 1500, d.getDelay() + 1500) {
                public void onTick(long l) { }
                public void onFinish() {
                    popOutLayout.setVisibility(View.GONE);
                    popOutLayout.setClickable(false);
                    d.setPopOut(false);
                    timer.cancel();
                }
            };
            timer.start();
        }
    }

    public void choices4() {
        //Changes button visibility
        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        //Sets the text
        textBox.setText(HtmlCompat.fromHtml(d.getMtextString(0),HtmlCompat.FROM_HTML_MODE_COMPACT));
        button.setText(d.getMtextString(1));
        button2.setText(d.getMtextString(2));
        button3.setText(d.getMtextString(3));
        button4.setText(d.getMtextString(4));
        //Animation delay
        fadelate.setStartOffset(d.getDelay()+300);
        fadelate2.setStartOffset(d.getDelay()+600);
        fadelate3.setStartOffset(d.getDelay()+900);
        fadelate4.setStartOffset(d.getDelay()+1200);
        //Starts Animation
        textBox.startAnimation(fade);
        button.startAnimation(fadelate);
        button2.startAnimation(fadelate2);
        button3.startAnimation(fadelate3);
        button4.startAnimation(fadelate4);
        //Sets the imageNumber
        d.setImageNum(9);
    }

    public void choices3() {
        //Changes button visibility
        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        //Sets the text
        textBox.setText(HtmlCompat.fromHtml(d.getMtextString(0),HtmlCompat.FROM_HTML_MODE_COMPACT));
        button.setText(d.getMtextString(1));
        button2.setText(d.getMtextString(2));
        button3.setText(d.getMtextString(3));
        //Animation delay
        fadelate.setStartOffset(d.getDelay()+300);
        fadelate2.setStartOffset(d.getDelay()+600);
        fadelate3.setStartOffset(d.getDelay()+900);
        //Starts Animation
        textBox.startAnimation(fade);
        button.startAnimation(fadelate);
        button2.startAnimation(fadelate2);
        button3.startAnimation(fadelate3);
        //Sets the imageNumber
        d.setImageNum(7);
    }

    public void choices2() {
        //Changes button visibility
        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        //Sets the text and starts the animation
        textBox.setText(HtmlCompat.fromHtml(d.getMtextString(0),HtmlCompat.FROM_HTML_MODE_COMPACT));
        button.setText(d.getMtextString(1));
        button2.setText(d.getMtextString(2));
        //Animation delay
        fadelate.setStartOffset(d.getDelay()+300);
        fadelate2.setStartOffset(d.getDelay()+600);
        //Starts Animation
        textBox.startAnimation(fade);
        button.startAnimation(fadelate);
        button2.startAnimation(fadelate2);
        //Sets the imageNumber
        d.setImageNum(5);
    }

    public void choices1() {
        //Changes button visibility
        button.setVisibility(View.VISIBLE);
        //Sets the text and starts the animation
        textBox.setText(HtmlCompat.fromHtml(d.getMtextString(0),HtmlCompat.FROM_HTML_MODE_COMPACT));
        button.setText(d.getMtextString(1));
        //Animation delay
        fadelate.setStartOffset(d.getDelay()+300);
        //Starts Animation
        textBox.startAnimation(fade);
        button.startAnimation(fadelate);
        //Sets the imageNumber
        d.setImageNum(3);
    }

    public void hideButton() {
        //Changes button visibility
        button.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
    }

    public void clearAnim() {
        textBox.clearAnimation();
        button.clearAnimation();
        button2.clearAnimation();
        button3.clearAnimation();
        button4.clearAnimation();
        popOutLayout.clearAnimation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.decision);

        d = new Dialogue(this, R.array.a1);
        dec = new DecisionMaking(d);

        //Button call
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.base);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        back = findViewById(R.id.back);

        //Button listener
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(decision.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

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

        hideButton();
        dec.sceneCheck();
        textChange();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        //Clear pending animation
        clearAnim();
        //Hides the button
        hideButton();
        switch (v.getId()) {
            case R.id.button:
                d.setButtonState(1);
                break;
            case R.id.base:
                d.setButtonState(2);
                break;
            case R.id.button3:
                d.setButtonState(3);
                break;
            case R.id.button4:
                d.setButtonState(4);
                break;
        }
        Log.d(TAG, "onClick: "+d.getChoice());
        dec.sceneCheck();
        textChange();
        if (d.getPopOutText() > 0) {
            popOutText();
        }
    }

}