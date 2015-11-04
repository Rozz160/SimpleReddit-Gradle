package com.project.simplereddit.type;

import com.cd.reddit.Reddit;

public class RedditSingleton {
	private static Reddit instance=null;
	private final static String testAccount = "SimpleRedditAccount";
	
	public static Reddit getInstance() {
		if(instance==null) {
			instance=new Reddit(testAccount);
		}
		return instance;
	}
}
