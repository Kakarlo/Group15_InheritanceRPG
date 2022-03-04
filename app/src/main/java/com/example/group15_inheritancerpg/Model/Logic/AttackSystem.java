package com.example.group15_inheritancerpg.Model.Logic;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.group15_inheritancerpg.Controller.Combat;
import com.example.group15_inheritancerpg.Model.Data.Hero;
import com.example.group15_inheritancerpg.Model.Data.Monster;
import com.example.group15_inheritancerpg.R;

import java.util.Random;

public class AttackSystem {
    public Hero hero;
    public Monster monster;
    private final Combat c;

    public AttackSystem(Combat combat) {
        this.c = combat;
        //Initializing class
        this.hero = new Hero(combat, "Knight", 1500, 100, 120, c.GetArray(R.array.move1), c.GetArray(R.array.move5), c.GetArray(R.array.move3), c.GetArray(R.array.move4));
        this.monster = new Monster(combat, "Barathrum", 1000, 75, 90 , c.GetArray(R.array.move1), c.GetArray(R.array.move5), c.GetArray(R.array.move3), c.GetArray(R.array.move4));
    }

    public String menuText;
    private final int speedLine = 300;
    private boolean win;
    private boolean reset;
    private boolean moved;
    int randomAttack;

    //Speed System
    public boolean speed(){
        while (hero.getHeroCurrentSpeed() <= speedLine && monster.getMonsCurrentSpeed() <= speedLine) {
            hero.setHeroCurrentSpeed(hero.getHeroCurrentSpeed() + hero.getHeroBaseSpeed()/25);
            monster.setMonsCurrentSpeed(monster.getMonsCurrentSpeed() + monster.getMonsBaseSpeed()/25);
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
//        Log.d(TAG, "after hero: "+hero.getHeroCurrentSpeed());
//        Log.d(TAG, "monster: "+monster.getMonsCurrentSpeed());
        return hero.getHeroCurrentSpeed() >= speedLine && hero.getHeroCurrentSpeed() > monster.getMonsCurrentSpeed();
    }

    //Attack system

    private void heroAttackSys(int[] atk) {
        //Checks if you have enough mp
        if(hero.getHeroMP() >= atk[3] ) {
            Random randomizer = new Random();
            int random = randomizer.nextInt(100);
            //checks for the attack chance
            if (random < atk[2]) {
                hero.setHeroDamage(randomizer.nextInt(atk[1] - atk[0]) + atk[0]);
                monster.setMonsHP(monster.getMonsHP() - hero.getHeroDamage());
                menuText = (hero.getHeroName() + heroAttackTxt(1) + hero.getHeroDamage() + " to the " + monster.getMonsName());
                c.changeText();
                heroSpecial(atk);
                Log.d(TAG, "hero: " + hero.getHeroAtkState() + hero.getHeroName() + heroAttackTxt(1) + hero.getHeroDamage() + " to the " + monster.getMonsName());
            } else {
                menuText = (hero.getHeroName() + heroAttackTxt(2));
                c.changeText();
                Log.d(TAG, "hero miss: "+ hero.getHeroAtkState()  + hero.getHeroName() + heroAttackTxt(2));
            }
            //checks if MP is max
            if (hero.getHeroMP() != hero.getHeroMaxMP() && hero.getHeroMP() < hero.getHeroMaxMP()) {
                hero.setHeroMP(hero.getHeroMP() + atk[4]);
            }
            hero.setHeroCurrentSpeed(hero.getHeroCurrentSpeed() - speedLine);
            hero.setHeroMP(hero.getHeroMP() - atk[3]);
        } else {
            menuText = ("You don't have enough MP");
            c.changeText();
        }
    }

    private void heroSpecial(int[] atk) {
        int x = atk[atk.length - 1];
        switch (x) {
            case 1:
                break;
            case 2:
                monster.setMonsStunned(atk[atk.length - 2]);
                break;
            case 3:
                int a = atk[atk.length - 2];
                int b = hero.getHeroMaxHP();
                int c = b * a / 100;
                hero.setHeroHP(hero.getHeroHP() + c);
                if (hero.getHeroHP() > hero.getHeroMaxHP()){
                    hero.setHeroHP(hero.getHeroMaxHP());
                }
                break;
            case 4:
                //monster.setMonsBurned(atk[atk.length - 2]);
                //monster.setMonsBurnedDamage(atk[atk.length - 3]);
                break;
            case 5:
                //slowness
                break;
            case 6:
                //freeze
                //freeze damage
                break;
            case 7:
                //lower enemy stats
                break;
            case 8:
                //elevate certain state
                break;
        }

    }

    private void monsAttackSys(int[] atk) {
        //Checks if you have enough mp
        monster.setMonsCurrentSpeed(monster.getMonsCurrentSpeed() - speedLine);
        if(monster.getMonsMP() >= atk[3] ) {
            Random randomizer = new Random();
            int random = randomizer.nextInt(100) + 1;
            //checks for the attack chance
            if (random < atk[2]) {
                monster.setMonsDamage(randomizer.nextInt(atk[1] - atk[0]) + atk[0]);
                hero.setHeroHP(hero.getHeroHP() - monster.getMonsDamage());
                menuText = (monster.getMonsName() + monsAttackTxt(1) + monster.getMonsDamage() + " to the " + hero.getHeroName());
                c.changeText();
                monsSpecial(atk);
                Log.d(TAG, "monsAttackSys: " + monster.getMonsName() + monsAttackTxt(1) + monster.getMonsDamage() + " to the " + hero.getHeroName());
            } else {
                menuText = (monster.getMonsName() + monsAttackTxt(2));
                c.changeText();
                Log.d(TAG, "mons miss: " + monster.getMonsName() + monsAttackTxt(2));
            }
            //checks if MP is max
            if (monster.getMonsMP() != monster.getMonsMaxMP() && monster.getMonsMP() < monster.getMonsMaxMP()) {
                monster.setMonsMP(monster.getMonsMP() + atk[4]);
            }
            monster.setMonsMP(monster.getMonsMP() - atk[3]);
        } else {
            int[] basic = monster.getMonsAtk1();
            Random randomizer = new Random();
            monster.setMonsDamage(randomizer.nextInt(basic[1] - basic[0]) + basic[0]);
            hero.setHeroHP(hero.getHeroHP() - monster.getMonsDamage());
            menuText = (monster.getMonsName() + monsAttackTxt(3) + monster.getMonsDamage() + " to the " + hero.getHeroName());
            c.changeText();

            if (monster.getMonsMP() != monster.getMonsMaxMP() && monster.getMonsMP() < monster.getMonsMaxMP()) {
                monster.setMonsMP(monster.getMonsMP() + basic[4]);
            }
        }
    }

    private void monsSpecial(int[] atk) {
        int x = atk[atk.length - 1];
        switch (x) {
            case 1:
                break;
            case 2:
                hero.setHeroStunned(atk[atk.length - 2]);
                break;
            case 3:
                int a = atk[atk.length - 2];
                int b = monster.getMonsMaxHP();
                int c = b * a / 100;
                monster.setMonsHP(monster.getMonsHP() + c);
                if (monster.getMonsHP() > monster.getMonsMaxHP()) {
                    monster.setMonsHP(monster.getMonsMaxHP());
                }
                break;
            case 4:
                //monster.setMonsBurned(atk[atk.length - 2]);
                //monster.setMonsBurnedDamage(atk[atk.length - 3]);
                break;
            case 5:
                //slowness
                break;
            case 6:
                //freeze
                //freeze damage
                break;
            case 7:
                //lower enemy stats
                break;
            case 8:
                //elevate certain state
                break;
        }

    }

    private String heroAttackTxt(int i ) {
        String text = " ";
        if (i == 1) {
            switch (hero.getHeroAtkState()) {
                case 1:
                    text = hero.heroTxt1_1();
                    break;
                case 2:
                    text = hero.heroTxt2_1();
                    break;
                case 3:
                    text = hero.heroTxt3_1();
                    break;
                case 4:
                    text = hero.heroTxt4_1();
                    break;
            }
        } else if (i == 2) {
            switch (hero.getHeroAtkState()) {
                case 1:
                    text = hero.heroTxt1_2();
                    break;
                case 2:
                    text = hero.heroTxt2_2();
                    break;
                case 3:
                    text = hero.heroTxt3_2();
                    break;
                case 4:
                    text = hero.heroTxt4_2();
                    break;
            }
        }
        return text;
    }

    private String monsAttackTxt(int i ) {
        String text = " ";
        if (i == 1) {
            switch (randomAttack) {
                case 0:
                    text = monster.monsTxt1_1();
                    break;
                case 1:
                    text = monster.monsTxt2_1();
                    break;
                case 2:
                    text = monster.monsTxt3_1();
                    break;
                case 3:
                    text = monster.monsTxt4_1();
                    break;
            }
        } else if (i == 2) {
            switch (randomAttack) {
                case 0:
                    text = monster.monsTxt1_2();
                    break;
                case 1:
                    text = monster.monsTxt2_2();
                    break;
                case 2:
                    text = monster.monsTxt3_2();
                    break;
                case 3:
                    text = monster.monsTxt4_2();
                    break;
            }
        } else {
            Log.d(TAG, "monsAttackTxt: ");
            text = monster.monsTxt1_1();
        }
        return text;
    }

    //battle phase
    public boolean battlePhase() {
        Random randomizer = new Random();
        moved = false;
        if (hero.getHeroCurrentSpeed() > monster.getMonsCurrentSpeed() && hero.getHeroCurrentSpeed() >= speedLine) {
            if (!heroDebuffed()){
                Log.d(TAG, "battlePhase: " + hero.getHeroAtkState());
                switch (hero.getHeroAtkState()) {
                    case 1:
                        heroAttackSys(hero.getHeroAtk1());
                        hero.setHeroAtkState(0);
                        moved = true;
                        break;
                    case 2:
                        heroAttackSys(hero.getHeroAtk2());
                        hero.setHeroAtkState(0);
                        moved = true;
                        break;
                    case 3:
                        heroAttackSys(hero.getHeroAtk3());
                        hero.setHeroAtkState(0);
                        moved = true;
                        break;
                    case 4:
                        heroAttackSys(hero.getHeroAtk4());
                        hero.setHeroAtkState(0);
                        moved = true;
                        break;
                }
                if (monster.getMonsHP() <= 0) {
                    reset = true;
                    win = true;
                    moved = true;
                }
            }
        } else if (monster.getMonsCurrentSpeed() > hero.getHeroCurrentSpeed() && monster.getMonsCurrentSpeed() >= speedLine) {
            if (!monsterDebuffed()){
                randomAttack = randomizer.nextInt(4);
                switch (randomAttack) {
                    case 0:
                        monsAttackSys(monster.getMonsAtk1());
                        moved = true;
                        break;
                    case 1:
                        monsAttackSys(monster.getMonsAtk2());
                        moved = true;
                        break;
                    case 2:
                        monsAttackSys(monster.getMonsAtk3());
                        moved = true;
                        break;
                    case 3:
                        monsAttackSys(monster.getMonsAtk4());
                        moved = true;
                        break;
                }
                if (hero.getHeroHP() <= 0) {
                    reset = true;
                    win = false;
                    moved = true;
                }
            }
        }
        return moved;
    }

    private boolean heroDebuffed() {
        boolean heroDebuffed = false;
        if (hero.getHeroStunned() > 0) {
            menuText = (hero.getHeroName() + " is stunned for " + hero.getHeroStunned() + " turns");
            c.changeText();
            hero.setHeroStunned(hero.getHeroStunned() - 1);
            hero.setHeroCurrentSpeed(hero.getHeroCurrentSpeed() - speedLine);
            moved = true;
            heroDebuffed = true;
        }
        return heroDebuffed;
    }

    private boolean monsterDebuffed() {
        boolean monsDebuffed = false;
        if (monster.getMonsStunned() > 0) {
            menuText = (monster.getMonsName() + " is stunned for " + monster.getMonsStunned() + " turns");
            c.changeText();
            monster.setMonsStunned(monster.getMonsStunned() - 1);
            monster.setMonsCurrentSpeed(monster.getMonsCurrentSpeed() - speedLine);
            moved = true;
            monsDebuffed = true;
        }
        return monsDebuffed;
    }

    //reset stats back
    public boolean reset() {
        hero.setHeroHP(hero.getHeroMaxHP());
        hero.setHeroMP(hero.getHeroMaxMP());
        monster.setMonsHP(monster.getMonsMaxHP());
        monster.setMonsMP(monster.getMonsMaxMP());
        hero.setHeroDamage(0);
        hero.setHeroStunned(0);
        hero.setHeroCurrentSpeed(0);
        hero.setHeroAtkState(0);
        monster.setMonsDamage(0);
        monster.setMonsStunned(0);
        monster.setMonsCurrentSpeed(0);
        return win;
    }

    //Getters
    public boolean getReset() {return this.reset;}

    //Setters
    public void setReset(Boolean state) {this.reset = state;}
}
