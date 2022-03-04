package com.example.group15_inheritancerpg.Model.Data;

import com.example.group15_inheritancerpg.Controller.Combat;

public class Monster {
    private final Combat c;

    public Monster (Combat combat, String monsName, int monsHP, int monsMP, int monsBaseSpeed) {
        this.c = combat;
        this.monsName = monsName;
        this.monsHP = monsHP;
        this.monsMaxHP = monsHP;
        this.monsMP = monsMP;
        this.monsMaxMP = monsMP;
        this.monsBaseSpeed = monsBaseSpeed;
    }

    public Monster (Combat combat, String monsName, int monsHP, int monsMP,
                 int monsBaseSpeed, String[] move1, String[] move2, String[] move3, String[] move4) {
        this.c = combat;
        this.monsName = monsName;
        this.monsHP = monsHP;
        this.monsMaxHP = monsHP;
        this.monsMP = monsMP;
        this.monsMaxMP = monsMP;
        this.monsBaseSpeed = monsBaseSpeed;
        this.monsTxt1 = move1;
        this.monsTxt2 = move2;
        this.monsTxt3 = move3;
        this.monsTxt4 = move4;
    }
    
    //Monster Class
    private int monsHP;
    private int monsMaxHP;
    private int monsMP;
    private int monsMaxMP;
    private int monsDamage;
    private int monsBaseSpeed;
    private int monsCurrentSpeed;
    private int monsStunned;
    private int monsAtkState;
    private String monsName;
    private String[] monsTxt1, monsTxt2, monsTxt3, monsTxt4;

    //Getters
    //Skill selected
    public int getMonsAtkState() {return this.monsAtkState;}
    //Skill int[] names
    public String getMonsSkill1() {return this.monsTxt1[0];}
    public String getMonsSkill2() {return this.monsTxt2[0];}
    public String getMonsSkill3() {return this.monsTxt3[0];}
    public String getMonsSkill4() {return this.monsTxt4[0];}
    //Skill actual name
    public String getMonsSkillName1() {return this.monsTxt1[1];}
    public String getMonsSkillName2() {return this.monsTxt2[1];}
    public String getMonsSkillName3() {return this.monsTxt3[1];}
    public String getMonsSkillName4() {return this.monsTxt4[1];}
    //Skill Text
    public String monsTxt1_1() {return this.monsTxt1[2];}
    public String monsTxt2_1() {return this.monsTxt2[2];}
    public String monsTxt3_1() {return this.monsTxt3[2];}
    public String monsTxt4_1() {return this.monsTxt4[2];}
    public String monsTxt1_2() {return this.monsTxt1[3];}
    public String monsTxt2_2() {return this.monsTxt2[3];}
    public String monsTxt3_2() {return this.monsTxt3[3];}
    public String monsTxt4_2() {return this.monsTxt4[3];}
    //Skill int[] (contains the data of the skill)
    public int[] getMonsAtk1() {return c.GetIntArray(getMonsSkill1());}
    public int[] getMonsAtk2() {return c.GetIntArray(getMonsSkill2());}
    public int[] getMonsAtk3() {return c.GetIntArray(getMonsSkill3());}
    public int[] getMonsAtk4() {return c.GetIntArray(getMonsSkill4());}
    
    public int getMonsHP() {return this.monsHP;}
    public int getMonsMaxHP() {return this.monsMaxHP;}
    public int getMonsMP() {return this.monsMP;}
    public int getMonsMaxMP() {return this.monsMaxMP;}
    public int getMonsDamage() {return this.monsDamage;}
    public int getMonsBaseSpeed() {return this.monsBaseSpeed;}
    public int getMonsCurrentSpeed() {return this.monsCurrentSpeed;}
    public int getMonsStunned() {return this.monsStunned;}
    public int getMonsHpPercent() {return this.monsHP * 100 / this.monsMaxHP;}
    public int getMonsMpPercent() {return this.monsMP * 100 / this.monsMaxMP;}
    public String getMonsName() {return this.monsName;}

    //Setters
    public void setMonsHP(int monsHP) {this.monsHP = monsHP;}
    public void setMonsMaxHP(int monsMaxHP) {this.monsMaxHP = monsMaxHP;}
    public void setMonsMP(int monsMP) {this.monsMP = monsMP;}
    public void setMonsMaxMP(int monsMaxMP) {this.monsMaxMP = monsMaxMP;}
    public void setMonsDamage(int monsDamage) {this.monsDamage = monsDamage;}
    public void setMonsBaseSpeed(int monsBaseSpeed) {this.monsBaseSpeed = monsBaseSpeed;}
    public void setMonsCurrentSpeed(int monsCurrentSpeed) {this.monsCurrentSpeed = monsCurrentSpeed;}
    public void setMonsName(String monsName) {this.monsName = monsName;}
    public void setMonsStunned(int monsStunned) {this.monsStunned = monsStunned;}
}

