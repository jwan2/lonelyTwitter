package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This class is the main view class of the project. <br> In this class, user interaction
 * and file manipulation is performed.
 * All files are in the form of 'json' file that are stored in Emulator's accessary
 * <pre>
 *  	pre.formatted text: <br>
 *  	 	File Explorer -> data -> ca.ualberta.cs.lonelytwitter -> files -> file.sav
 *
 *  </pre>
 * <code> begin <br>
 * some pseudo code <br>
 * end.</code>
 * The file name is indicated in the &nbsp &nbsp &nbsp FILENAME constant.
 * <p>
 * <ul>
 * <li>item 1</li>
 * <li>item 2</li>
 * <li>item 3</li>
 * </ul>
 * <ol>
 * <li>item 1</li>
 * <li>item 2</li>
 * <li>item 3</li>
 * </ol>
 *
 * @author Jia
 * @version 1.0
 * @see Tweet
 * @since 0.5
 */


public class LonelyTwitterActivity extends Activity {

	/**
	 * The file that all the tweets are saved there. The format of the file is JSON.
	 * @see #loadFromFile()
	 * @see #saveInFile()
	 */

	private static final String FILENAME = "file.sav";
    private enum TweetListOrdering {
		/**
		 * Date ascending tweet list ordering.
		 */
		DATE_ASCENDING, /**
		 * Date descending tweet list ordering.
		 */
		DATE_DESCENDING, /**
		 * Text ascending tweet list ordering.
		 */
		TEXT_ASCENDING, /**
		 * Text descending tweet list ordering.
		 */
		TEXT_DESCENDING};
	private EditText bodyText;
	private ListView oldTweetsList;

	private ArrayList <Tweet> tweetList;
	private ArrayAdapter<Tweet> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();

                text = trimExtraSpaces(text);

				Tweet tweet = new NormalTweet(text);

				tweetList.add(tweet);

				adapter.notifyDataSetChanged();

				saveInFile();

//				saveInFile(text, new Date(System.currentTimeMillis()));
//				finish();

			}
		});

        Button clearButton = (Button) findViewById(R.id.clear);

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                tweetList.clear();
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });


	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
        loadFromFile();
//		String[] tweets = loadFromFile();
//		 tweetList = new ArrayList<Tweet>();
		 adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);
		 oldTweetsList.setAdapter(adapter);
	}

    /**
     * Trims extra spaces using regular expression.
     * @param inputString string that needs to be cleared of extra spaces
     * @return resulting string
     *
     */
    private String trimExtraSpaces(String inputString){
        inputString = inputString.replaceAll("\\s+", " ");
        return inputString;
    }

    /**
     * This method sorts items the in tweet list and refreshes the adapter.
     * @param ordering ordering to be used
     */
    private void sortTweetListItems(TweetListOrdering ordering) {

    }

	/**
	 * Loads tweets form specified file.
	 *
	 * @throws TweetTooLongException if the text is too long.
	 * @exception FileNotFoundException if the file is not created first.
	 */
	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

			Gson gson = new Gson();

			// Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
			// 2017-01-26 17:53:59
			tweetList = gson.fromJson(in, new TypeToken<ArrayList<NormalTweet>>() {
			}.getType());

			fis.close();
		} catch (FileNotFoundException e) {
			tweetList = new ArrayList<Tweet>();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

    /**
     * Saves tweets to a specified file in JSON format.
     * @throws  FileNotFoundException if file folder doesn't exist
     */
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

			Gson gson = new Gson();

			gson.toJson(tweetList, out);

			out.flush();


			fos.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}