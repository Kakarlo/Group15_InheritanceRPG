package com.example.group15_inheritancerpg.Controller;

import android.graphics.drawable.Drawable;
import androidx.core.content.res.ResourcesCompat;

import com.example.group15_inheritancerpg.Model.Dialogue;
import com.example.group15_inheritancerpg.R;


public class DecisionMaking {

    //Default constructor
    public DecisionMaking () {}
    public DecisionMaking (Dialogue dialogue) {this.d = dialogue;}
    Dialogue d;
    private boolean failed,restricted;

    //////////////////////test///////////////////////

    //So when i pick a choice, the button will then get the corresponding string name array to set the dialog for the next scenario?

    private void arrayCheck() {
        //checks if the scenario is now restricted
        if (d.getButtonState() != 0 && !failed) {
            d.setChoice(d.getMtextString(d.getNumOfChoice() + d.getButtonState()));
            d.setArrID(d.getContext().getResources().getIdentifier(d.getChoice(), "array", d.getContext().getPackageName()));
            try {
                d.setMtext(d.getArrID());
            } catch (Exception e) {
                d.setMtext(R.array.errorMessage);
            }
            d.setTextLength(d.getMtext().length);
        } else if (failed) {
            d.setChoice(d.getMtext()[d.getLastItem() - 1]);
            d.setArrID(d.getContext().getResources().getIdentifier(d.getChoice(), "array", d.getContext().getPackageName()));
            try {
                d.setMtext(d.getArrID());
            } catch (Exception e) {
                d.setMtext(R.array.errorMessage);
            }
            d.setTextLength(d.getMtext().length);
            failed = false;
        } else {
            d.setTextLength(d.getMtext().length);
        }
    }

    public void sceneCheck() {
        if (d.getButtonState() == 0) {
            //Runs if no choices has been made
            arrayCheck();
            textChange();
        } else if (restricted) {
            restricted = false;
            restricted();
        } else {
            arrayCheck();
            d.setLastItem(d.getMtext().length - 1);
            d.setCheck(d.getMtextString(d.getLastItem()));
            switch (d.getCheck()) {
                case "Dialog":
                    textChange();
                    break;
                case "Restricted":
                    restricted = true;
                    d.setTextLength(d.getTextLength() - 1);//Done as the Restricted Array has 1 more item than the rest
                    textChange();
                    break;
                case "Item":
                    d.setItemCounter(2);
                    textChange();
                    break;
                case "Ending":
                    d.setEnding(d.getChoice());
                    textChange();
                    ending();
                    break;
            }
        }
    }

    //TODO: make a better system to check restricted scenes
    private void restricted() {
        //Checks if the first choice was pressed
        if (d.getButtonState() == 1) {
            //Checks if the user got an item
            if (d.getItemState() > 0) {
                //Checks if the correct item was retrieved
                if (d.getItemState() == 2) {
                    failed = false;
                } else {
                    d.setPopOutText(1);
                    failed = true;
                }
            } else {
                d.setPopOutText(2);
                failed = true;
            }
            arrayCheck();
            textChange();
        } else if (d.getButtonState() == 2) {
            textChange();
        }
    }

    //TODO: make a better system to check ending scenes
    private void ending() {
        //Checks which ending was activated
        switch (d.getEnding()) {
            case "b2a2b2":
                //Indicates which ending was activated
                boolean witchEnd = true;
                break;
            case "b2b2a2":
                boolean knightEnd = true;
                break;
            case "c2c2b2":
                boolean dragonEnd = true;
                break;
            case "c2d2a2":
                boolean priestEnd = true;
                break;
        }
    }

    private void textChange() {
        //Checks if delay is to be applied
        if (d.getAllowDelay()) {
            d.setDelay(d.getMtextString(0).length() * d.getDelayMult());
        }
        //Checks how many choices in that particular scene are
        if (d.getTextLength() == d.getChoice4()){ //for 4 choices scenario
            d.setNumOfChoice(4);
        } else if (d.getTextLength() == d.getChoice3()) { //for 3 choice scenario
            d.setNumOfChoice(3);
        } else if (d.getTextLength() == d.getChoice2()) { // for 2 choice scenario
            d.setNumOfChoice(2);
        } else if (d.getTextLength() == d.getChoice1()) { // for 1 choice scenario
            d.setNumOfChoice(1);
        }
        //Checks the item state
        if (d.getItemCounter() > 1) {
            d.setItemCounter(d.getItemCounter()-1);
        } else if (d.getItemCounter() == 1) {
            d.setItemState(d.getButtonState());
            d.setItemCounter(d.getItemCounter()-1);
        }
    }

    public Drawable imgChange() {
        //Changes image
        d.setImageState(Integer.parseInt(d.getMtextString(d.getImageNum()).replaceAll("[\\D]", "")));
        if (d.getImageState() > 0) {
            d.setImgID(d.getContext().getResources().getIdentifier("umaru" + d.getImageState(), "drawable", d.getContext().getPackageName()));
            d.setImage(ResourcesCompat.getDrawable(d.getContext().getResources(), d.getImgID(), null));
        }
        return d.getImage();
    }
}