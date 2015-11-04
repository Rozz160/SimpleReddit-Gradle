package com.project.simplereddit.adapter;

import java.util.ArrayList;
import java.util.List;

import com.project.simplereddit.R;
import com.project.simplereddit.type.RedditPost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class RedditPostAdapter extends BaseAdapter {
	private Context context;
	private List<RedditPost> redditPost;

	public RedditPostAdapter(Context context) {
		this.context = context;
		redditPost = new ArrayList<RedditPost>();
	}
	
	public void addPosts(List<RedditPost> rp) {
		redditPost.addAll(rp);
		notifyDataSetChanged();
	}
	
	public List<RedditPost> getList() {
		return redditPost;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.activity_reddit_post_list, parent, false);
		
		TextView title = (TextView) rowView.findViewById(R.id.title);
		TextView score = (TextView) rowView.findViewById(R.id.votes);
		TextView author = (TextView) rowView.findViewById(R.id.author);
		TextView comment = (TextView) rowView.findViewById(R.id.comments);
		ImageView thumbnail = (ImageView) rowView.findViewById(R.id.logo);
		
		title.setText(redditPost.get(position).getPost().getTitle());
		score.setText("" + (redditPost.get(position).getPost().getUps() - redditPost.get(position).getPost().getDowns()));
		author.setText("by: " + redditPost.get(position).getPost().getAuthor() + " in " + redditPost.get(position).getPost().getSubreddit());
		comment.setText("Comments: " + redditPost.get(position).getPost().getNum_comments());
		
		if(redditPost.get(position).getImageDownloaded()) {
			thumbnail.setImageBitmap(redditPost.get(position).getThumbnail());
		}

		return rowView;
	}

	@Override
	public int getCount() {
		return redditPost.size();
	}

	@Override
	public Object getItem(int arg0) {
		return redditPost.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}