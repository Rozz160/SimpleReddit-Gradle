package com.project.simplereddit;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import com.project.simplereddit.requests.LinkHandler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class FullImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_image);
		
		setupActionBar();
		
		Bundle extras = getIntent().getExtras();
		String value = extras.getString("REDDIT_POST_URL");
		
		System.out.println("URL: " + value);
	
		ImageView image = (ImageView) findViewById(R.id.fullimage);
		try {
			image.setImageBitmap(new ImageRequest().execute(value).get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		getWindow().getDecorView().getRootView().setOnTouchListener(new TapGestureListener());
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
		getMenuInflater().inflate(R.menu.full_image, menu);
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
	
	private class TapGestureListener extends SimpleOnGestureListener implements OnTouchListener {
		
		GestureDetector gDetector;
		
		public TapGestureListener() {
			gDetector = new GestureDetector(FullImageActivity.this, this);
		}
		
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			finish();
			return super.onSingleTapConfirmed(e);
		}
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return gDetector.onTouchEvent(event);
		}
	}
	
	private class ImageRequest extends AsyncTask<String, Void, Bitmap> {
			
		@Override
		protected Bitmap doInBackground(String... params) {
			try {		
				String urlImage = new LinkHandler().getImageUrl(params[0]);

				if(urlImage != null){
					URL url = new URL(urlImage);
					InputStream content = (InputStream)url.getContent();
					return BitmapFactory.decodeStream(content);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
