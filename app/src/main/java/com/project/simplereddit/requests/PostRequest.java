package com.project.simplereddit.requests;

import java.util.List;

import android.os.AsyncTask;

import com.cd.reddit.RedditException;
import com.cd.reddit.json.mapping.RedditLink;
import com.project.simplereddit.type.RedditSingleton;

public class PostRequest extends AsyncTask<String, String, List<RedditLink>> {
	
	@Override
	protected List<RedditLink> doInBackground(String... params) {
		
		List<RedditLink> rl = null;
		try {			
			rl = RedditSingleton.getInstance().listingFor(params[0], params[1], params[2], params[3]);
			System.out.println("Size: " + rl.size());
			
		} catch (RedditException e) {
			e.printStackTrace();
		}
		
		return rl;
	}
}
