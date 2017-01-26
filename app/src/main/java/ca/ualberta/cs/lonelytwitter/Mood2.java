package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by Jia on 2017-01-17.
 */

public class Mood2 extends Mood {
    public void setMood2(){
        super.setCurrentMood("Happy");
    }

    public Mood2(String currentMood) {
        super(currentMood);
    }

    public Mood2(Date date, String currentMood) {
        super(date, currentMood);
    }
}
