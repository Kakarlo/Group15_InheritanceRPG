package com.example.group15_inheritancerpg.Controller;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.text.HtmlCompat;

import com.example.group15_inheritancerpg.Model.DataLogic.Dialogue;
import com.example.group15_inheritancerpg.Model.DataLogic.MusicPlayer;
import com.example.group15_inheritancerpg.R;
import com.example.group15_inheritancerpg.View.DialogueView;


public class DialogueController {
    private final Dialogue d;
    private final DialogueView dv;
    private CountDownTimer timer;
    private final MusicPlayer m;
    private final SharedPreferences sp;

    public DialogueController(DialogueView dialogueView) {
        this.dv = dialogueView;
        this.sp = dv.getPreferences(Context.MODE_PRIVATE);
        this.d = new Dialogue(this, sp);
        this.m = new MusicPlayer(dialogueView);
        m.startMusic();
    }

    public void firstScene(String[] stringArr) {
        //Checks if you have gone further
        d.setFirstTxt(stringArr);
        loadTxt();
    }

    public void loadTxt() {
        try {
            int i = dv.getResources().getIdentifier(sp.getString("textID", "null"), "array", dv.getPackageName());
            d.setTxt(dv.getResources().getStringArray(i));
        } catch (Exception e) {
            d.setTxt(d.getFirstTxt());
        }
    }

    public void getStringArr() {
        int arrID = dv.getResources().getIdentifier(d.getChoice(), "array", dv.getPackageName());
        try {
            d.setTxt(dv.getResources().getStringArray(arrID));
        } catch (Exception e) {
            d.setTxt(dv.getResources().getStringArray(R.array.errorMessage));
        }
    }

    public void changeScene(int state) {
        //Clears animation
        hideButton();
        clearAnim();
        d.sceneCheck(state);
        textChange();
        imageChange();
        popOut();
    }

    public void save() {d.Save();}

    public void stop() {
        m.stopMusic();
    }

    public void reset() {
        d.Reset();
        loadTxt();
    }

    private void textChange() {
        if (d.getNumOfChoice() == 4){ //for 4 choices scenario
            choices4();
        } else if (d.getNumOfChoice() == 3) { //for 3 choice scenario
            choices3();
        } else if (d.getNumOfChoice() == 2) { // for 2 choice scenario
            choices2();
        } else if (d.getNumOfChoice() == 1) { // for 1 choice scenario
            choices1();
        }
    }

    private void choices4() {
        //Changes button visibility
        dv.button.setVisibility(View.VISIBLE);
        dv.button2.setVisibility(View.VISIBLE);
        dv.button3.setVisibility(View.VISIBLE);
        dv.button4.setVisibility(View.VISIBLE);
        //Sets the text
        dv.textBox.setText(HtmlCompat.fromHtml(d.getTxtString(0),HtmlCompat.FROM_HTML_MODE_COMPACT));
        dv.button.setText(d.getTxtString(1));
        dv.button2.setText(d.getTxtString(2));
        dv.button3.setText(d.getTxtString(3));
        dv.button4.setText(d.getTxtString(4));
        //Animation delay
        dv.fadelate.setStartOffset(d.getDelay()+300);
        dv.fadelate2.setStartOffset(d.getDelay()+600);
        dv.fadelate3.setStartOffset(d.getDelay()+900);
        dv.fadelate4.setStartOffset(d.getDelay()+1200);
        //Starts Animation
        dv.textBox.startAnimation(dv.fade);
        dv.button.startAnimation(dv.fadelate);
        dv.button2.startAnimation(dv.fadelate2);
        dv.button3.startAnimation(dv.fadelate3);
        dv.button4.startAnimation(dv.fadelate4);
        //Sets the imageNumber
        d.setImageNum(9);
    }

    private void choices3() {
        //Changes button visibility
        dv.button.setVisibility(View.VISIBLE);
        dv.button2.setVisibility(View.VISIBLE);
        dv.button3.setVisibility(View.VISIBLE);
        //Sets the text
        dv.textBox.setText(HtmlCompat.fromHtml(d.getTxtString(0),HtmlCompat.FROM_HTML_MODE_COMPACT));
        dv.button.setText(d.getTxtString(1));
        dv.button2.setText(d.getTxtString(2));
        dv.button3.setText(d.getTxtString(3));
        //Animation delay
        dv.fadelate.setStartOffset(d.getDelay()+300);
        dv.fadelate2.setStartOffset(d.getDelay()+600);
        dv.fadelate3.setStartOffset(d.getDelay()+900);
        //Starts Animation
        dv.textBox.startAnimation(dv.fade);
        dv.button.startAnimation(dv.fadelate);
        dv.button2.startAnimation(dv.fadelate2);
        dv.button3.startAnimation(dv.fadelate3);
        //Sets the imageNumber
        d.setImageNum(7);
    }

