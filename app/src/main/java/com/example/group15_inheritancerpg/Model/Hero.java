package com.example.group15_inheritancerpg.Model;

import android.content.Context;

public class Hero {
    //Default Constructor
    public Hero (Context context){
        this.context = context;
    }
    //Hero Class
    private int heroHP;
    private int heroMaxHP;
    private int heroMP;
    private int heroMaxMP;
    private int heroDamage;
    private int heroBaseSpeed;
    private int heroCurrentSpeed;
    private int heroStunned;
    private String heroName;
    //skills sets
    private final Context context;
    private int move1, move2, move3, move4;
    private boolean state1, state2, state3, state4;

    public void heroSkill (int move1, int move2, int move3, int move4) {
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
    }
    public String getSkill1() {return context.getResources().getString(move1).replaceAll("\\s+","");}
    public String getSkill2() {return context.getResources().getString(move2).replaceAll("\\s+","");}
    public String getSkill3() {return context.getResources().getString(move3).replaceAll("\\s+","");}
    public String getSkill4() {return context.getResources().getString(move4).replaceAll("\\s+","");}
    public String getSkillName1() {return context.getResources().getString(move1);}
    public String getSkillName2() {return context.getResources().getString(move2);}
    public String getSkillName3() {return context.getResources().getString(move3);}
    public String getSkillName4() {return context.getResources().getString(move4);}

    public int getMove1() {return this.move1;}
    public int getMove2() {return this.move2;}
    public int getMove3() {return this.move3;}
    public int getMove4() {return this.move4;}

    public Hero (Context context,
                 String heroName,
                 int heroHP,
                 int heroMP,
                 int heroBaseSpeed) {
        this.context = context;
        this.heroName = heroName;
        this.heroHP = heroHP;
        this.heroMaxHP = heroHP;
        this.heroMP = heroMP;
        this.heroMaxMP = heroMP;
        this.heroBaseSpeed = heroBaseSpeed;
    }

    public Hero (Context context,
                 String heroName,
                 int heroHP,
                 int heroMP,
                 int heroBaseSpeed,
                 int move1,
                 int move2,
                 int move3,
                 int move4) {
        this.context = context;
        this.heroName = heroName;
        this.heroHP = heroHP;
        this.heroMaxHP = heroHP;
        this.heroMP = heroMP;
        this.heroMaxMP = heroMP;
        this.heroBaseSpeed = heroBaseSpeed;
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
    }


    //Getters
    public boolean getState1() {return this.state1;}
    public boolean getState2() {return this.state2;}
    public boolean getState3() {return this.state3;}
    public boolean getState4() {return this.state4;}
    public int getHeroHP() {return this.heroHP;}
    public int getHeroMaxHP() {return this.heroMaxHP;}
    public int getHeroMP() {return this.heroMP;}
    public int getHeroMaxMP() {return this.heroMaxMP;}
    public int getHeroDamage() {return this.heroDamage;}
    public int getHeroBaseSpeed() {return this.heroBaseSpeed;}
    public int getHeroCurrentSpeed() {return this.heroCurrentSpeed;}
    public int getHeroStunned() {return this.heroStunned;}
    public int getHeroHpPercent() {return this.heroHP * 100 / this.heroMaxHP; }
    public int getHeroMpPercent() {return this.heroMP * 100 / this.heroMaxMP;}
    public String getHeroName() {return this.heroName;}

    //Setters
    public void setState1(boolean state) {this.state1 = state;}
    public void setState2(boolean state) {this.state2 = state;}
    public void setState3(boolean state) {this.state3 = state;}
    public void setState4(boolean state) {this.state4 = state;}
    public void setHeroHP(int heroHP) {this.heroHP = heroHP;}
    public void setHeroMaxHP(int heroMaxHP) {this.heroMaxHP = heroMaxHP;}
    public void setHeroMP(int heroMP) {this.heroMP = heroMP;}
    public void setHeroMaxMP(int heroMaxMP) {this.heroMaxMP = heroMaxMP;}
    public void setHeroDamage(int heroDamage) {this.heroDamage = heroDamage;}
    public void setHeroBaseSpeed(int heroBaseSpeed) {this.heroBaseSpeed = heroBaseSpeed;}
    public void setHeroCurrentSpeed(int heroCurrentSpeed) {this.heroCurrentSpeed = heroCurrentSpeed;}
    public void setHeroName(String heroName) {this.heroName = heroName;}
    public void setHeroStunned(int heroStunned) {this.heroStunned = heroStunned;}
}
