package ca.ualberta.cs.lonelytwitter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by WAN on 2017-01-17.
 */

public abstract class  Tweet implements Tweetable{
    private Date date;
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() > 144) {
            // todo throw new error here
            throw new TweetTooLongException();

        } else {
            this.message = message;
        }

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
     * Instantiates a new Tweet.
     *
     * @param message the message
     * @param date    the date
     */
    public Tweet(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    /**
     * Instantiates a new Tweet.
     *
     * @param message the message
     */
    public Tweet(String message) {
        this.message = message;
        this.date = new Date();
    }

    /**
     * Is important boolean.
     *
     * @return the boolean
     */
    public abstract Boolean isImportant();


    /**
     * The Arraylist.
     */
    ArrayList<Mood> arraylist = new ArrayList<Mood>();

    /**
     * Add mood.
     *
     * @param mood the mood
     */
    public void addMood(Mood mood){
        this.arraylist.add(mood);
    }
    @Override
    public String toString() {
        return date.toString() + " | " + message;
    }

}
