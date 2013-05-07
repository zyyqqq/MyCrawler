package com.spider;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


public class MyCrawler
{
	private static String rootUrl = "http://blog.csdn.net";
	private static String prefix = "http://blog.csdn.net";
	private Queue<String> queue = new LinkedList<String>();
	private  HashSet<String> visitedUrls = new HashSet<String>();
	
	public String fetchFileName(String url)
	{
		int beg = url.lastIndexOf('/') + 1;
		return url.substring(beg);
	}
	public void downloadPageFrom(String url)
	{
		try
		{
			String fileName = fetchFileName(url);
			System.out.println(fileName);
			FileOutputStream fos = new FileOutputStream(fileName);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler(); 
			String content = httpClient.execute(httpGet, responseHandler);
			bw.write(content);
			fos.close();
			this.retrieveUrl(content);
			//System.out.println(content);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void retrieveUrl(String content)
	{
		
		/*Pattern pattern = Pattern.compile("<a href=\"/hejingyuan6/article/details/\\d+\">");
		Matcher matcher = pattern.matcher(content);
		while(matcher.find())
		{
			String matcherResult = matcher.group();
			System.out.println(matcherResult);
			String suffix = 
					matcherResult.substring(matcherResult.indexOf('/'), matcherResult.lastIndexOf('\"'));
			String url = MyCrawler.prefix + suffix;
			if( !visitedUrls.contains(url) )
			{
				visitedUrls.add(url);
				this.downloadPageFrom(url);
			}
		}*/
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new MyCrawler().downloadPageFrom(MyCrawler.rootUrl);
		System.out.println("download finished!");
	}

}
