package com.test.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.w3c.dom.DOMException;
import com.test.tool.TestTool;
import com.test.bean.TestCaseBean;
import com.test.bean.TestMethodBean;
import com.test.configure.Variable;
import com.test.control.GetContent;
import com.test.control.SimpleMailSender;


public class SeleniumAction {
	
	protected static WebDriver dr;
	private static WebElement element;
	private static By by;
	public String[] str ={Variable.flag[0],Variable.sresult[0]}; 
	
	
/*ͨ�����Բ��ҵ���Ӧ��By
 * ֧��id��xpath��name��className
 * xml��ȡGomeXml.xml�ڵ������һ����ֵ
 * */
	
	public  static By setBy (String locator) throws Exception{
		TestTool tool =new TestTool();
		String type;
		HashMap hmap = TestTool.toMap(locator);
		type = TestTool.getType(hmap);
		if(dr!=null){
			dr.manage().timeouts().implicitlyWait(Variable.wait, TimeUnit.SECONDS);
		}
		if (type.equals("id")){
			return By.id((String) hmap.get(type));
		} else if (type.equals("xpath")){
			return By.xpath((String) hmap.get(type));
		}else if (type.equals("name")){
			return By.name((String) hmap.get(type));
		}else if (type.equals("className")){
			return By.className((String) hmap.get(type));
		}else if (type.contains("xml")){       //���xml�µ�����
			String str = TestTool.gomeXml(locator);
			return setBy(str);
		}else if(type.equals("cssSelector")){
			return By.cssSelector((String) hmap.get(type));
		}else if(type.equals("tagName")){
			return By.cssSelector((String) hmap.get(type));
		}
		return by;
	}
	
	
	public WebDriver getWebDriver(){
		return SeleniumAction.dr;
	}
	
	
	public void setWebDriver(WebDriver webdriver) throws Exception{
		 SeleniumAction.dr=webdriver;
	}
	
	/*���xml�ڵ�text
	 * xml=A ����GomeXml.xml ��A�ڵ� text
	 *	 * */
	
	public static WebElement getElement(String locator) throws Exception{
		element = dr.findElement(setBy(TestTool.gomeXml(locator)));
		return element;
	}
	

	
	public String[]  initDriver(Object obj)throws Exception{
		String[] driver = (String[])obj;
			if(TestTool.gomeXml(driver[0]).equals("ff")){
				dr = new FirefoxDriver();
			}else if(TestTool.gomeXml(driver[0]).equals("HtmlUnitDriver")){
				dr =new HtmlUnitDriver();
			}
			dr.manage().window().maximize(); 
			return str;
	}
	
	
	
	public String[]  open(Object obj){
		String[] locator = (String[])obj;
		dr.get(TestTool.gomeXml(locator[0]));
		return str;
	} 
	
	public String[] set(Object obj) throws Exception{
		String[] locator = (String[])obj;
		this.getElement(TestTool.gomeXml(locator[0])).sendKeys(TestTool.gomeXml(locator[1]));
		return str;
	} 
	
	
	
    public boolean isEnbleElement(String locator){
    	boolean flag = false;
    	try{
    		flag = SeleniumAction.getElement(locator).equals(null)? false:true;
    		return flag;
    	}catch(Exception e){
    		return flag;
    	}
    }
    
    public boolean isVisible(String locator) {   
        try {
			return SeleniumAction.getElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}   
     }
    
    public static TestMethodBean repustTestMethodBean(TestMethodBean tmb,int flag,int sresult){
    	tmb.setStepFlag(Variable.flag[flag]);
    	tmb.setRemarks(Variable.sresult[sresult]);
    	return tmb;
    }
    /*�����ʼ�
	 * 
	 * 
	 * */
	public String[] caseSendMail(Object tmb) throws Exception{
			SimpleMailSender sm = new SimpleMailSender();
			sm.sendEMail(sm.getMailSenderBean());
			return str;
	}
	/*���Ͷ���
	 * */
	public String[] caseSendPhones(Object tmb) throws Exception{
			GetContent.SendPhones();
			return str;
	}

	public static void main(String args[])  throws Exception{
		TestTool.initElement();
		SeleniumAction sa = new SeleniumAction();
		String[] str = {"ff"};
		String[] str2 = {"xmluat=loginUrl,aa,aaa","11"};
		String[] str1 = {"id=kw","java"};
		sa.initDriver(str);
		sa.open(str2);
		sa.set(str1);

	}
}
