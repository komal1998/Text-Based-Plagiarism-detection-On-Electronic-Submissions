package com.plagarism;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class CountMatchesTest {

	public static String checkCount(String textToSearch,String str) throws Exception {
		textToSearch=textToSearch.replace("\"","");
		
		String dataToSent="";
		if(textToSearch!=null && textToSearch.trim().split(" ")!=null && textToSearch.trim().split(" ").length>0){
			for(int i=0;i<textToSearch.trim().split(" ").length;i++){
				//String str = FileUtils.readFileToString(new File("./test.txt"));
				//System.out.println(str);
				//System.out.println("String:\n" + str);
				str = StringUtils.lowerCase(str);
				
				int countThe = StringUtils.countMatches(str, textToSearch.split(" ")[i].toLowerCase());
				System.out.println(textToSearch.split(" ")[i]+"("+ countThe+")");
				dataToSent=dataToSent+","+textToSearch.split(" ")[i]+"("+ countThe+")";
			}
		}
		
		return dataToSent;
	}
	
	
	public static String checkSpaceCount(String textToSearch,String str) throws Exception {

		String dataToSent="";
		str = StringUtils.lowerCase(str);
		int countThe = StringUtils.countMatches(str, textToSearch);
		dataToSent=dataToSent+",AllWordsCount("+ countThe+")";
		return dataToSent;
	}
	
	public static void main(String[] args) throws Exception {

		String str ="snapshot of the page snapshot of the page snapshot of the page";
		System.out.println("String:\n" + str);
		str = StringUtils.lowerCase(str);
		String ss=checkCount(str,"page");
		System.out.println(ss);
	}

}