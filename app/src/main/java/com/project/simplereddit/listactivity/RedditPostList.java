package com.project.simplereddit.listactivity;

import com.project.simplereddit.R;
import com.project.simplereddit.adapter.RedditPostAdapter;
import com.project.simplereddit.adapter.SwipeGestureListener;
import com.project.simplereddit.manager.PostManager;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class RedditPostList extends ListActivity implements OnScrollListener {

	private RedditPostAdapter listAdapter;
	private PostManager pm;
	private String subreddit;
	private String group;
	private static final String STATE_SUBREDDIT = "subreddit";
	private static final String STATE_GROUP = "group";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		
		if(savedInstanceState == null) {
			group = "hot";
			subreddit = "all";
		} else {
			group = savedInstanceState.getString(STATE_GROUP);
			subreddit = savedInstanceState.getString(STATE_SUBREDDIT);
		}
		
		setTitle("Subreddit: " + subreddit + "  |  " + group);
		
		pm = new PostManager(this, subreddit, group);
		listAdapter = new RedditPostAdapter(this);
		listAdapter.addPosts(pm.getMorePosts());
		setListAdapter(listAdapter);
		getListView().setOnScrollListener(this);
		getListView().setOnTouchListener(new SwipeGestureListener(this, getListView()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reddit_post_list, menu);
		
		View v = (View) menu.findItem(R.id.search).getActionView();
        EditText txtSearch = ( EditText ) v.findViewById(R.id.reddit_search);
 
        txtSearch.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				subreddit = v.getText().toString();
				recreate();
				return false;
			}
        });
		return true;
	}
	
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.refresh:
			recreate();
			return true;
		case R.id.group_hot:
			group = "hot";
			recreate();
			return true;
		case R.id.group_top:
			group = "top";
			recreate();
			return true;
		case R.id.group_new:
			group = "new";
			recreate();
			return true;
		case R.id.group_controversial:
			group = "controversial";
			recreate();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	    savedInstanceState.putString(STATE_GROUP, group);
	    savedInstanceState.putString(STATE_SUBREDDIT, subreddit);
	    
	    super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	public void onScroll(AbsListView lw, final int firstVisibleItem,
	                 final int visibleItemCount, final int totalItemCount) {

		switch(lw.getId()) {
	    	case android.R.id.list:     

			final int lastItem = firstVisibleItem + visibleItemCount;
			if(lastItem == totalItemCount) {
				listAdapter.addPosts(pm.getMorePosts());
			}
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public RedditPostAdapter getListAdapter() {
		return listAdapter;
	}
}