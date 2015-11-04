package com.project.simplereddit.adapter;

import java.util.List;

import com.cd.reddit.json.mapping.RedditComment;
import com.project.simplereddit.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
public class CommentAdapter extends BaseAdapter {
	private Context context;
	private List<RedditComment> redditPost;

	public CommentAdapter(Context context, List<RedditComment> redditPost) {
		this.context = context;
		this.redditPost = redditPost;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.activity_comment_list, parent, false);
		
		TextView commentDetails = (TextView) rowView.findViewById(R.id.comment_details);
		TextView commentBody = (TextView) rowView.findViewById(R.id.comment_body);
		
		commentDetails.setText("Score: " + (redditPost.get(position).getUps() - redditPost.get(position).getDowns()) + " by: " + redditPost.get(position).getAuthor());
		commentBody.setText(redditPost.get(position).getBody());

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