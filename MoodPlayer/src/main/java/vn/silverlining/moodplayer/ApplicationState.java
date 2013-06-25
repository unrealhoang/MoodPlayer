package vn.silverlining.moodplayer;

import java.util.List;

import vn.silverlining.moodplayer.model.Song;

/**
 * Created by unreal on 6/26/13.
 */
public class ApplicationState {
    private int currentMood;
    private int wantToMood;
    private static ApplicationState instance;

    private ApplicationState() {

    }

    public static ApplicationState getInstance() {
        if (instance == null)
            instance = new ApplicationState();
        return instance;
    }

    public int getWantToMood() {
        return wantToMood;
    }

    public void setWantToMood(int wantToMood) {
        this.wantToMood = wantToMood;
    }

    public int getCurrentMood() {
        return currentMood;
    }

    public void setCurrentMood(int currentMood) {
        this.currentMood = currentMood;
    }
}
