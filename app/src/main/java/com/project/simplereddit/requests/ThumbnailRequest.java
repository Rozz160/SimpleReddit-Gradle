package com.project.simplereddit.requests;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.project.simplereddit.listactivity.RedditPostList;
import com.project.simplereddit.type.RedditPost;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;


public class ThumbnailRequest extends AsyncTask<String, Void, Bitmap> {
	
	private RedditPost rp;
	private RedditPostList context;
	
	public ThumbnailRequest(RedditPostList context, RedditPost rp) {
		this.context = context;
		this.rp = rp;
	}
		
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
	
	@Override
	protected void onPostExecute(Bitmap result) {
		if(result != null) {
			Bitmap scaledBitmap = ThumbnailUtils.extractThumbnail(result, 110, 110);
			rp.setThumbnail(scaledBitmap);
			rp.setImageDownloaded(true);
			context.getListAdapter().notifyDataSetChanged();
		}
	}
	
}
