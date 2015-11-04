package com.project.simplereddit.type;

import com.cd.reddit.json.mapping.RedditLink;

import android.graphics.Bitmap;

public class RedditPost {
	private Bitmap thumbnail;
	private RedditLink post;
	private boolean imageDownloaded= false;
	
	public Bitmap getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Bitmap thumbnail) {
		this.thumbnail = thumbnail;
	}
	public RedditLink getPost() {
		return post;
	}
	public void setPost(RedditLink post) {
		this.post = post;
	}
	public boolean getImageDownloaded() {
		return imageDownloaded;
	}
	public void setImageDownloaded(boolean downloadedThumbnail) {
		this.imageDownloaded = downloadedThumbnail;
	}
	
}
