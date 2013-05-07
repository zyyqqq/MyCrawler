package com.spider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class UserLogin
{
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	private static String username = "13782884742";
	private static String password = "7721887410";
	private static String url = "http://www.renren.com/PLogin.do";
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("origURL", "http://www.renren.com"));
		nvps.add(new BasicNameValuePair("domain", "renren.com"));
		nvps.add(new BasicNameValuePair("isplogin", "true"));
		nvps.add(new BasicNameValuePair("forName", ""));
		nvps.add(new BasicNameValuePair("method", ""));
		nvps.add(new BasicNameValuePair("submit", "µÇÂ¼"));
		nvps.add(new BasicNameValuePair("email", username));  
        nvps.add(new BasicNameValuePair("password", password)); 
		
		try
		{
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			/*
			Header[] h = httpResponse.getAllHeaders();
			for(Header e : h)
			{
				System.out.println(e.getName() + " : " + e.getValue());
			}
			*/
			/*
			InputStream is = httpResponse.getEntity().getContent();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			while( null != (str = br.readLine()))
			{
				System.out.println(str);
			}
			is.close();
			*/
			
			Header locationHeader = httpResponse.getFirstHeader("Location");
			if(locationHeader != null)
			{
				String redirectUrl = locationHeader.getValue();
				
				httpPost.abort();
				
				HttpGet httpGet = new HttpGet(redirectUrl);
				
			    httpResponse = httpClient.execute(httpGet);
			    
			    InputStreamReader isr = new InputStreamReader(httpResponse.getEntity().getContent()
			    		, "utf-8");
			    BufferedReader br = new BufferedReader(isr);
			    
			    FileWriter fw = new FileWriter("userFirstPage.html");
			    BufferedWriter bw = new BufferedWriter(fw);
			    String content = null;
			    while(null != (content = br.readLine()))
			    {
			    	//System.out.println(content);
			    	bw.write(content);
			    	bw.newLine();
			    }
			    fw.close();
			    isr.close();
			}
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
