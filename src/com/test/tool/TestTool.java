package com.test.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import com.test.bean.TestCaseBean;
import com.test.bean.TestMethodBean;
import com.test.configure.Variable;

public class TestTool {
	
	
	private static   HashMap hmap;
	private static String st;
	
	public static void  initElement() throws Exception{
		Variable.gomexml = getElement(Variable.sendPath,"GomeXml.xml");
		Variable.send = getElement(Variable.sendPath,"Send.xml");
		Variable.httpxml = getElement(Variable.sendPath,"GomeHttp.xml");
	}
	
	public static  HashMap toMap(String locator){
		hmap =new HashMap();
		for(int i = 0;i<locator.length();i++){
			if(locator.substring(i,i+1).equals("=")){
				hmap.put(locator.substring(0,i).trim(), locator.substring(i+1,locator.length()).trim());
			}
		}
		return hmap;
	}
	
	public static String getType(HashMap locator){
		Set s =  locator.entrySet();
		Iterator it = s.iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			st =(String)entry.getKey();
		}
		return st;
	}
	
	
	public static Element getElement(String path,String fileName ) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        DocumentBuilder db = dbf.newDocumentBuilder();  
        Document doc = db.parse(new File(getPath(path,fileName)));  
        return doc.getDocumentElement();
	}
	

	public static String isFlag(String cassflag,TestCaseBean tcb){
			if(cassflag==Variable.flag[0]){
				return tcb.getStepFlag();
			}else if(cassflag==Variable.flag[1]){
				return Variable.flag[1];
			}else{
				return Variable.flag[2];
			}
	}
	
	public static String getPath(String path,String fileName){
		
		return System.getProperty("user.dir")+path+fileName;
	}
	public static void writeLog(String log){
		OutputStreamWriter osw;
		try {	
			osw = new OutputStreamWriter(new FileOutputStream(new File(getPath(Variable.runlogpath,"run.log")),true),"GBK");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.append(log+"\n");
	    	bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//bw.close();
			e.printStackTrace();
		}
	}
	
	public static String getDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	
	
	public static TestMethodBean repustTestMethodBean(TestMethodBean tmb,String[] str){
    	tmb.setStepFlag(str[0]);
    	tmb.setRemarks(str[1]);
    	return tmb;
    }
	/*获得xml环境信息
	 * 
	 */
	public static String  replaceSting(String str,String replace) throws DOMException, Exception{
		
		if(replace.length()>0){
			return str.replace("#variable#",replace);
		}else{
			return str;
		}
	}
	
	public static String xmlString(String locator,String xmlname)  {
		/*xml=www....          返回：www.... 
		 * xmluat=loginUrl     返回： xmluat 节点内容 退换  loginUrl节点中#。。。。#内容
		 * www.url.com         返回：  www.url.com
		 * */
		
		HashMap map = toMap(locator);
		String replace = getType(map);
		String replaceValss =null;
		String str=null;
		if(map.size()==0){
			return locator;
		}
		try {
			
			if(xmlname.equals("gome")){
				 replaceValss =Variable.gomexml.getElementsByTagName(replace).getLength()==0? "": Variable.httpxml.getElementsByTagName(replace).item(0).getTextContent();
				 str = Variable.gomexml.getElementsByTagName((String)map.get(replace)).getLength()==0? "":Variable.httpxml.getElementsByTagName((String)map.get(replace)).item(0).getTextContent();
			}else if(xmlname.equals("send")){
				 
				 replaceValss =Variable.send.getElementsByTagName(replace).getLength()==0? "": Variable.httpxml.getElementsByTagName(replace).item(0).getTextContent();
				 str = Variable.send.getElementsByTagName((String)map.get(replace)).getLength()==0? "":Variable.httpxml.getElementsByTagName((String)map.get(replace)).item(0).getTextContent();
			}else if(xmlname.equals("http")){
				 replaceValss =Variable.httpxml.getElementsByTagName(replace).getLength()==0? "": Variable.httpxml.getElementsByTagName(replace).item(0).getTextContent();
				 str = Variable.httpxml.getElementsByTagName((String)map.get(replace)).getLength()==0? "":Variable.httpxml.getElementsByTagName((String)map.get(replace)).item(0).getTextContent();
			}
			
			return replaceSting(str,replaceValss);
		} catch (Exception e) {
			if(str==null){
				return locator;
			}else{
				return str;
			}
		}
	
	}
	
	public static String sendXml(String locator){
		return xmlString(locator,"send");
	}
	
	public static String gomeXml(String locator){
		return xmlString(locator,"gome");
	}
	
	public static String httpXml(String locator){
		return xmlString(locator,"http");
	}
	

	/*
	 * httpXmls 可以获得 httpxml下节点多个属性
	 * @String locator 节点信息
	 * @return String  返回xml对应节点信息
	 */
	public static String httpXmls(String locator){
		if(locator.contains("xmls=")){
			String[] str;   //return 数据
			List<String> ls = new ArrayList();  //通过list获得
			for(String s : locator.substring(locator.indexOf("=")+1, locator.length()).split("@@")){
				ls.add(s);
			}
			return getNodeDate(Variable.httpxml.getChildNodes(),ls);
		}else
		{
			return httpXml(locator);
		}
	}
	/*通过List 获得xml数据
	 * @NodeList nodelist  xml NodeList
	 * @List  获得xml 节点路径
	 * return String 获得xml 多个节点数据  格式 k=v&。。。。。。
	 */
	public static String getNodeDate(NodeList nodelist,List<String> ls){
		String str="";
		if(ls.size()>1){
			for(int i = 0;i<nodelist.getLength();i++){
				if(nodelist.item(i).getNodeName().equals(ls.get(0))){
					ls.remove(0);
					Element e = (Element)nodelist.item(i);
					return getNodeDate(e.getElementsByTagName(ls.get(0)),ls);
				}
			}
			return "nullNodeList";   //未找到节点数据 返回为""
		}else{
			for(int i = 0;i<nodelist.getLength();i++){
				if(nodelist.item(i).getNodeName().equals(ls.get(0))){
					str =nodelist.item(i).getTextContent().length()>0?  str+nodelist.item(i).getTextContent()+"&":str;   //循环负值
				}
			}
			return str.replace(",", "=");  //返回单节点数据
		}
	}
	public static void main(String[] agrs) throws Exception{
		initElement(); 
		System.out.println("httpXmls++++"+httpXmls("xmls=login@@params"));
	}
}
