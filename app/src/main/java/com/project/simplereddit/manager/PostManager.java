package com.project.simplereddit.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.cd.reddit.json.mapping.RedditLink;
import com.project.simplereddit.listactivity.RedditPostList;
import com.project.simplereddit.requests.PostRequest;
import com.project.simplereddit.requests.ThumbnailRequest;
import com.project.simplereddit.type.RedditPost;

public class PostManager {
	private String lastId = "null";
	private String postLimit = "25";
	private String subreddit, group;
	private RedditPostList context;
	
	public PostManager(RedditPostList context, String subreddit, String group) {
		this.subreddit = subreddit;
		this.group = group;
		this.context = context;
	}
	
	private List<RedditLink> requestRedditPosts() throws InterruptedException, ExecutionException {
		return new PostRequest().execute(subreddit, group, postLimit, lastId).get();
	}
	
	private List<RedditPost> setRedditPostList(List<RedditLink> rl) {
		List<RedditPost> list = new ArrayList<RedditPost>();
		RedditPost rp;

		for(RedditLink r : rl) {
			rp = new RedditPost();
			rp.setPost(r);
			
			new ThumbnailRequest(context, rp).execute(r.getUrl());
			
			list.add(rp);
		}
		lastId = "t3_" + list.get(list.size()-1).getPost().getId();
		return list;
	}
	
	public List<RedditPost> getMorePosts() {
		try {
			return setRedditPostList(requestRedditPosts());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
