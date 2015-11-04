package com.project.simplereddit;

import com.cd.reddit.RedditException;
import com.project.simplereddit.listactivity.RedditPostList;
import com.project.simplereddit.type.RedditSingleton;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void openGuestActivity(View view) {
		Intent myIntent = new Intent(MainActivity.this, RedditPostList.class);
		MainActivity.this.startActivity(myIntent);	
	}
	
	public void openLoginActivity(View view) {
		EditText usernameField = (EditText)findViewById(R.id.username);
		EditText passwordField = (EditText)findViewById(R.id.password);
		String username = usernameField.getText().toString();
		String password = passwordField.getText().toString();
		
		new LoginRequest().execute(username, password);
	}
	
	public class LoginRequest extends AsyncTask<String, String, Void> {
		
		@Override
		protected Void doInBackground(String... params){
			try {
				System.out.println("Start login");
				RedditSingleton.getInstance().login(params[0], params[1]);
				System.out.println("Complete login");
			} catch (RedditException e) {
				System.out.println("Error");
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void v) {
			Intent myIntent = new Intent(MainActivity.this, RedditPostList.class);
			MainActivity.this.startActivity(myIntent);
		}
	}
}
