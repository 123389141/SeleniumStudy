package com.test.action;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.bean.TestCaseBean;
import com.test.bean.TestMethodBean;
import com.test.configure.Variable;
//import com.test.control.GetContent;
//import com.test.control.SimpleMailSender;
import com.test.tool.TestTool;
//import com.testing.Test;




public class CusAction extends SeleniumAction implements IAction{
	WebDriver webdr;
	public String[] login(Object obj) throws Exception{
		String[] locator = (String[])obj;
		webdr = this.getWebDriver();
		String[] aaa =  locator[0].split(",");
		webdr.get(TestTool.gomeXml(aaa[0]));
		webdr.findElement(this.setBy("xml=login-loginName")).sendKeys(aaa[1]);
		webdr.findElement(this.setBy("xml=gin-loginPassword")).sendKeys(aaa[2]);
		
		return this.str;
	} 
	
}
