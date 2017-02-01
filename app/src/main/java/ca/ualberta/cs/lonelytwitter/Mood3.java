package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by Jia on 2017-01-17.
 */
public class Mood3 extends Mood {
    /**
     * Set mood 3.
     */
    public void setMood3(){
        super.setCurrentMood("Sad");
    }

    /**
     * Instantiates a new Mood 3.
     *
     * @param currentMood the current mood
     */
    public Mood3(String currentMood) {
        super(currentMood);
    }

    /**
     * Instantiates a new Mood 3.
     *
     * @param date        the date
     * @param currentMood the current mood
     */
    public Mood3(Date date, String currentMood) {
        super(date, currentMood);
    }
}

