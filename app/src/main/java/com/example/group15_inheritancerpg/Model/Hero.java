package com.example.group15_inheritancerpg.Model;

import android.content.Context;

public class Hero {
    //Default Constructor
    public Hero (Context context) {this.context = context;}

    public Hero (Context context, String heroName, int heroHP, int heroMP, int heroBaseSpeed) {
        this.context = context;
        this.heroName = heroName;
        this.heroHP = heroHP;
        this.heroMaxHP = heroHP;
        this.heroMP = heroMP;
        this.heroMaxMP = heroMP;
        this.heroBaseSpeed = heroBaseSpeed;
    }

    public Hero (Context context, String heroName, int heroHP, int heroMP,
                 int heroBaseSpeed, int move1, int move2, int move3, int move4) {
        this.context = context;
        this.heroName = heroName;
        this.heroHP = heroHP;
        this.heroMaxHP = heroHP;
        this.heroMP = heroMP;
        this.heroMaxMP = heroMP;
        this.heroBaseSpeed = heroBaseSpeed;
        this.heroTxt1 = context.getResources().getStringArray(move1);
        this.heroTxt2 = context.getResources().getStringArray(move2);
        this.heroTxt3 = context.getResources().getStringArray(move3);
        this.heroTxt4 = context.getResources().getStringArray(move4);
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
    private int heroAtkState;
    private final Context context;
    private String heroName;
    private String[] heroTxt1, heroTxt2, heroTxt3, heroTxt4;
    //TODO:made new changes

    //Getter
    //Skill selected
    public int getHeroAtkState() {return this.heroAtkState;}
    //Skill int[] names
    public String getHeroSkill1() {return this.heroTxt1[0];}
    public String getHeroSkill2() {return this.heroTxt2[0];}
    public String getHeroSkill3() {return this.heroTxt3[0];}
    public String getHeroSkill4() {return this.heroTxt4[0];}
    //Skill actual name
    public String getHeroSkillName1() {return this.heroTxt1[1];}
    public String getHeroSkillName2() {return this.heroTxt2[1];}
    public String getHeroSkillName3() {return this.heroTxt3[1];}
    public String getHeroSkillName4() {return this.heroTxt4[1];}
    //Skill Text
    public String heroTxt1_1() {return this.heroTxt1[2];}
    public String heroTxt2_1() {return this.heroTxt2[2];}
    public String heroTxt3_1() {return this.heroTxt3[2];}
    public String heroTxt4_1() {return this.heroTxt4[2];}
    public String heroTxt1_2() {return this.heroTxt1[3];}
    public String heroTxt2_2() {return this.heroTxt2[3];}
    public String heroTxt3_2() {return this.heroTxt3[3];}
    public String heroTxt4_2() {return this.heroTxt4[3];}
    //Skill int[] (contains the data of the skill)
    public int[] getHeroAtk1() {
        int i = context.getResources().getIdentifier(getHeroSkill1(), "array", context.getPackageName());
        return context.getResources().getIntArray(i);
    }
    public int[] getHeroAtk2() {
        int i = context.getResources().getIdentifier(getHeroSkill2(), "array", context.getPackageName());
        return context.getResources().getIntArray(i);
    }
    public int[] getHeroAtk3() {
        int i = context.getResources().getIdentifier(getHeroSkill3(), "array", context.getPackageName());
        return context.getResources().getIntArray(i);
    }
    public int[] getHeroAtk4() {
        int i = context.getResources().getIdentifier(getHeroSkill4(), "array", context.getPackageName());
        return context.getResources().getIntArray(i);
    }

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
    public void setHeroAtkState(int state) {this.heroAtkState = state;}
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
