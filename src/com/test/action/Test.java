package com.test.action;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.test.configure.Variable;
import com.test.tool.TestTool;

/*
 * httpXmls()   xmls= ���ڵ�@@�ӽڵ�@@���սڵ�
 * httpXml()     xml=   xmlsit=aaa  ""   ��ͬ���Է��ص�һ��
 * gomeXml()     xml=   xmlsit=aaa  ""   ��ͬ���Է��ص�һ��
 * sendXml()     xml=   xmlsit=aaa  ""   ��ͬ���Է��ص�һ��
 */

public class Test implements IAction{

	public String[] str ={Variable.flag[0],Variable.sresult[0]}; 
	public String[]  testHttpXmls(Object obj)throws Exception{
		String[] driver = (String[])obj;
		
		
		
		return str;
			
	} 
	
}
