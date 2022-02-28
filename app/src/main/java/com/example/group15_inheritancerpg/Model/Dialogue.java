package com.example.group15_inheritancerpg.Model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.group15_inheritancerpg.R;

public class Dialogue {

    //Default constructor
    public Dialogue(Context context, int firstScene) {
        this.context = context;
        defText= getContext().getResources().getStringArray(R.array.default_text);
        mtext = context.getResources().getStringArray(firstScene);
    }

    private Drawable image;
    private String[] mtext;
    private final String[] defText;
    private String choice;
    private String check;
    private String ending;
    private Context context;

    private int arrID;      //Array ID (integer)
    private int imgID;      //Image ID (integer)
    private int imageState; //Which image to play
    private int imageNum;   //Where the image data is in the String []
    private int delay;      //Delay of text
    private int lastItem;   // Last item of the String []
    private int textLength; //length of the String []

    private int buttonState; //Indicates which button is pressed
    private boolean witchEnd,knightEnd,dragonEnd,priestEnd; //Indicates which ending was activated
    private int numOfChoice; //Number of choices a Scenario will offer
    private int itemState;   //Which item was picked
    private int itemCounter;

    private boolean popOut;
    private int popOutText; //Which text to pop out

    //Getters
    public Drawable getImage() {return image;}
    public String[] getMtext() {return mtext;}
    public String getMtextString(int index) {return mtext[index]; }
    public String getChoice() {return this.choice;}
    public String getCheck() {return this.check;}
    public String getEnding() {return this.ending;}
    public Context getContext() {return this.context;}
    public int getArrID() {return this.arrID;}
    public int getImgID() {return this.imgID;}
    public int getImageState() {return this.imageState;}
    public int getImageNum() {return this.imageNum;}
    public int getDelay() {return this.delay;}
    public int getDelayMult() {return 20;} //25 is a good speed
    public int getLastItem() {return this.lastItem;}
    public int getChoice4() {return this.defText.length;}
    public int getChoice3() {return getChoice4() - 2;}
    public int getChoice2() {return getChoice3() - 2;}
    public int getChoice1() {return getChoice2() - 2;}
    public int getTextLength() {return this.textLength;}
    public int getButtonState() {return this.buttonState;}
    public int getNumOfChoice() {return this.numOfChoice;}
    public int getItemState() {return this.itemState;}
    public int getItemCounter() {return this.itemCounter;}
    public int getPopOutText() {return this.popOutText;}
    public boolean getPopOut() {return this.popOut;}
    public boolean getAllowDelay() {return true;}//for the dialogue delay

    //Setters
    public void setImage(Drawable image) {this.image = image;}
    public void setMtext(String[] mtext) {this.mtext = mtext;}
    public void setMtext(int id) {this.mtext = this.context.getResources().getStringArray(id);}
    public void setChoice(String choice) {this.choice = choice;}
    public void setCheck(String check) {this.check = check;}
    public void setEnding(String ending) {this.ending = ending;}
    public void setContext(Context context) {this.context = context;}
    public void setArrID(int arrID) {this.arrID = arrID;}
    public void setImgID(int imgID) {this.imgID = imgID;}
    public void setImageState(int imageState) {this.imageState = imageState;}
    public void setImageNum(int imageNum) {this.imageNum = imageNum;}
    public void setDelay(int delay) {this.delay = delay;}
    public void setLastItem(int lastItem) {this.lastItem = lastItem;}
    public void setTextLength(int textLength) {this.textLength = textLength;}
    public void setButtonState(int buttonState) {this.buttonState = buttonState;}
    public void setNumOfChoice(int numOfChoice) {this.numOfChoice = numOfChoice;}
    public void setItemState(int itemState) {this.itemState = itemState;}
    public void setItemCounter(int itemCounter) {this.itemCounter = itemCounter;}
    public void setPopOutText(int number) {this.popOutText = number;}
    public void setPopOut(boolean popOut) {this.popOut = popOut;}
}
