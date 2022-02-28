package com.example.group15_inheritancerpg.View;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.group15_inheritancerpg.Controller.GameBehavior;
import com.example.group15_inheritancerpg.Model.Hero;
import com.example.group15_inheritancerpg.Model.Monster;
import com.example.group15_inheritancerpg.Model.MoveSets;
import com.example.group15_inheritancerpg.R;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@SuppressLint("SetTextI18n")
public class turn extends AppCompatActivity implements View.OnClickListener {

    ProgressBar heroHpBar,heroMpBar,monsHpBar,monsMpBar; //hp and mp bars
    Button basicAttack,fullSlash,chargedAttack,stun,main;
    TextView mheroHp,mheroMp,mheroName,mmonsHp,mmonsMp, mmonsName,menuText, mwinIndicator;
    ConstraintLayout menuBox;
    FrameLayout infoBox,heroStat,monsStat;
    Animation leftRight,rightLeft;

    GameBehavior langerbaul = new GameBehavior();
    MoveSets move = new MoveSets();
    Hero hero = new Hero(this, "Knight", 1500, 100, 110, R.string.m1, R.string.m2, R.string.m3, R.string.m4);
    Monster monster = new Monster("Barathrum", 1000, 75, 90);

    public void turnCheck() {
        if(langerbaul.speed(hero,monster)) {
            // Hero's turn
            showButton();
        } else {
            // Monster's turn
            hideButton();
        }
    }

    public void battle() { //attacks
        if (langerbaul.getReset()) {
            win();
            langerbaul.setReset(false);
        } else {
            try {
                hide(langerbaul.battlePhase(move, hero, monster, menuText));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bar();
    }

    public void showButton(){
        //enables and shows the buttons
        basicAttack.setVisibility(View.VISIBLE);
        fullSlash.setVisibility(View.VISIBLE);
        chargedAttack.setVisibility(View.VISIBLE);
        stun.setVisibility(View.VISIBLE);
        basicAttack.setClickable(true);
        fullSlash.setClickable(true);
        chargedAttack.setClickable(true);
        stun.setClickable(true);
        //disables the menuText
        menuText.setVisibility(View.GONE);
        //disables layout click
        menuBox.setClickable(false);
    }

    public void hideButton(){
        //disables and shows the buttons
        basicAttack.setVisibility(View.GONE);
        fullSlash.setVisibility(View.GONE);
        chargedAttack.setVisibility(View.GONE);
        stun.setVisibility(View.GONE);
        basicAttack.setClickable(false);
        fullSlash.setClickable(false);
        chargedAttack.setClickable(false);
        stun.setClickable(false);
        //enables the menuText
        menuText.setVisibility(View.VISIBLE);
        //enables layout click
        menuBox.setClickable(true);
    }

    public void hide(boolean move){
        if (move) {
            hideButton();
        }
    }

    //resets the game
    public void win() {
        if(langerbaul.reset(hero, monster)) {
            mwinIndicator.setVisibility(View.VISIBLE);
            mwinIndicator.setText("You WIN!");
        } else {
            mwinIndicator.setVisibility(View.VISIBLE);
            mwinIndicator.setText("You LOSE!");
        }
    }

    public void bar() {
        //sets the mp and hp
        mheroHp.setText(String.valueOf(hero.getHeroHP()));
        mheroMp.setText(String.valueOf(hero.getHeroMP()));
        mmonsHp.setText(String.valueOf(monster.getMonsHP()));
        mmonsMp.setText(String.valueOf(monster.getMonsMP()));
        //Setting the hp/mp bar
        heroHpBar.setProgress(hero.getHeroHpPercent(), true);
        heroMpBar.setProgress(hero.getHeroMpPercent(), true);
        monsHpBar.setProgress(monster.getMonsHpPercent(),true);
        monsMpBar.setProgress(monster.getMonsMpPercent(), true);

        //Changes the color of the hp
        if (hero.getHeroHpPercent() > 30 && hero.getHeroHpPercent() <= 75 ) {
            heroHpBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.Mid)));// for the r.color thingy kay go to colors and make some color
        } else if (hero.getHeroHpPercent() >= 0 && hero.getHeroHpPercent() <= 30 ) {
            heroHpBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.Low)));
        } else {
            heroHpBar.setProgressTintList(ColorStateList.valueOf((getResources().getColor(R.color.Max))));
        }
        //Changes the color of the hp
        if (monster.getMonsHpPercent() > 30 && monster.getMonsHpPercent() <= 75 ) {
            monsHpBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.Mid)));// for the r.color thingy kay go to colors and make some color
        } else if (monster.getMonsHpPercent() >= 0 && monster.getMonsHpPercent() <= 30 ) {
            monsHpBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.Low)));
        } else {
            monsHpBar.setProgressTintList(ColorStateList.valueOf((getResources().getColor(R.color.Max))));
        }
    }

    //manual movement of turns
    public void next(View v) {
        turnCheck();
        battle();
    }

    public void info(View v) {
        if(infoBox.getVisibility() == View.GONE){
            infoBox.setVisibility(View.VISIBLE);
        } else {
            infoBox.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fullscreen code
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.turn);

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

        //setting the textviews
        mheroHp.setText(String.valueOf(hero.getHeroHP()));
        mheroMp.setText(String.valueOf(hero.getHeroMP()));
        mheroName.setText(hero.getHeroName());
        mmonsHp.setText(String.valueOf(monster.getMonsHP()));
        mmonsMp.setText(String.valueOf(monster.getMonsMP()));
        mmonsName.setText(monster.getMonsName());

        //Button Listeners
        basicAttack.setOnClickListener(this);
        fullSlash.setOnClickListener(this);
        chargedAttack.setOnClickListener(this);
        stun.setOnClickListener(this);
        main.setOnClickListener(view -> {
            Intent intent = new Intent(turn.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        //Button Text
        basicAttack.setText(hero.getSkill1());
        fullSlash.setText(hero.getSkill2());
        chargedAttack.setText(hero.getSkill3());
        stun.setText(hero.getSkill4());

        //Layout
        menuBox = findViewById(R.id.menuBox);
        infoBox = findViewById(R.id.infoTextBox);
        heroStat = findViewById(R.id.heroStatsLayout);
        monsStat = findViewById(R.id.monsStatsLayout);

        //Animating
        heroStat.setAnimation(leftRight);
        monsStat.setAnimation(rightLeft);

        try {
            langerbaul.moveSets(hero);
        } catch (Exception e) {
            e.printStackTrace();
        }

        turnCheck();
    }

    @Override
    public void onClick(View v){
        hideButton();
        //Sets the winning text to gone
        mwinIndicator.setVisibility(View.GONE);
        switch (v.getId()) {
            case R.id.basicAttack:
                hero.setState1(true);
                break;
            case R.id.fullSlash:
                hero.setState2(true);
                break;
            case R.id.chargedAttack:
                hero.setState3(true);
                break;
            case R.id.stun:
                hero.setState4(true);
                break;
        }
        battle();
    }

}