    private void choices2() {
        //Changes button visibility
        dv.button.setVisibility(View.VISIBLE);
        dv.button2.setVisibility(View.VISIBLE);
        //Sets the text and starts the animation
        dv.textBox.setText(HtmlCompat.fromHtml(d.getTxtString(0),HtmlCompat.FROM_HTML_MODE_COMPACT));
        dv.button.setText(d.getTxtString(1));
        dv.button2.setText(d.getTxtString(2));
        //Animation delay
        dv.fadelate.setStartOffset(d.getDelay()+300);
        dv.fadelate2.setStartOffset(d.getDelay()+600);
        //Starts Animation
        dv.textBox.startAnimation(dv.fade);
        dv.button.startAnimation(dv.fadelate);
        dv.button2.startAnimation(dv.fadelate2);
        //Sets the imageNumber
        d.setImageNum(5);
    }

    private void choices1() {
        //Changes button visibility
        dv.button.setVisibility(View.VISIBLE);
        //Sets the text and starts the animation
        dv.textBox.setText(HtmlCompat.fromHtml(d.getTxtString(0),HtmlCompat.FROM_HTML_MODE_COMPACT));
        dv.button.setText(d.getTxtString(1));
        //Animation delay
        dv.fadelate.setStartOffset(d.getDelay()+300);
        //Starts Animation
        dv.textBox.startAnimation(dv.fade);
        dv.button.startAnimation(dv.fadelate);
        //Sets the imageNumber
        d.setImageNum(3);
    }

    private void hideButton() {
        //Changes button visibility
        dv.button.setVisibility(View.INVISIBLE);
        dv.button2.setVisibility(View.INVISIBLE);
        dv.button3.setVisibility(View.INVISIBLE);
        dv.button4.setVisibility(View.INVISIBLE);
    }

    private void clearAnim() {
        dv.textBox.clearAnimation();
        dv.button.clearAnimation();
        dv.button2.clearAnimation();
        dv.button3.clearAnimation();
        dv.button4.clearAnimation();
        dv.popOutLayout.clearAnimation();
    }

    private void imageChange() {
        try {
            int imgID = dv.getResources().getIdentifier("umaru" + d.imageChange(), "drawable", dv.getPackageName());
            Drawable image = ResourcesCompat.getDrawable(dv.getResources(), imgID, null);
            dv.imageView.setImageDrawable(image);
        } catch (Exception e){
            Log.d(TAG, "imageChange: Oops! no image");
        }
    }

    public void popOutText(int text) {
        switch (text) {
            case 1:
                dv.popOut.setText(R.string.popOut1);
                dv.popOut.setVisibility(View.VISIBLE);
                break;
            case 2:
                dv.popOut.setText(R.string.popOut2);
                dv.popOut.setVisibility(View.VISIBLE);
                break;
        }
        dv.popOutLayout.setVisibility(View.VISIBLE);
        dv.popOutLayout.startAnimation(dv.fadeInOut);
        dv.popOutLayout.setClickable(true);
        dv.fadeInOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                dv.popOut.setVisibility(View.GONE);
                dv.popOutLayout.setVisibility(View.GONE);
                dv.popOutLayout.setClickable(false);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void popOut() {
        dv.popOutLayout.setVisibility(View.VISIBLE);
        dv.popOutLayout.setClickable(true);
        timer = new CountDownTimer(d.getDelay() + 1500, d.getDelay() + 1500) {
            public void onTick(long l) { }
            public void onFinish() {
                dv.popOutLayout.setVisibility(View.GONE);
                dv.popOutLayout.setClickable(false);
                timer.cancel();
            }
        };
        timer.start();
        dv.popOutLayout.setOnClickListener(view -> {
            d.setTextSkip(d.getTextSkip()+1);
            if (d.getTextSkip() >= 2){
                dv.popOutLayout.setVisibility(View.GONE);
                dv.popOutLayout.setClickable(false);
                clearAnim();
                d.setTextSkip(0);
                timer.cancel();
            }
        });
    }

}
