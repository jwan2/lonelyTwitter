package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by Jia on 2017/1/25.
 */
public abstract class Mood {
    private Date date;
    private String currentMood;


    /**
     * Instantiates a new Mood.
     *
     * @param currentMood the current mood
     */
    public Mood(String currentMood) {
        this.currentMood = currentMood;
        this.date = new Date();
    }

    /**
     * Instantiates a new Mood.
     *
     * @param date        the date
     * @param currentMood the current mood
     */
    public Mood(Date date, String currentMood) {
        this.date = date;
        this.currentMood = currentMood;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets current mood.
     *
     * @param currentMood the current mood
     */
    public void setCurrentMood(String currentMood) {
        this.currentMood = currentMood;
    }
}
