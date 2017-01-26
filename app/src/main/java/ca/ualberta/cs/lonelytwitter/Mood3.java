package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by Jia on 2017-01-17.
 */

public class Mood3 extends Mood {
    public void setMood3(){
        super.setCurrentMood("Sad");
    }
    public Mood3(String currentMood) {
        super(currentMood);
    }
    public Mood3(Date date, String currentMood) {
        super(date, currentMood);
    }
}

