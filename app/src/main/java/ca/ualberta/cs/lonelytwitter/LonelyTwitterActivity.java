package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * This class is the main view class of the project. <br> In this class, user interaction and file manipulation is performed.
 * All files are in the form of 'json' files that are stored in Emulator's accessible from Android Device Monitor
 * <pre>
 *     pre.formatted testL <br>
 *         File Explorer -> data -> -> ca.ualberta.cs.lonelytwitter -> files -> file.sav
 * </pre>
 * <code> begin <br>
 * some pseudo code <br>
 *     end.
 * </code>
 * The file name is indicated in the &nbsp FILENAME constant
 * <ul>
 *     <li>item 1</li>
 *     <li>item 2</li>
 *     <li>item 3</li>
 *
 * </ul>
 *
 *<ol>
 *     <li>item 1</li>
 *     <li>item 2</li>
 *     <li>item 3</li>
 *</ol>
 *
 * @author ysu3
 * @version 1.0
 * @see Tweet
 * @since 0.5
 */

public class LonelyTwitterActivity extends Activity {
	/**
	 * The file that all the tweets are saved here. The format of the file is JSON
	 * @see #loadFromFile()
	 * @see #saveInFile()
	 */
	private  LonelyTwitterActivity activity = this;

	private static final String FILENAME = "file.sav";
	private  enum TweetListOrdering {DATE_ASCENDING, DATE_DESCENDING, TEXT_DESCENDING};
	private EditText bodyText;
	private ListView oldTweetsList;


	private ArrayList<Tweet> tweetList;
	private ArrayAdapter<Tweet> adapter;


	public ListView getOldTweetsList(){
		return oldTweetsList;
	}




	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);

		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);


//		Tweet tweet = null;
//		try {
//			tweet = new NormalTweet("a");
//			Tweet tweet2 = new NormalTweet(new Date(),"b");
//			Tweet iptTweet = new ImportantTweet("important");
//			iptTweet.getDate();
//
//
//			NormalTweet nt = new NormalTweet("a message");
//			ArrayList<Tweet> arrayList = new ArrayList<Tweet>();
//			arrayList.add(tweet);
//			arrayList.add(tweet2);
//			arrayList.add(iptTweet);
//		} catch (TweetTooLongException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			tweet.setMessage("message for first tweet");
//		} catch (TweetTooLongException e) {
//			e.printStackTrace();
//		}


		clearButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				tweetList.clear();
				deleteFile(FILENAME);
				adapter.notifyDataSetChanged();
			}
		});



		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				text = trimExtraSpaces(text);


				Tweet tweet = new NormalTweet(text);

				tweetList.add(tweet);
				adapter.notifyDataSetChanged();

				//saveInFile(text, new Date(System.currentTimeMillis()));
				saveInFile();
				//finish();
			}
		});


		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				Tweet tweet = (Tweet) adapter.getItemAtPosition(i);
				String message = tweet.getMessage();
				intent.putExtra("msg", message);

				startActivity(intent);
			}
		});




	}

	/**
	 * This method loads content in a specific file and store them in an array. Then setup an adapter for that array.
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//String[] tweets = loadFromFile();

		loadFromFile();

		//tweetList = new ArrayList<Tweet>();
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}


	/**
	 * Trim extra spaces using regular expression
	 * @param inputString string needs to be cleared of extra spaces
	 * @return resulting string
	 */
	private String trimExtraSpaces(String inputString){
		inputString = inputString.replaceAll("//s+", " ");
		return inputString;
	};


	/**
	 * This method sorts item in the tweet list and refreshes the adapter
	 * @param ordering
	 */
	private void sortTweetListItems(TweetListOrdering ordering){

	}



	/**
	 * Loads tweets from specified file.
	 *
	 * @throws TweetTooLongException if text is too long
	 * @exception FileNotFoundException if the file is not created.
	 */
	private void loadFromFile() {
		//ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));



			Gson gson = new Gson();
			//new TypeToken<ArrayList<Tweet>>(){}.getType();
			// get from stack overflow
			Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();
			tweetList = gson.fromJson(in,listType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweetList = new ArrayList<Tweet>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}


	/**
	 * Saves tweets to a file in JSON format
	 * @throws FileNotFoundException
	 */
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);

			BufferedWriter out = new BufferedWriter((new OutputStreamWriter(fos)));
			//fos.write(new String(date.toString() + " | " + text)
			//		.getBytes());

			Gson gson = new Gson();
			gson.toJson(tweetList, out);
			out.flush();


			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Handle the Exception properly later
			tweetList = new ArrayList<Tweet>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}