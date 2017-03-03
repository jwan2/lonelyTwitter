package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EditTweetActivity extends Activity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);

        Intent intent = getIntent();
        String message = intent.getStringExtra("msg");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);
    }

    public String getText(){
        return (String) textView.getText();
    }

}
