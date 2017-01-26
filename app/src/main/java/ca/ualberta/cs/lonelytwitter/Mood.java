package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by Jia on 2017/1/25.
 */

public abstract class Mood {
    private Date date;
    private String currentMood;


    public Mood(String currentMood) {
        this.currentMood = currentMood;
        this.date = new Date();
    }

    public Mood(Date date, String currentMood) {
        this.date = date;
        this.currentMood = currentMood;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCurrentMood(String currentMood) {
        this.currentMood = currentMood;
    }
}
