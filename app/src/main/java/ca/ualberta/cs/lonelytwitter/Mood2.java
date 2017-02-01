package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by Jia on 2017-01-17.
 */
public class Mood2 extends Mood {
    /**
     * Set mood 2.
     */
    public void setMood2(){
        super.setCurrentMood("Happy");
    }

    /**
     * Instantiates a new Mood 2.
     *
     * @param currentMood the current mood
     */
    public Mood2(String currentMood) {
        super(currentMood);
    }

    /**
     * Instantiates a new Mood 2.
     *
     * @param date        the date
     * @param currentMood the current mood
     */
    public Mood2(Date date, String currentMood) {
        super(date, currentMood);
    }
}
