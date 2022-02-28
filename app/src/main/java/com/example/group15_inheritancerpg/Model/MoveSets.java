package com.example.group15_inheritancerpg.Model;

import android.annotation.SuppressLint;
import android.widget.TextView;

import java.util.Random;

@SuppressWarnings("unused")
@SuppressLint("SetTextI18n")
public class MoveSets {
    //TODO:do this the proper way

    public MoveSets (){}

    ////////////////Hero Skill Data////////////////

    public void BasicAttack(Hero hero, Monster monster, TextView menuText, int speedLine) {
        //Basic Attack
        int attackMin = 75;
        int attackMax = 100;
        int hitChance = 100;
        int mpCost = 0;
        int mpAdd = 25;
        if(hero.getHeroMP() >= mpCost ) {
            Random randomizer = new Random();
            int random = randomizer.nextInt(100) + 1;
            //checks for the attack chance
            if (random < hitChance) {
                hero.setHeroDamage(randomizer.nextInt(attackMax - attackMin) + attackMin);
                monster.setMonsHP(monster.getMonsHP() - hero.getHeroDamage());
                menuText.setText(hero.getHeroName() + "'s basic attack dealt " + hero.getHeroDamage() + " to the " + monster.getMonsName());
            } else {
                menuText.setText(hero.getHeroName() + "'s basic attack has failed");
            }
            //checks if MP is max
            if (hero.getHeroMP() != hero.getHeroMaxMP() && hero.getHeroMP() < hero.getHeroMaxMP()) {
                hero.setHeroMP(hero.getHeroMP() + mpAdd);
            }
            hero.setHeroCurrentSpeed(hero.getHeroCurrentSpeed() - speedLine);
        } else {
            menuText.setText("You don't have enough MP");
        }
    }

    public void FullSlash(Hero hero, Monster monster, TextView menuText, int speedLine) {
        //Full Slash
        int attackMin = 200;
        int attackMax = 300;
        int hitChance = 50;
        int mpCost = 50;
        if(hero.getHeroMP() >= mpCost ) {
            //Random
            Random randomizer = new Random();
            int random = randomizer.nextInt(100) + 1;

            if (random < hitChance) {
                hero.setHeroDamage(randomizer.nextInt(attackMax - attackMin) + attackMin);
                monster.setMonsHP(monster.getMonsHP() - hero.getHeroDamage());
                menuText.setText(hero.getHeroName() + "'s full slash dealt " + hero.getHeroDamage() + " to the " + monster.getMonsName());
            } else {
                menuText.setText(hero.getHeroName() + "'s full slash has failed");
            }
            hero.setHeroMP(hero.getHeroMP() - mpCost);
            hero.setHeroCurrentSpeed(hero.getHeroCurrentSpeed() - speedLine);
        } else {
        menuText.setText("You don't have enough MP");
        }
    }

    public void ChargedAttack(Hero hero, Monster monster, TextView menuText, int speedLine) {
        //Charged Attack
        int attackMin = 150;
        int attackMax = 200;
        int hitChance = 100;
        int mpCost = 25;
        if(hero.getHeroMP() >= mpCost ) {
            //Random
            Random randomizer = new Random();
            int random = randomizer.nextInt(100) + 1;

            if (random < hitChance) {
                hero.setHeroDamage(randomizer.nextInt(attackMax - attackMin) + attackMin);
                monster.setMonsHP(monster.getMonsHP() - hero.getHeroDamage());
                menuText.setText(hero.getHeroName() + "'s charged attack dealt " + hero.getHeroDamage() + " to the " + monster.getMonsName());
            } else {
                menuText.setText(hero.getHeroName() + "'s charged attack has failed");
            }
            hero.setHeroMP(hero.getHeroMP() - mpCost);
            hero.setHeroCurrentSpeed(hero.getHeroCurrentSpeed() - speedLine);
        }else {
            menuText.setText("You don't have enough MP");
        }
    }

    public void Stun(Hero hero, Monster monster, TextView menuText, int speedLine) {
        //Stun
        int attackMin = 30;
        int attackMax = 50;
        int hitChance = 50;
        int mpCost = 25;
        if(hero.getHeroMP() >= mpCost ) {
            //Random
            Random randomizer = new Random();
            int random = randomizer.nextInt(100) + 1;

            if (random < hitChance) {
                hero.setHeroDamage(randomizer.nextInt(attackMax - attackMin) + attackMin);
                monster.setMonsHP(monster.getMonsHP() - hero.getHeroDamage());
                monster.setMonsStunned(2);
                menuText.setText(hero.getHeroName() + "'s stun dealt " + hero.getHeroDamage() + " to the " + monster.getMonsName());
            } else {
                menuText.setText(hero.getHeroName() + "'s stun has failed");
            }
            hero.setHeroMP(hero.getHeroMP() - mpCost);
            hero.setHeroCurrentSpeed(hero.getHeroCurrentSpeed() - speedLine);
        }else {
            menuText.setText("You don't have enough MP");
        }
    }

