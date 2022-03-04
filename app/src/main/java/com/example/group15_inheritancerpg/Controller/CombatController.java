package com.example.group15_inheritancerpg.Controller;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.example.group15_inheritancerpg.Model.Logic.AttackSystem;
import com.example.group15_inheritancerpg.Model.Data.Hero;
import com.example.group15_inheritancerpg.Model.Data.Monster;
import com.example.group15_inheritancerpg.R;
import com.example.group15_inheritancerpg.View.CombatView;

@SuppressLint("SetTextI18n")
public class CombatController {
    private final Hero hero;
    private final Monster monster;
    private final AttackSystem atkSys;
    private final CombatView cv;

    public CombatController(CombatView combatView) {
        this.cv = combatView;
        this.atkSys = new AttackSystem(this);
        this.hero = atkSys.hero;
        this.monster = atkSys.monster;
    }

    public void textSet() {
        //setting the textviews
        cv.mheroHp.setText(String.valueOf(hero.getHeroHP()));
        cv.mheroMp.setText(String.valueOf(hero.getHeroMP()));
        cv.mheroName.setText(hero.getHeroName());
        cv.mmonsHp.setText(String.valueOf(monster.getMonsHP()));
        cv.mmonsMp.setText(String.valueOf(monster.getMonsMP()));
        cv.mmonsName.setText(monster.getMonsName());
        //Button Text
        cv.basicAttack.setText(hero.getHeroSkillName1());
        cv.fullSlash.setText(hero.getHeroSkillName2());
        cv.chargedAttack.setText(hero.getHeroSkillName3());
        cv.stun.setText(hero.getHeroSkillName4());
    }

    public String[] GetArray(int arrID) {
        return cv.getResources().getStringArray(arrID);
    }

    public int[] GetIntArray(String ID) {
        int i = cv.getResources().getIdentifier(ID, "array", cv.getPackageName());
        return cv.getResources().getIntArray(i);
    }

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
        t2 = hero.getHeroCurrentSpeed();
        tt2 = t2 * 1f / oneh;
        mo2= monster.getMonsCurrentSpeed();
        mm2 = mo2 * 1f / oneh;
        Log.d(TAG, "turnCheck: "+tt1 + "  "+tt2);

        cv.ntest = new TranslateAnimation( Animation.RELATIVE_TO_PARENT, tt1, Animation.RELATIVE_TO_PARENT,  tt2, Animation.RELATIVE_TO_PARENT,0f, Animation.RELATIVE_TO_PARENT, 0f);
        cv.ntest.setDuration(1500);
        cv.ntest.setStartOffset(200);
        cv.ntest.setInterpolator(new FastOutSlowInInterpolator());
        cv.ntest.setFillAfter(true);

        cv.ntest2 = new TranslateAnimation( Animation.RELATIVE_TO_PARENT, mm1, Animation.RELATIVE_TO_PARENT,  mm2, Animation.RELATIVE_TO_PARENT,0f, Animation.RELATIVE_TO_PARENT, 0f);
        cv.ntest2.setDuration(1500);
        cv.ntest2.setStartOffset(200);
        cv.ntest2.setInterpolator(new FastOutSlowInInterpolator());
        cv.ntest2.setFillAfter(true);

