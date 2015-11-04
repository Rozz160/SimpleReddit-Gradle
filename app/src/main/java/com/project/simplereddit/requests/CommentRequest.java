package com.project.simplereddit.requests;

import android.os.AsyncTask;

import com.cd.reddit.RedditException;
import com.cd.reddit.json.util.RedditComments;
import com.project.simplereddit.type.RedditSingleton;

public class CommentRequest extends AsyncTask<String, String, RedditComments> {
	
	@Override
	protected RedditComments doInBackground(String... params) {

		RedditComments comment = null;
		try {			
			comment = RedditSingleton.getInstance().commentsFor(params[0], params[1]);
		} catch (RedditException e) {
			e.printStackTrace();
		}
		
		return comment;
	}
}
