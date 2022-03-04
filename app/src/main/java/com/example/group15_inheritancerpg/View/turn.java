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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import com.example.group15_inheritancerpg.Controller.Combat;
import com.example.group15_inheritancerpg.Model.Hero;
import com.example.group15_inheritancerpg.Model.Monster;
import com.example.group15_inheritancerpg.R;

import java.util.Objects;

@SuppressLint("SetTextI18n")
public class turn extends AppCompatActivity implements View.OnClickListener {

    ProgressBar heroHpBar,heroMpBar,monsHpBar,monsMpBar; //hp and mp bars
    Button basicAttack,fullSlash,chargedAttack,stun,main;
    TextView mheroHp,mheroMp,mheroName,mmonsHp,mmonsMp, mmonsName,menuText, mwinIndicator;
    ConstraintLayout menuBox;
    FrameLayout infoBox,heroStat,monsStat;
    Animation leftRight,rightLeft,ntest,ntest2;
    ImageView test1,test2;

    Hero hero;
    Monster monster;
    Combat langerbaul;
    int t1,t2,mo1,mo2,oneh=328;
    float tt1,tt2,mm1,mm2;

    //TODO: did some animation thing
    //TODO: need to add some type of delay
    public void asdf (){
        t1 = hero.getHeroCurrentSpeed();
        tt1 = t1 * 1f / oneh;
        mo1 = monster.getMonsCurrentSpeed();
        tt1 = mo1 * 1f / oneh;
    }
    public void asdf1 () {
        t2 =hero.getHeroCurrentSpeed();
        tt2 = t2 * 1f / oneh;
        mo2=monster.getMonsCurrentSpeed();
        mm2 = mo2 * 1f / oneh;
        Log.d(TAG, "turnCheck: "+tt1 + "  "+tt2);

        ntest = new TranslateAnimation( Animation.RELATIVE_TO_PARENT, tt1, Animation.RELATIVE_TO_PARENT,  tt2, Animation.RELATIVE_TO_PARENT,0f, Animation.RELATIVE_TO_PARENT, 0f);
        ntest.setDuration(1500);
        ntest.setStartOffset(200);
        ntest.setInterpolator(new FastOutSlowInInterpolator());
        ntest.setFillAfter(true);

        ntest2 = new TranslateAnimation( Animation.RELATIVE_TO_PARENT, mm1, Animation.RELATIVE_TO_PARENT,  mm2, Animation.RELATIVE_TO_PARENT,0f, Animation.RELATIVE_TO_PARENT, 0f);
        ntest2.setDuration(1500);
        ntest2.setStartOffset(200);
        ntest2.setInterpolator(new FastOutSlowInInterpolator());
        ntest2.setFillAfter(true);

        test1.startAnimation(ntest);
        test2.startAnimation(ntest2);
    }

    public void turnCheck() {
        asdf();
        if(langerbaul.speed()) {
            // Hero's turn
            showButton();
        } else {
            // Monster's turn
            hideButton();
        }
        asdf1();
    }

    public void battle() { //attacks
        if (langerbaul.getReset()) {
            win();
            langerbaul.setReset(false);
        } else {
            hide(langerbaul.battlePhase());
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
        if(langerbaul.reset()) {
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

        //Initializing class
        hero = new Hero(this, "Knight", 1500, 100, 120, R.array.move1, R.array.move5, R.array.move3, R.array.move4);
        monster = new Monster(this, "Barathrum", 1000, 75, 90 , R.array.move1, R.array.move5, R.array.move3, R.array.move4);

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
        basicAttack.setText(hero.getHeroSkillName1());
        fullSlash.setText(hero.getHeroSkillName2());
        chargedAttack.setText(hero.getHeroSkillName3());
        stun.setText(hero.getHeroSkillName4());

        //Layout
        menuBox = findViewById(R.id.menuBox);
        infoBox = findViewById(R.id.infoTextBox);
        heroStat = findViewById(R.id.heroStatsLayout);
        monsStat = findViewById(R.id.monsStatsLayout);

        //Animating
        heroStat.setAnimation(leftRight);
        monsStat.setAnimation(rightLeft);

        //test
        test1 = findViewById(R.id.imageView2);
        test2 = findViewById(R.id.imageView3);

        langerbaul = new Combat(hero, monster, menuText);

        turnCheck();
    }

    @Override
    public void onClick(View v){
        hideButton();
        //Sets the winning text to gone
        mwinIndicator.setVisibility(View.GONE);
        switch (v.getId()) {
            case R.id.basicAttack:
                hero.setHeroAtkState(1);
                battle();
                break;
            case R.id.fullSlash:
                hero.setHeroAtkState(2);
                battle();
                break;
            case R.id.chargedAttack:
                hero.setHeroAtkState(3);
                battle();
                break;
            case R.id.stun:
                hero.setHeroAtkState(4);
                battle();
                break;
        }
    }

}