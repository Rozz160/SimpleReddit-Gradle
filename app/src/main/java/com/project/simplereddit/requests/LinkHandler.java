package com.project.simplereddit.requests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkHandler {
	
	public static final Pattern imgurPattern = Pattern.compile(".*imgur\\.com/(\\w+).*"),
			qkmePattern1 = Pattern.compile(".*qkme\\.me/(\\w+).*"),
			qkmePattern2 = Pattern.compile(".*quickmeme\\.com/meme/(\\w+).*"),
			lvmePattern = Pattern.compile(".*livememe\\.com/(\\w+).*");
	
	public String getImageUrl(final String url) {

		final String urlLower = url.toLowerCase();

		final String[] imageExtensions = {".jpg", ".jpeg", ".png", ".gif"};

		for(final String ext : imageExtensions) {
			if(urlLower.endsWith(ext)) {
				return url;
			}
		}

		if(url.contains("?")) {

			final String urlBeforeQ = urlLower.split("\\?")[0];

			for(final String ext : imageExtensions) {
				if(urlBeforeQ.endsWith(ext)) {
					return url;
				}
			}
		}

		final Matcher matchImgur = imgurPattern.matcher(url);

		if(matchImgur.find()) {
			final String imgId = matchImgur.group(1);
			if(imgId.length() > 2)
				return String.format("http://i.imgur.com/%s.jpg", imgId);
		}

		final Matcher matchQkme1 = qkmePattern1.matcher(url);

		if(matchQkme1.find()) {
			final String imgId = matchQkme1.group(1);
			if(imgId.length() > 2)
				return String.format("http://i.qkme.me/%s.jpg", imgId);
		}

		final Matcher matchQkme2 = qkmePattern2.matcher(url);

		if(matchQkme2.find()) {
			final String imgId = matchQkme2.group(1);
			if(imgId.length() > 2)
				return String.format("http://i.qkme.me/%s.jpg", imgId);
		}

		final Matcher matchLvme = lvmePattern.matcher(url);

		if(matchLvme.find()) {
			final String imgId = matchLvme.group(1);
			if(imgId.length() > 2)
				return String.format("http://www.livememe.com/%s.jpg", imgId);
		}

		return null;
	}
}
