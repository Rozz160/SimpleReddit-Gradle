package com.project.simplereddit.adapter;

import com.project.simplereddit.FullImageActivity;
import com.project.simplereddit.listactivity.CommentList;
import com.project.simplereddit.type.RedditPost;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.ListView;

public class SwipeGestureListener extends SimpleOnGestureListener implements OnTouchListener {
	
	private ListView listView;
	private Context context;
	private GestureDetector gDetector;
	private static final int SWIPE_MIN_DISTANCE = 150;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	
	public SwipeGestureListener() {
		super();
	}
	
	public SwipeGestureListener(Context context, ListView listView) {
		this(context, null, listView);
	}
	
	public SwipeGestureListener(Context context, GestureDetector gDetector, ListView listView) {
		if (gDetector == null)
			gDetector = new GestureDetector(context, this);
		
		this.context = context;
		this.gDetector = gDetector;
		this.listView = listView;
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	
		final int position = listView.pointToPosition(50, Math.round(e2.getY()));
		RedditPost post = (RedditPost) listView.getItemAtPosition(position);
		
		if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY) {
			return false;
		}
		if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {
			Intent myIntent = new Intent(context, CommentList.class);
			myIntent.putExtra("REDDIT_POST_SUBREDDIT",post.getPost().getSubreddit());
			myIntent.putExtra("REDDIT_POST_ID",post.getPost().getId());
			context.startActivity(myIntent);
			
		} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {
			Intent myIntent = new Intent(context, FullImageActivity.class);
			myIntent.putExtra("REDDIT_POST_URL",post.getPost().getUrl());
			context.startActivity(myIntent);	
		}

		return super.onFling(e1, e2, velocityX, velocityY);
	}
	
	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		final int position = listView.pointToPosition(50, Math.round(e.getY()));
		RedditPost post = (RedditPost) listView.getItemAtPosition(position);
		
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(post.getPost().getUrl()));
		context.startActivity(browserIntent);
		
		return super.onSingleTapConfirmed(e);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return gDetector.onTouchEvent(event);
	}
	
	public GestureDetector getDetector() {
		return gDetector;
	}
}
