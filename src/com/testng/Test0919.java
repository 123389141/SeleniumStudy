package com.testng;

import java.lang.reflect.Method;

import com.test.action.CusAction;
import com.test.action.IAction;
import com.test.bean.TestMethodBean;
import com.test.tool.TestTool;


/*
 * 自动化测试 20140919 培训
 */



public class Test0919 {

	
	
	public static void getClassName(String[] className) throws ClassNotFoundException{
		
	
		for (String name : className){
		 	Class c = Class.forName(name); 
	         Method m[] = c.getDeclaredMethods(); 
	         System.out.println("++++++++++++++++++++开始循环=>"+name+"+++++++++++");
		         for (int i = 0; i < m.length; i++){
		             System.out.println(m[i].toString()+"\n"); 
		         }
		     System.out.println("++++++++++++++++++++结束循环=>"+name+"+++++++++++"+"\n"+"\n"+"\n"+"\n");   
	 	}
	 }
	
	 	public static void main(String ags[]) throws ClassNotFoundException{
		
	 	String[] className = {"com.test.tool.TestTool","com.test.tool.MyAuthenticator","com.test.tool.Templet"
	 			,"com.test.control.Control","com.test.control.GetContent","com.test.control.SimpleMailSender"
	 			,"com.test.action.CusAction","com.test.action.SeleniumAction","com.test.action.IAction"
	 			,"com.test.bean.MailSenderBean","com.test.bean.TestCaseBean","com.test.bean.TestMethodBean"};
	 	
	 	getClassName(className);
	 
	 	}
}
