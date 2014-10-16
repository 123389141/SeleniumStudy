package com.test.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.test.configure.Variable;
import com.test.http.HttpRequester;
import com.test.http.HttpRespons;
import com.test.tool.TestTool;

public class HttpAction implements IAction {

	
	public String[] str ={Variable.flag[0],Variable.sresult[0]}; 
	
	//("http://g.atguat.com.cn/ec/homeus/myaccount/login.jsp?_DARGS=/ec/myaccount/login.jsp",null,null,null)
	//"http://g.atguat.com.cn/ec/homeus/myaccount/login.jsp?_DARGS=/ec/myaccount/login.jsp",xmls=map,cookicMap,null;
	
	public String[]  sendGet(Object obj)throws Exception{
		String[] s =(String[])obj;
		 HttpRequester request = new HttpRequester(); 
		return str;
	}
	
	public static void main(String[] args) {
        try { 
        	new HttpAction();
        	TestTool.initElement();
            HttpRequester request = new HttpRequester();     
            Map<String, String> map = new HashMap<String, String>();
            Map<String, String> map1 = new HashMap<String, String>();
            //map.put("_dyncharset","utf-8");
            String param = "";
            //System.out.println(map.get("_dyncharset"));
            
            
            //_dyncharset=utf-8&_dynSessConf=1885962775661229429&%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.loginSuccessURL=http%3A%2F%2Fg.atguat.com.cn%2Fec%2Fhomeus%2Fmyaccount%2Fgome%2FprofileHome.jsp%3Fflag%3Da%26_requestid%3D456&_D%3A%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.loginSuccessURL=+&%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.loginErrorURL=%2Fec%2Fhomeus%2Fmyaccount%2Flogin.jsp%3FtableName%3Dlogin&_D%3A%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.loginErrorURL=+&%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.value.login=zdh_01&_D%3A%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.value.login=+&gomeOrCoo8=gome&%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.value.password=zdh12345678&_D%3A%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.value.password=+&captcha=&%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.value.autoLoginMode=&_D%3A%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.value.autoLoginMode=+&chkRememberUsername=1&%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.orginURI=http%3A%2F%2Fg.atguat.com.cn%2Fec%2Fhomeus%2Fmyaccount%2Fgome%2FprofileHome.jsp%3Fflag%3Da%26_requestid%3D456&_D%3A%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.orginURI=+&%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.login=%E7%99%BB%E5%BD%95&_D%3A%2Fatg%2Fuserprofiling%2FB2CProfileFormHandler.login=+&_DARGS=%2Fec%2Fmyaccount%2Flogin.jsp
            
            
            map.put("_dyncharset","utf-8");
			map.put("_dynSessConf","1885962775661229429");
			map.put("/atg/userprofiling/B2CProfileFormHandler.loginSuccessURL","http://g.atguat.com.cn/ec/homeus/myaccount/gome/profileHome.jsp?flag=a&_requestid=456&locale=zh_CN");
			map.put("_D:/atg/userprofiling/B2CProfileFormHandler.loginSuccessURL","");
			map.put("/atg/userprofiling/B2CProfileFormHandler.loginErrorURL","/ec/homeus/myaccount/login.jsp?tableName=login");
			map.put("_D:/atg/userprofiling/B2CProfileFormHandler.loginErrorURL","");
			map.put("/atg/userprofiling/B2CProfileFormHandler.value.login","zdh_01");
			map.put("_D:/atg/userprofiling/B2CProfileFormHandler.value.login","");
			map.put("gomeOrCoo8","gome");
			map.put("/atg/userprofiling/B2CProfileFormHandler.value.password","zdh12345678");
			map.put("_D:/atg/userprofiling/B2CProfileFormHandler.value.password","");
			map.put("captcha","");
			map.put("/atg/userprofiling/B2CProfileFormHandler.value.autoLoginMode","");
			map.put("_D:/atg/userprofiling/B2CProfileFormHandler.value.autoLoginMode","");
			map.put("chkRememberUsername","1");
			map.put("/atg/userprofiling/B2CProfileFormHandler.orginURI","http://g.atguat.com.cn/ec/homeus/myaccount/gome/profileHome.jsp?flag=a&_requestid=456");
			map.put("_D:/atg/userprofiling/B2CProfileFormHandler.orginURI","");
			map.put("/atg/userprofiling/B2CProfileFormHandler.login","µÇÂ¼");
			map.put("_D:/atg/userprofiling/B2CProfileFormHandler.login","");
			map.put("_DARGS","/ec/myaccount/login.jsp");   
            for (String key : map.keySet()) {  
                
            	//param.append(key).append("=").append(map.get(key)); 
            	param = param+key+"="+map.get(key)+"&"; 
            	
               
            }  
          //  System.out.println("AAAAAAAAAAAAAA++++"+param);  
            map1.put("Host","g.atguat.com.cn");
            map1.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0");
            map1.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            map1.put("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            map1.put("Accept-Encoding","gzip, deflate");
            map1.put("Referer","http://g.atguat.com.cn/ec/homeus/myaccount/login.jsp?DPSLogout=true&_requestid=2984");
            map1.put("Connection","keep-alive");
            map1.put("Content-Type","application/x-www-form-urlencoded");
            Map<String, String> cookicMap = new HashMap<String, String>();
            List<HttpCookie> list=null;
            //
            HttpRespons hr = null;
            
            hr = request.sendGet("http://g.atguat.com.cn/ec/homeus/myaccount/login.jsp?_DARGS=/ec/myaccount/login.jsp",null,null,null);
            hr = request.sendPost("http://g.atguat.com.cn/ec/homeus/myaccount/login.jsp?_DARGS=/ec/myaccount/login.jsp",map,cookicMap,null);
            hr = request.sendPost("http://g.atguat.com.cn/ec/homeus/cart/add.jsp?callback=addCart&_=1384333331882&productId=prod18190080&catalogRefId=sku18120050&quantity=1",null,null,hr.getHCoolies());
           
           System.out.println("AAAAAAAAAAAA"+hr.getMessage());
           System.out.println("BBBBB"+hr.getContent());
        } catch (Exception e) {     
            e.printStackTrace();     
        }     
    }     
	
	

}


