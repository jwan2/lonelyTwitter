package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by Jia on 2017/2/16.
 */

public class TweetList {
    private ArrayList<NormalTweet> tweets = new ArrayList<NormalTweet>();

    public void add(NormalTweet tweet){
        if (tweets.contains(tweet)){
            throw new IllegalArgumentException() ;
        };
        tweets.add(tweet);


    }

    public boolean hasTweet(Tweet tweet){
        return tweets.contains(tweet);
    }

    public NormalTweet getTweet(int index){
//        return new NormalTweet("not the tweet");
        return tweets.get(0);
    }
    public ArrayList<NormalTweet> getTweets(){
        return tweets;
    }

    public void delete(NormalTweet tweet){
        tweets.remove(tweet);
    }
    public int getCount(){
        return tweets.size();
    }

}
