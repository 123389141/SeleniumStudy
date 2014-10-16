package com.test.http;

import java.awt.List;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.net.HttpURLConnection; 
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;  
import java.nio.charset.Charset;  
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;  
import java.util.Set;
import java.util.Vector;  





/** 
 * HTTP请求对象 
 *  
 * @author YYmmiinngg 
 */  
public class HttpRequester {  
    private static final String HttpCookie = null;
	private String defaultContentEncoding;  
   
    public HttpRequester() {  
        this.defaultContentEncoding = Charset.defaultCharset().name();  
    }  
   //setRequestProperty使用该方法 添加 Header
    /** 
     * 发送GET请求 
     *  
     * @param urlString 
     *            URL地址 
     * @return 响应对象 
     * @throws IOException 
     */  
    public HttpRespons sendGet(String urlString) throws IOException {  
        return this.send(urlString, "GET", null, null,null);  
    }  
   
    public HttpRespons sendGet(String urlString,Map<String, String> params) throws IOException {  
        return this.send(urlString, "GET", params, null,null);  
    }
    public HttpRespons sendGet(String urlString,Map<String, String> params,Map<String, String> propertys) throws IOException {  
        return this.send(urlString, "GET", params, propertys,null);  
    }
    public HttpRespons sendGet(String urlString,Map<String, String> params,Map<String, String> propertys,Map<String,String>  cookies) throws IOException {  
        return this.send(urlString, "GET", params, propertys,cookies);  
    }
    
   
    public HttpRespons sendPost(String urlString) throws IOException {  
        return this.send(urlString, "POST", null, null,null);  
    }  
   
    public HttpRespons sendPost(String urlString,Map<String, String> params) throws IOException {  
        return this.send(urlString, "POST", params, null,null);  
    }
    public HttpRespons sendPost(String urlString,Map<String, String> params,Map<String, String> propertys) throws IOException {  
        return this.send(urlString, "POST", params, propertys,null);  
    }
    public HttpRespons sendPost(String urlString,Map<String, String> params,Map<String, String> propertys,Map<String,String>  cookies) throws IOException {  
        return this.send(urlString, "POST", params, propertys,cookies);  
    }

 
   
    /** 
     * 发送HTTP请求 
     *  
     * @param urlString 
     * @return 响映对象 
     * @throws IOException 
     */  
    CookieManager manager=null;
    private HttpRespons send(String urlString, String method,  
            Map<String, String> parameters, Map<String, String> propertys,Map<String,String>  cookies)  
            throws IOException {  
        HttpURLConnection urlConnection = null;  
        if (method.equalsIgnoreCase("GET") && parameters != null) {  
            StringBuffer param = new StringBuffer();  
            int i = 0;  
            for (String key : parameters.keySet()) {  
                if (i == 0)  
                    param.append("?");  
                else  
                    param.append("&");  
                param.append(key).append("=").append(parameters.get(key));  
                i++;  
            }  
            urlString += param;  
        }  
      //  Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
        URL url = new URL(urlString);  
        //urlConnection = (HttpURLConnection) url.openConnection();
        ///urlConnection = (HttpURLConnection) url.openConnection(proxy); 
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method);  
        urlConnection.setDoOutput(true);  
        urlConnection.setDoInput(true);  
        urlConnection .setInstanceFollowRedirects(false);   //想对中间的Location做些处理则可以通过HttpURLConnection实现。 // 必须设置false，否则会自动redirect到Location的地址 conn.setInstanceFollowRedirects(false);
        urlConnection.setUseCaches(false);
        
       
        
        if (propertys != null)  
            for (String key : propertys.keySet()) {  
            	
                urlConnection.addRequestProperty(key, propertys.get(key));  
            }
        if(cookies!=null){
        	StringBuffer sbu = new StringBuffer();
        	sbu.append("eos_style_cookie=default; ");
        	for (String key : cookies.keySet()) { 
        		sbu.append(cookies.get(key).substring(0,cookies.get(key).indexOf(";")+1));
        	}
        	
        	//System.out.println("sbu.toStringsbu.toStringsbu.toString+++"+sbu.toString());
        	
        	urlConnection.setRequestProperty("Cookie",sbu.toString());
        }
        if (method.equalsIgnoreCase("POST") && parameters != null) {  
            StringBuffer param = new StringBuffer();  
            for (String key : parameters.keySet()) {  
                param.append(key).append("=").append(parameters.get(key)); 
                param.append("&");
            }  
            System.out.println(param.toString());
            urlConnection.getOutputStream().write(param.toString().getBytes());
            urlConnection.getOutputStream().flush();  
            urlConnection.getOutputStream().close();  
        }  
        	
