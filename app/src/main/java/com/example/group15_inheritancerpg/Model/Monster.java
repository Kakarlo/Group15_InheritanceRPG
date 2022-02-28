package com.example.group15_inheritancerpg.Model;

public class Monster {
    //Monster Class
    private int monsHP;
    private int monsMaxHP;
    private int monsMP;
    private int monsMaxMP;
    private int monsDamage;
    private int monsBaseSpeed;
    private int monsCurrentSpeed;
    private int monsStunned;
    private String monsName;

    public Monster (String monsName,int monsHP, int monsMP, int monsBaseSpeed) {
        this.monsName = monsName;
        this.monsHP = monsHP;
        this.monsMaxHP = monsHP;
        this.monsMP = monsMP;
        this.monsMaxMP = monsMP;
        this.monsBaseSpeed = monsBaseSpeed;
    }

    //Getters
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

