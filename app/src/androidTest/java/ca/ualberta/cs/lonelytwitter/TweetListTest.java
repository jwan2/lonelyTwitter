package ca.ualberta.cs.lonelytwitter;

import android.net.UrlQuerySanitizer;
import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by Jia on 2017/2/16.
 */

public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest() {
        super(LonelyTwitterActivity.class);
    }



    public void testAddTweet() {
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");
        tweets.add(tweet);
        assertTrue(tweets.hasTweet(tweet));

        try {
            tweets.add(tweet);
            fail();
        } catch (Exception e){
            assertEquals(e instanceof IllegalArgumentException, true);
        }
    }

    public  void testHasTweet() {
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");
        assertFalse(tweets.hasTweet(tweet));
        tweets.add(tweet);
        assertTrue(tweets.hasTweet(tweet));
    }

    public void testGetTweet(){
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");

        tweets.add(tweet);
        NormalTweet returnedTweet = tweets.getTweet(0);
        assertEquals(returnedTweet.getMessage(), tweet.getMessage());
        assertEquals(returnedTweet.getDate(), tweet.getDate());
    }

    public void testGetTweets(){
        TweetList tweets = new TweetList();
        ArrayList tweetslist = new ArrayList();
        NormalTweet tweet = new NormalTweet("some tweet");
        NormalTweet tweet2 = new NormalTweet("some tweets");
        tweets.add(tweet);
        tweets.add(tweet2);
        tweetslist.add(tweet);
        tweetslist.add(tweet2);
        ArrayList returnedTweets = tweets.getTweets();
        assertEquals(returnedTweets, tweetslist);

    }

    public void testDeleteTweet(){
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");

        tweets.add(tweet);
        tweets.delete(tweet);
        assertFalse(tweets.hasTweet(tweet));
    }
    public void testGetCount(){
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");

        tweets.add(tweet);
        assertEquals(1, tweets.getCount());
        tweets.delete(tweet);
        assertEquals(0, tweets.getCount());
    }

}


