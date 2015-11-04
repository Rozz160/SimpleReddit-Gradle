package com.project.simplereddit.listactivity;

import java.util.concurrent.ExecutionException;

import com.cd.reddit.json.util.RedditComments;
import com.project.simplereddit.R;
import com.project.simplereddit.adapter.CommentAdapter;
import com.project.simplereddit.requests.CommentRequest;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;

public class CommentList extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		
		Bundle extras = getIntent().getExtras();
		String subreddit = extras.getString("REDDIT_POST_SUBREDDIT");
		String id = extras.getString("REDDIT_POST_ID");
		
		RedditComments comments = null;
		try {
			comments = new CommentRequest().execute(subreddit, id).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		setListAdapter(new CommentAdapter(this, comments.getComments()));
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comment_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
