package com.example.group15_inheritancerpg.Controller;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.example.group15_inheritancerpg.Model.Hero;
import com.example.group15_inheritancerpg.Model.Monster;
import com.example.group15_inheritancerpg.Model.MoveSets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

@SuppressLint("SetTextI18n")
public class GameBehavior {

    int random;
    int speedLine = 300;
    boolean win;
    Method heroSkill1, heroSkill2, heroSkill3, heroSkill4;
    Method monsSkill1, monsSkill2, monsSkill3, monsSkill4;


    //Speed System
    public void speed(Hero hero, Monster monster){
        while (hero.getHeroCurrentSpeed() <= speedLine && monster.getMonsCurrentSpeed() <= speedLine) {
            hero.setHeroCurrentSpeed(hero.getHeroCurrentSpeed() + hero.getHeroBaseSpeed());
            monster.setMonsCurrentSpeed(monster.getMonsCurrentSpeed() + monster.getMonsBaseSpeed());
        }
        if (hero.getHeroCurrentSpeed() == monster.getMonsCurrentSpeed()) {
            Random randomizer = new Random();
            int rando = randomizer.nextInt(100);
            if (rando >= 50) {
                hero.setHeroCurrentSpeed(hero.getHeroCurrentSpeed() - 10);
            } else {
                monster.setMonsCurrentSpeed(monster.getMonsCurrentSpeed() - 10);
            }
        }
        Log.d(TAG, "hero: "+hero.getHeroCurrentSpeed());
        Log.d(TAG, "monster: "+monster.getMonsCurrentSpeed());
    }

    //Move sets
    public void moveSets(Hero hero) throws Exception {
        String className = "com.example.group15_inheritancerpg.Model.MoveSets";
        Class<?> c = Class.forName(className);
        Object move = c.newInstance();

        heroSkill1 = move.getClass().getDeclaredMethod(hero.getSkill1(), Hero.class, Monster.class, TextView.class, int.class);
        heroSkill2 = move.getClass().getDeclaredMethod(hero.getSkill2(), Hero.class, Monster.class, TextView.class, int.class);
        heroSkill3 = move.getClass().getDeclaredMethod(hero.getSkill3(), Hero.class, Monster.class, TextView.class, int.class);
        heroSkill4 = move.getClass().getDeclaredMethod(hero.getSkill4(), Hero.class, Monster.class, TextView.class, int.class);

        monsSkill1 = move.getClass().getDeclaredMethod(hero.getSkill1(), Monster.class, Hero.class, TextView.class, int.class);
        monsSkill2 = move.getClass().getDeclaredMethod(hero.getSkill2(), Monster.class, Hero.class, TextView.class, int.class);
        monsSkill3 = move.getClass().getDeclaredMethod(hero.getSkill3(), Monster.class, Hero.class, TextView.class, int.class);
        monsSkill4 = move.getClass().getDeclaredMethod(hero.getSkill4(), Monster.class, Hero.class, TextView.class, int.class);

    }

    //battle phase //TODO: make a class or method to check the debuffs before move
    public void battlePhase(MoveSets move, Hero hero, Monster monster, TextView menuText) throws InvocationTargetException, IllegalAccessException {
        Random randomizer = new Random();
        random = randomizer.nextInt(100) + 1;
        if (hero.getHeroCurrentSpeed() >= speedLine && hero.getHeroCurrentSpeed() > monster.getMonsCurrentSpeed()) {
            if (hero.getHeroStunned() > 0) {
                menuText.setText(hero.getHeroName() + " is stunned for " + hero.getHeroStunned() + " turns");
                hero.setHeroStunned(hero.getHeroStunned() - 1);
                hero.setHeroCurrentSpeed(hero.getHeroCurrentSpeed() - speedLine);
            }else{
                if (hero.getState1()) {
                    heroSkill1.invoke(move, hero, monster, menuText, speedLine);
                    hero.setState1(false);
                }
                if (hero.getState2()) {
                    heroSkill2.invoke(move, hero, monster, menuText, speedLine);
                    hero.setState2(false);
                }
                if (hero.getState3()) {
                    heroSkill3.invoke(move, hero, monster, menuText, speedLine);
                    hero.setState3(false);
                }
                if (hero.getState4()) {
                    heroSkill4.invoke(move, hero, monster, menuText, speedLine);
                    hero.setState4(false);
                }
                if (monster.getMonsHP() <= 0) {
                    win = true;
                }
            }
        } else {
            if (monster.getMonsStunned() > 0) {
                menuText.setText(monster.getMonsName() + " is stunned for " + monster.getMonsStunned() + " turns");
                monster.setMonsStunned(monster.getMonsStunned() - 1);
                monster.setMonsCurrentSpeed(monster.getMonsCurrentSpeed() - speedLine);
            } else {
                if (monster.getMonsMP() <= 0) {
                    move.BasicAttack(monster, hero, menuText, speedLine);
                } else {
                    int randomAttack = randomizer.nextInt(4);
                    switch (randomAttack) {
                        case 0:
                            move.BasicAttack(monster, hero, menuText, speedLine);
                            break;
                        case 1:
                            if (monster.getMonsMP() - 50 >= 0) {
                                move.FullSlash(monster, hero, menuText, speedLine);
                            } else {
                                    move.BasicAttack(monster, hero, menuText, speedLine);
                            }
                            break;
                        case 2:
                            if (monster.getMonsMP() - 50 >= 0) {
                                move.ChargedAttack(monster, hero, menuText, speedLine);
                            } else {
                                move.BasicAttack(monster, hero, menuText, speedLine);
                            }
                            break;
                        case 3:
                            if (monster.getMonsMP() - 50 >= 0) {
                                move.Stun(monster, hero, menuText, speedLine);
                            } else {
                                move.BasicAttack(monster, hero, menuText, speedLine);
                            }
                            break;
                    }
                }
                if (hero.getHeroHP() <= 0) {
                    win = false;
                }
            }
        }

    }

    //reset stats back
    public boolean reset(Hero hero, Monster monster) {
        hero.setHeroHP(hero.getHeroMaxHP());
        hero.setHeroMP(hero.getHeroMaxMP());
        monster.setMonsHP(monster.getMonsMaxHP());
        monster.setMonsMP(monster.getMonsMaxMP());
        hero.setHeroDamage(0);
        hero.setHeroStunned(0);
        hero.setHeroCurrentSpeed(0);
        monster.setMonsDamage(0);
        monster.setMonsStunned(0);
        monster.setMonsCurrentSpeed(0);
        return win;
    }
}