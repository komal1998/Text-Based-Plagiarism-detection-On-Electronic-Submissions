package com.plagarism;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.highlight.TextFragment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.plagarism.file.ReadIndexFromFileExample;
import com.plagarism.file.WriteIndexFromFileExample;
import com.plagarism.highlight.HighlightAnalysisMain;
public class DownloadFromGoogle {
	 private static final String INDEX_DIR = "c:\\temp\\";
		public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
		 
	
	public static String getContent(String url)
	{
		
		
		
		
		return null;
	}
	
	 
	
	public static Map<String,TextFragment> getLinkSFromGoogle(String searchTerm, String excludeUrl) throws IOException
	{
		Map<String,TextFragment> mapTextFragment=new HashMap<String,TextFragment>();
		
		List<TextFragment> textFragmentList=new ArrayList<TextFragment>();
		try{
		String searchURL = GOOGLE_SEARCH_URL + "?q="+searchTerm+"&num="+20;
		//without proper User-Agent, we will get 403 error
		Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
		
		//below will print HTML data, save it to a file and open in browser to compare
		//System.out.println(doc.html());
		
		//If google search results HTML change the <h3 class="r" to <h3 class="r1"
		//we need to change below accordingly
		Elements results = doc.select("div.g a");

		for (Element result : results) {
			try{
			String linkHref = result.attr("href");
			System.out.println(linkHref);
			if(URLValidator.urlValidator(linkHref)){
				if(excludeUrl!=null && !excludeUrl.trim().equals("") && linkHref.contains(excludeUrl))
					continue;
				
				Document doc2 = Jsoup.connect(linkHref).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
				String data = doc2.body().text();
				//System.out.println("----------------------");
				//System.out.println(data);
				System.out.println("----------------------");
				if(data!=null && data.length()>5){
				File file = new File(INDEX_DIR,"file"+System.currentTimeMillis()+".txt");
				try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
					
					//create file if not exists
					if (!file.exists()) {
						file.createNewFile();
					}
					//fetch bytes from data
					byte[] bs = data.getBytes();
					fileOutputStream.write(bs);
					fileOutputStream.flush();
					fileOutputStream.close();
					//System.out.println("File written successfully."+file.getAbsolutePath());
					linkHref=linkHref+"::-::"+CountMatchesTest.checkCount(searchTerm,data)+" "+CountMatchesTest.checkSpaceCount(" ", data);
						TextFragment getTextFragment=HighlightAnalysisMain.getTextFragment(searchTerm, file.getAbsolutePath(), data);
						if(getTextFragment!=null)
						{
							System.out.println(getTextFragment.toString());
							
							mapTextFragment.put(linkHref,getTextFragment);
							textFragmentList.add(getTextFragment);
						}
					
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
		}
		
		
		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		}
		catch(Exception exx)
		{
			exx.printStackTrace();
		}
		
		return mapTextFragment;
		
	}
	
	
	public static void main(String args[])
	{
		//getContent("http://www.javatpoint.com");
		try {
			
			
			getLinkSFromGoogle("java training","javatpoint");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	
	

}
