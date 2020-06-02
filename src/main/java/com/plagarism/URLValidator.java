package com.plagarism;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Java program to validate an url in Java
class URLValidator
{
	private static final String URL_REGEX =
			"^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
			"(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
			"([).!';/?:,][[:blank:]])?$";

	private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

	public static boolean urlValidator(String url) {

		if (url == null) {
			return false;
		}

		Matcher matcher = URL_PATTERN.matcher(url);
		return matcher.matches();
	}

	public static void main(String[] args)
	{
		String url = "https://www.techiedelight.com/";

		// Validate an url
		if (urlValidator(url)) {
			System.out.print("The URL " + url + " is valid");
		}
		else {
			System.out.print("The URL " + url + " isn't valid");
		}
	}
}