    ////////////////Monster Skill Data////////////////

    public void BasicAttack(Monster monster, Hero hero, TextView menuText, int speedLine) {
        //Basic Attack
        int attackMin = 75;
        int attackMax = 100;
        int hitChance = 100;
        int mpAdd = 25;
        //Random
        Random randomizer = new Random();
        int random = randomizer.nextInt(100) + 1;

        //checks for the attack chance
        if (random < hitChance) {
            monster.setMonsDamage(randomizer.nextInt(attackMax - attackMin) + attackMin);
            hero.setHeroHP(hero.getHeroHP() - monster.getMonsDamage());
            menuText.setText(monster.getMonsName() + "'s basic attack dealt " + monster.getMonsDamage() + " to the " + hero.getHeroName());
        } else {
            menuText.setText(monster.getMonsName() + "'s basic attack has failed");
        }
        //checks if MP is max
        if (monster.getMonsMP() != monster.getMonsMaxMP() && monster.getMonsMP() < monster.getMonsMaxMP()) {
            monster.setMonsMP(monster.getMonsMP() + mpAdd);
        }
        monster.setMonsCurrentSpeed(monster.getMonsCurrentSpeed() - speedLine);
    }

    public void FullSlash(Monster monster, Hero hero, TextView menuText, int speedLine) {
        //Full Slash
        int attackMin = 200;
        int attackMax = 300;
        int hitChance = 50;
        int mpCost = 50;
        //Random
        Random randomizer = new Random();
        int random = randomizer.nextInt(100) + 1;

        if (random < hitChance) {
            monster.setMonsDamage(randomizer.nextInt(attackMax - attackMin) + attackMin);
            hero.setHeroHP(hero.getHeroHP() - monster.getMonsDamage());
            menuText.setText(monster.getMonsName() + "'s full slash dealt " + monster.getMonsDamage() + " to the " + hero.getHeroName());
        } else {
            menuText.setText(monster.getMonsName() + "'s full slash has failed");
        }
        monster.setMonsMP(monster.getMonsMP() - mpCost);
        monster.setMonsCurrentSpeed(monster.getMonsCurrentSpeed() - speedLine);
    }

    public void ChargedAttack(Monster monster, Hero hero, TextView menuText, int speedLine) {
        //Charged Attack
        int attackMin = 150;
        int attackMax = 200;
        int hitChance = 100;
        int mpCost = 25;
        //Random
        Random randomizer = new Random();
        int random = randomizer.nextInt(100) + 1;

        if (random < hitChance) {
            monster.setMonsDamage(randomizer.nextInt(attackMax - attackMin) + attackMin);
            hero.setHeroHP(hero.getHeroHP() - monster.getMonsDamage());
            menuText.setText(monster.getMonsName() + "'s charged attack dealt " + monster.getMonsDamage() + " to the " + hero.getHeroName());
        } else {
            menuText.setText(monster.getMonsName() + "'s charged attack has failed");
        }
        monster.setMonsMP(monster.getMonsMP() - mpCost);
        monster.setMonsCurrentSpeed(monster.getMonsCurrentSpeed() - speedLine);
    }

    public void Stun(Monster monster, Hero hero, TextView menuText, int speedLine) {
        //Stun
        int attackMin = 30;
        int attackMax = 50;
        int hitChance = 50;
        int mpCost = 25;
        //Random
        Random randomizer = new Random();
        int random = randomizer.nextInt(100) + 1;

        if (random < hitChance) {
            monster.setMonsDamage(randomizer.nextInt(attackMax - attackMin) + attackMin);
            hero.setHeroHP(hero.getHeroHP() - monster.getMonsDamage());
            menuText.setText(monster.getMonsName() + "'s stun dealt " + monster.getMonsDamage() + " to the " + hero.getHeroName());
            hero.setHeroStunned(2);
        } else {
            menuText.setText(hero.getHeroName() + "'s stun has failed");
        }
        monster.setMonsMP(monster.getMonsMP() - mpCost);
        monster.setMonsCurrentSpeed(monster.getMonsCurrentSpeed() - speedLine);
    }

}
