package com.test.control;


import java.io.IOException;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import com.test.configure.Variable;
import com.test.tool.Templet;
import com.test.tool.TestTool;

public class GetContent {
	private static final String WEBSERVICE_URL = "http://10.58.24.126:801/QgService.asmx";
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

	/**
	 * @param args
	 * @return 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public static void SendPhoneMsg(String[] phones,String results) {
		try{
			if(phones[0].length()>0){
				for(String phone : phones){
					GetContent.sendMsg(phone, results);
				}
			}else{
				TestTool.writeLog("短信提示:"+TestTool.getDate()+"=>电话号码为空");  
			}
			

		}catch(Exception e){
			TestTool.writeLog("短信提示:"+TestTool.getDate()+"=>抛出异常");  
			System.out.println(e);
		}
	}
	/*
	 * 得到电话号码
	 */

	private static boolean sendMsg(String phone, String content)
			throws IOException, SAXException, ParserConfigurationException {
		String soap = getSoapRequest(phone, content);
		if (soap == null) {
			return false;
		}
		URL url = new URL(WEBSERVICE_URL);
		URLConnection conn = url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Length",Integer.toString(soap.length()));
		conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		conn.setRequestProperty("SOAPAction", "http://tempuri.org/SendSaleSMS");
		OutputStream os = conn.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
		osw.write(soap);
		osw.flush();
		osw.close();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse(conn.getInputStream());
		NodeList nodeList = document.getElementsByTagName("SendSaleSMSResponse");
		int length = nodeList.getLength();
		String result = "";
		for (int i = 0; i < length; i++) {
			Element elt = (Element) nodeList.item(i);

			Node eltName = elt.getElementsByTagName("SendSaleSMSResult").item(0);
			result = eltName.getFirstChild().getNodeValue();
		}
		result = result.equals("0") ? "fail" : "success";
		System.out.println("start send==========");
		System.out.println("phone:" + phone);
		System.out.println("content:" + content);
		System.out.println("result:" + result);
		System.out.println("end send=====");
		return result.equals("0") ? false : true;
	}

	private static String getSoapRequest(String phone, String content) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
				+ "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soap:Body>    <SendSaleSMS xmlns=\"http://tempuri.org/\">"
				+ "<phone>" + phone + "</phone>" + "<context>" + content
				+ "</context>" + "<priority>" + 5 + "</priority>"
				+ " </SendSaleSMS>" + "</soap:Body></soap:Envelope>");
		return sb.toString();
	}
	

	
	public static String getPhones() throws Exception{
		return Variable.send.getElementsByTagName("phones").item(0).getTextContent();
	}
	
	public static void SendPhones() throws Exception{
			SendPhoneMsg(getPhones().split(","),Templet.getPhonesRequst());
	}
//	public static void main(String ags[]){
//		try {
//			
//			System.out.println(new SeleniumAction().setBy("xml=login-user"));
//		} catch (DOMException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
