package com.example.group15_inheritancerpg.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.group15_inheritancerpg.Controller.CombatController;
import com.example.group15_inheritancerpg.R;

import java.util.Objects;

@SuppressLint("SetTextI18n")
public class CombatView extends AppCompatActivity implements View.OnClickListener {

    public ProgressBar heroHpBar,heroMpBar,monsHpBar,monsMpBar; //hp and mp bars
    public Button basicAttack,fullSlash,chargedAttack,stun,main,info;
    public TextView mheroHp,mheroMp,mheroName,mmonsHp,mmonsMp, mmonsName,menuText, mwinIndicator;
    public ConstraintLayout menuBox;
    public FrameLayout infoBox,heroStat,monsStat;
    public Animation leftRight,rightLeft,ntest,ntest2;
    public ImageView test1,test2;

    CombatController langerbaul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fullscreen code
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.turn);

        //Class call
        langerbaul = new CombatController(this);

        //Animation Call
        leftRight = AnimationUtils.loadAnimation(this,R.anim.left_to_right);
        leftRight.setStartOffset(500);
        rightLeft = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        rightLeft.setStartOffset(500);

        //Button call
        basicAttack = findViewById(R.id.basicAttack);
        fullSlash = findViewById(R.id.fullSlash);
        chargedAttack = findViewById(R.id.chargedAttack);
        stun = findViewById(R.id.stun);
        main = findViewById(R.id.main);
        info = findViewById(R.id.info);

        //Progress bar call
        heroHpBar = findViewById(R.id.heroHpBar);
        heroHpBar.setMax(100);
        heroMpBar = findViewById(R.id.heroMpBar);
        heroMpBar.setMax(100);
        monsHpBar = findViewById(R.id.monsHpBar);
        monsHpBar.setMax(100);
        monsMpBar = findViewById(R.id.monsMpBar);
        monsMpBar.setMax(100);

        //Textview Call
        mheroHp = findViewById(R.id.heroHp);
        mheroMp = findViewById(R.id.heroMp);
        mheroName = findViewById(R.id.heroName);
        mmonsHp = findViewById(R.id.monsHp);
        mmonsMp = findViewById(R.id.monsMp);
        mmonsName = findViewById(R.id.monsName);
        menuText = findViewById(R.id.menuText);
        mwinIndicator = findViewById(R.id.winIndicator);

        //Layout
        menuBox = findViewById(R.id.menuBox);
        infoBox = findViewById(R.id.infoTextBox);
        heroStat = findViewById(R.id.heroStatsLayout);
        monsStat = findViewById(R.id.monsStatsLayout);

        //Button Listeners
        basicAttack.setOnClickListener(this);
        fullSlash.setOnClickListener(this);
        chargedAttack.setOnClickListener(this);
        stun.setOnClickListener(this);
        main.setOnClickListener(this);
        menuBox.setOnClickListener(this);
        infoBox.setOnClickListener(this);
        info.setOnClickListener(this);

        //Set Text
        langerbaul.textSet();

        //Animating
        heroStat.setAnimation(leftRight);
        monsStat.setAnimation(rightLeft);

        //test
        test1 = findViewById(R.id.imageView2);
        test2 = findViewById(R.id.imageView3);

        langerbaul.turnCheck();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.basicAttack:
                langerbaul.battle(1);
                break;
            case R.id.fullSlash:
                langerbaul.battle(2);
                break;
            case R.id.chargedAttack:
                langerbaul.battle(3);
                break;
            case R.id.stun:
                langerbaul.battle(4);
                break;
            case R.id.main:
                Intent intent = new Intent(CombatView.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menuBox:
                langerbaul.next();
                break;
            case R.id.info:
                langerbaul.info();
                break;
            case R.id.infoTextBox:
                langerbaul.info();
                break;
        }
    }

}