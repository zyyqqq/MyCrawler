package com.spider;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParserTool
{

	/**
	 * @param args
	 */
	private  Parser parser =  null;
	
	public void test()
	{
		try
		{
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("http://blog.csdn.net");
			String content = httpClient.execute(httpGet, new BasicResponseHandler());
			System.out.println(content);
			parser = new Parser(content);
			NodeFilter nodeFilter = new NodeFilter()
			{

				@Override
				public boolean accept(Node node)
				{
					// TODO Auto-generated method stub
					if(node instanceof LinkTag)
					{
						//System.out.println(((LinkTag) node).getLink());
						//if(((LinkTag) node).getLink().endsWith("html"))
							return true;
					}
					return false;
				}
				
			};
			NodeList nodeList = parser.extractAllNodesThatMatch(nodeFilter);
			for(int i = 0; i < nodeList.size(); ++i)
			{
				//System.out.println(((LinkTag)nodeList.elementAt(i)).getLink());
			}
			
		} catch (ParserException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new HtmlParserTool().test();
	}

}
