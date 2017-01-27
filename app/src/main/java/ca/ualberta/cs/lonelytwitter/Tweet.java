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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Tweet(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public Tweet(String message) {
        this.message = message;
        this.date = new Date();
    }

    public abstract Boolean isImportant();


    ArrayList<Mood> arraylist = new ArrayList<Mood>();

    public void addMood(Mood mood){
        this.arraylist.add(mood);
    }
    @Override
    public String toString() {
        return date.toString() + " | " + message;
    }

}