        cv.test1.startAnimation(cv.ntest);
        cv.test2.startAnimation(cv.ntest2);
    }

    public void turnCheck() {
        asdf();
        if(atkSys.speed()) {
            // Hero's turn
            showButton();
        } else {
            // Monster's turn
            hideButton();
        }
        asdf1();
    }

    public void battle(int state) { //attacks
        cv.mwinIndicator.setVisibility(View.GONE);
        hero.setHeroAtkState(state);
        if (atkSys.getReset()) {
            win();
            atkSys.setReset(false);
            turnCheck();
        } else {
            hide(atkSys.battlePhase());
        }
        bar();
    }

    public void changeText() {cv.menuText.setText(atkSys.menuText);}

    public void showButton(){
        //enables and shows the buttons
        cv.basicAttack.setVisibility(View.VISIBLE);
        cv.fullSlash.setVisibility(View.VISIBLE);
        cv.chargedAttack.setVisibility(View.VISIBLE);
        cv.stun.setVisibility(View.VISIBLE);
        cv.basicAttack.setClickable(true);
        cv.fullSlash.setClickable(true);
        cv.chargedAttack.setClickable(true);
        cv.stun.setClickable(true);
        //disables the menuText
        cv.menuText.setVisibility(View.GONE);
        //disables layout click
        cv.menuBox.setClickable(false);
    }

    public void hideButton(){
        //disables and shows the buttons
        cv.basicAttack.setVisibility(View.GONE);
        cv.fullSlash.setVisibility(View.GONE);
        cv.chargedAttack.setVisibility(View.GONE);
        cv.stun.setVisibility(View.GONE);
        cv.basicAttack.setClickable(false);
        cv.fullSlash.setClickable(false);
        cv.chargedAttack.setClickable(false);
        cv.stun.setClickable(false);
        //enables the menuText
        cv.menuText.setVisibility(View.VISIBLE);
        //enables layout click
        cv.menuBox.setClickable(true);
    }

    public void hide(boolean move){
        if (move) {
            hideButton();
        }
    }

    //resets the game
    public void win() {
        if(atkSys.reset()) {
            cv.mwinIndicator.setVisibility(View.VISIBLE);
            cv.mwinIndicator.setText("You WIN!");
        } else {
            cv.mwinIndicator.setVisibility(View.VISIBLE);
            cv.mwinIndicator.setText("You LOSE!");
        }
    }

    public void bar() {
        //sets the mp and hp
        cv.mheroHp.setText(String.valueOf(hero.getHeroHP()));
        cv.mheroMp.setText(String.valueOf(hero.getHeroMP()));
        cv.mmonsHp.setText(String.valueOf(monster.getMonsHP()));
        cv.mmonsMp.setText(String.valueOf(monster.getMonsMP()));
        //Setting the hp/mp bar
        cv.heroHpBar.setProgress(hero.getHeroHpPercent(), true);
        cv.heroMpBar.setProgress(hero.getHeroMpPercent(), true);
        cv.monsHpBar.setProgress(monster.getMonsHpPercent(),true);
        cv.monsMpBar.setProgress(monster.getMonsMpPercent(), true);

        //Changes the color of the hp
        if (hero.getHeroHpPercent() > 30 && hero.getHeroHpPercent() <= 75 ) {
            cv.heroHpBar.setProgressTintList(ColorStateList.valueOf(cv.getResources().getColor(R.color.Mid)));// for the r.color thingy kay go to colors and make some color
        } else if (hero.getHeroHpPercent() >= 0 && hero.getHeroHpPercent() <= 30 ) {
            cv.heroHpBar.setProgressTintList(ColorStateList.valueOf(cv.getResources().getColor(R.color.Low)));
        } else {
            cv. heroHpBar.setProgressTintList(ColorStateList.valueOf((cv.getResources().getColor(R.color.Max))));
        }
        //Changes the color of the hp
        if (monster.getMonsHpPercent() > 30 && monster.getMonsHpPercent() <= 75 ) {
            cv.monsHpBar.setProgressTintList(ColorStateList.valueOf(cv.getResources().getColor(R.color.Mid)));// for the r.color thingy kay go to colors and make some color
        } else if (monster.getMonsHpPercent() >= 0 && monster.getMonsHpPercent() <= 30 ) {
            cv.monsHpBar.setProgressTintList(ColorStateList.valueOf(cv.getResources().getColor(R.color.Low)));
        } else {
            cv.monsHpBar.setProgressTintList(ColorStateList.valueOf((cv.getResources().getColor(R.color.Max))));
        }
    }

    //manual movement of turns
    public void next() {
        turnCheck();
        battle(0);
    }

    public void info() {
        if(cv.infoBox.getVisibility() == View.GONE){
            cv.infoBox.setVisibility(View.VISIBLE);
        } else {
            cv.infoBox.setVisibility(View.GONE);
        }
    }
}