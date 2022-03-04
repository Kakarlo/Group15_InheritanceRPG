package com.example.group15_inheritancerpg.Model;

import android.content.SharedPreferences;

import com.example.group15_inheritancerpg.Controller.DialogueController;


public class Dialogue {
    private final DialogueController dc;
    private final SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public Dialogue(DialogueController controller, SharedPreferences sharedPreferences) {
        this.dc = controller;
        this.sp = sharedPreferences;
    }

    private String[] txt;
    private String[] firstTxt;
    private String choice;
    private String ending;
    //Where the image data is in the String []
    private int imageNum;
    //Delay of text
    private int delay;
    private final int delayMult = 20; //25 is a good speed
    // Last item of the String []
    private int lastItem;
    //length of the String []
    private int textLength;
    //Choice thingy
    private final int defTextLength = 12;
    private final int choice4 = defTextLength;
    private final int choice3 = choice4 - 2;
    private final int choice2 = choice3 - 2;
    private final int choice1 = choice2 - 2;
    //Indicates which button is pressed
    private int buttonState;
    //Indicates which ending was activated
    private boolean witchEnd,knightEnd,dragonEnd,priestEnd;
    //Number of choices a Scenario will offer
    private int numOfChoice;
    //Number of clicks to skip text animation
    private int textSkip;
    //Which item was picked //TODO: could be made better for a more dynamic approach
    public int getItemState() {return sp.getInt("ItemState", 0);} //Which item was picked
    private int item;
    boolean failed,restricted;
    //Text Delay
    boolean allowDelay = true;

    public void sceneCheck(int state) {
        buttonState = state;
        if (buttonState == 0) {
            //Runs if no choices has been made
            arrayCheck();
            textChange();
        } else if (restricted) {
            restricted = false;
            restricted();
        } else {
            arrayCheck();
            lastItem = txt.length - 1;
            String check = txt[lastItem];
            switch (check) {
                case "Dialog":
                    textChange();
                    break;
                case "Restricted":
                    restricted = true;
                    textLength -=1; //Done as the Restricted Array has 1 more item than the rest
                    textChange();
                    break;
                case "Item":
                    item = 2;
                    textChange();
                    break;
                case "Ending":
                    ending = choice;
                    textChange();
                    ending();
                    break;
            }
        }
    }

    public void arrayCheck() {
        //checks if the scenario is now restricted
        if (buttonState != 0 && !failed) {
            choice = txt[numOfChoice + buttonState];
            dc.getStringArr();
            textLength = txt.length;
        } else if (failed) {
            choice = txt[lastItem - 1];
            dc.getStringArr();
            textLength = txt.length;
            failed = false;
        } else {
            textLength = txt.length;
        }
    }

    public void textChange() {
        //Checks if delay is to be applied
        if (allowDelay) {
            delay = txt[0].length() * delayMult;
        }
        //Checks how many choices in that particular scene are
        if (textLength == choice4){ //for 4 choices scenario
            numOfChoice = 4;
        } else if (textLength == choice3) { //for 3 choice scenario
            numOfChoice = 3;
        } else if (textLength == choice2) { // for 2 choice scenario
            numOfChoice = 2;
        } else if (textLength == choice1) { // for 1 choice scenario
            numOfChoice = 1;
        }
        //Checks the item state
        if (item > 1) {
            item--;
        } else if (item == 1) {
            editor = sp.edit();
            editor.putInt("ItemState" ,buttonState);
            editor.apply();
            item--;
        }
    }

    public void restricted() {
        //Checks if the first choice was pressed
        if (buttonState == 1) {
            //Checks if the user got an item
            if (getItemState() > 0) {
                //Checks if the correct item was retrieved
                if (getItemState() == 2) {
                    failed = false;
                } else {
                    dc.popOutText(1);
                    failed = true;
                }
            } else {
                dc.popOutText(2);
                failed = true;
            }
            arrayCheck();
            textChange();
        } else if (buttonState == 2) {
            textChange();
        }
    }

    public void ending() {
        //Checks which ending was activated
        switch (ending) {
            case "b2a2b2":
                editor = sp.edit();
                editor.putBoolean("WitchEnd" ,true);
                editor.apply();
                break;
            case "b2b2a2":
                editor = sp.edit();
                editor.putBoolean("KnightEnd" ,true);
                editor.apply();
                break;
            case "c2c2b2":
                editor = sp.edit();
                editor.putBoolean("DragonEnd" ,true);
                editor.apply();
                break;
            case "c2d2a2":
                editor = sp.edit();
                editor.putBoolean("PriestEnd" ,true);
                editor.apply();
                break;
        }
    }

    public int imageChange() {
        //Changes image
        return Integer.parseInt(txt[imageNum].replaceAll("[\\D]", ""));
    }

    public void Save() {
        if (choice != null) {
            editor = sp.edit();
            editor.putString("textID", choice);
            editor.apply();
        }
    }

    public void Reset() {
        editor = sp.edit();
        editor.putString("textID", "null");
        editor.putInt("ItemState" ,0);
        editor.putBoolean("WitchEnd" ,false);
        editor.putBoolean("KnightEnd" ,false);
        editor.putBoolean("DragonEnd" ,false);
        editor.putBoolean("PriestEnd" ,false);
        editor.apply();
    }

    //Getters
    public String[] getFirstTxt() {return firstTxt;}
    public String getTxtString(int index) {return txt[index];}
    public String getChoice() {return choice;}
    public int getDelay() {return delay;}
    public int getNumOfChoice() {return numOfChoice;}
    public int getTextSkip() {return textSkip;}

    //Setters
    public void setFirstTxt(String[] firstTxt) {this.firstTxt = firstTxt;}
    public void setTxt(String[] text) {this.txt = text;}
    public void setImageNum(int imageNum) {this.imageNum = imageNum;}
    public void setTextSkip(int textSkip) {this.textSkip = textSkip;}





}