        return this.makeContent(urlString, urlConnection);  
    }  
   
    /** 
     * 得到响应对象 
     *  
     * @param urlConnection 
     * @return 响应对象 
     * @throws IOException 
     */  
    private HttpRespons makeContent(String urlString,  
            HttpURLConnection urlConnection) throws IOException {  
        HttpRespons httpResponser = new HttpRespons();  
        try {  
        	manager = new CookieManager();
        	manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            InputStream in = urlConnection.getInputStream();  
            BufferedReader bufferedReader = new BufferedReader(  
                    new InputStreamReader(in));  
            httpResponser.contentCollection = new Vector<String>();  
            StringBuffer temp = new StringBuffer();  
            String line = bufferedReader.readLine();  
            while (line != null) {  
                httpResponser.contentCollection.add(line);  
                temp.append(line).append("\r\n");  
                line = bufferedReader.readLine();  
            }  
            bufferedReader.close();  
            
            
            
            
            //MessageHeader mh = new MessageHeader();
            String ecod = urlConnection.getContentEncoding();  
            if (ecod == null)  
                ecod = this.defaultContentEncoding;
            CookieStore cookieJar = manager.getCookieStore();
            httpResponser.coolies= cookieJar.getCookies();
            httpResponser.urlString = urlString;  
            httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();  
            httpResponser.file = urlConnection.getURL().getFile();  
            httpResponser.host = urlConnection.getURL().getHost();  
            httpResponser.path = urlConnection.getURL().getPath();  
            httpResponser.port = urlConnection.getURL().getPort();  
            httpResponser.protocol = urlConnection.getURL().getProtocol();  
            httpResponser.query = urlConnection.getURL().getQuery();  
            httpResponser.ref = urlConnection.getURL().getRef();  
            httpResponser.userInfo = urlConnection.getURL().getUserInfo();  
            httpResponser.content = new String(temp.toString().getBytes(), ecod);  
            httpResponser.contentEncoding = ecod;  
            httpResponser.code = urlConnection.getResponseCode();  
            httpResponser.message = urlConnection.getResponseMessage();  
            httpResponser.contentType = urlConnection.getContentType();  
            httpResponser.method = urlConnection.getRequestMethod();  
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();  
            httpResponser.readTimeout = urlConnection.getReadTimeout();  
           // System.out.println(cookieJar.getCookies().toString());
            Map<String,String> a = new HashMap<String, String>();  
            System.out.println("+++++++++++");
             if(urlConnection.getHeaderFields().get("Set-Cookie")!=null){
	             for(int i=0;i<urlConnection.getHeaderFields().get("Set-Cookie").size();i++){
	            	 
	            	 //System.out.println(urlConnection.getHeaderFields().get("Set-Cookie").toString());
	            	 System.out.println(urlConnection.getHeaderFields().get("Set-Cookie").get(i));
	            	 a.put(Integer.toString(i), urlConnection.getHeaderFields().get("Set-Cookie").get(i));
	             }
	             httpResponser.Hcoolies=a;
             }
             System.out.println("--------------");
            return httpResponser;  
        } catch (IOException e) {  
            throw e;  
        } finally {  
            if (urlConnection != null)  
                urlConnection.disconnect();  
        }  
    }  
    
    
   
  
    /** 
     * 默认的响应字符集 
     */  
    public String getDefaultContentEncoding() {  
        return this.defaultContentEncoding;  
    }  
   
    /** 
     * 设置默认的响应字符集 
     */  
    public void setDefaultContentEncoding(String defaultContentEncoding) {  
        this.defaultContentEncoding = defaultContentEncoding;  
    }  
}  
