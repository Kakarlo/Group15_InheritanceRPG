package com.example.group15_inheritancerpg.Model.DataLogic;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.group15_inheritancerpg.R;

import java.util.Random;

public class MusicPlayer {
    private final Context context;

    public MusicPlayer (Context context) {
        this.context = context;
    }

    private MediaPlayer music;
    private int musicID;

    public void startMusic () {
        Random random = new Random();
        byte index = (byte) random.nextInt(3);
        switch (index) {
            case 0:
                musicID = R.raw.timber_town;
                break;
            case 1:
                musicID = R.raw.night_elf_village;
                break;
            case 2:
                musicID = R.raw.fairy_dust_town;
                break;
            case 3:
                musicID = 0;
                break;
            case 4:
                musicID = 0;
                break;
            case 5:
                musicID = 0;
                break;
            case 6:
                musicID = 0;
                break;
            case 7:
                musicID = 0;
                break;
        }
        if (musicID != 0) {
            music = MediaPlayer.create(context, musicID);
            music.setOnPreparedListener(mp -> {
                music.setVolume(0.5f, 0.5f);
                music.start();
            });

            //Plays another song
            music.setOnCompletionListener(mediaPlayer -> {
                stopMusic();
                startMusic();
            });
        }
    }

    public void stopMusic () {
        if (music != null) {
            music.release();
            music = null;
        }
    }

}
