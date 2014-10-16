package com.test.bean;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.test.configure.Variable;
import com.test.tool.MyAuthenticator;
public class MailSenderBean {
	private String mailServerHost = Variable.mailServerHost;    
    private String mailServerPort = Variable.mailServerPort;    
    // �ʼ������ߵĵ�ַ    
    private Address[] fromAddress;    
    // �ʼ������ߵĵ�ַ    
    private Address[] toAddress;    
    // ��½�ʼ����ͷ��������û���������    
    private String userName=Variable.mailUserName;    
    private String password = Variable.mailpassword;    
    // �Ƿ���Ҫ�����֤    
    private boolean validate = Variable.mailValidate;    
    // �ʼ�����    
    private String subject = Variable.mailTatle;    
    // �ʼ����ı�����    
    private String content;    
    // �ʼ��������ļ���    
    private String[] attachFileNames;      
    /** *//**   
      * ����ʼ��Ự����   
      */    
    public Properties getProperties(){    
      Properties p = new Properties();    
      p.put("mail.smtp.host", this.mailServerHost);    
      p.put("mail.smtp.port", this.mailServerPort);    
      p.put("mail.smtp.auth", validate ? "true" : "false");    
      return p;    
    }  
    
    public Session getMailSession(Properties  properties,String userName,String password){
    	//Session mailsession=null;
    	if(userName!=null && userName.trim().length()>0 ){
    		properties.put("mail.smtp.auth", "true");
    		if(password.equals(null)) password = "";
    		 return Session.getInstance(properties, new MyAuthenticator(userName,password));
    	}else{
    		return Session.getInstance(properties);
    	}
    }
    public String getMailServerHost() {    
      return mailServerHost;    
    }    
    public void setMailServerHost(String mailServerHost) {    
      this.mailServerHost = mailServerHost;    
    }   
    public String getMailServerPort() {    
      return mailServerPort;    
    }   
    public void setMailServerPort(String mailServerPort) {    
      this.mailServerPort = mailServerPort;    
    }   
    public boolean isValidate() {    
      return validate;    
    }   
    public void setValidate(boolean validate) {    
      this.validate = validate;    
    }   
    public String[] getAttachFileNames() {    
      return attachFileNames;    
    }   
    public void setAttachFileNames(String[] fileNames) {    
      this.attachFileNames = fileNames;    
    }   
    public Address[] getFromAddress() {   
    	
      return this.fromAddress;    
    }    
    public void setFromAddress(String fromAddress) throws AddressException {  
      
    	String[] from = fromAddress.trim().split(",");
    	Address[] fromAdresses = new InternetAddress[from.length];
    	for (int i=0;i<from.length;i++){
    		fromAdresses[i]=new InternetAddress(from[i]);
    	}
    	System.out.println("ִ��++++++"+fromAdresses.length);
    	this.fromAddress =fromAdresses;  
    	
      //this.fromAddress = from;    
    }   
    public String getPassword() {    
      return password;    
    }   
    public void setPassword(String password) {    
      this.password = password;    
    }   
    public Address[] getToAddress() {    
      return toAddress;    
    }    
    public void setToAddress(String toAddress) throws AddressException { 
    	String[] cc = toAddress.trim().split(",");
    	Address[] ccAdresses = new InternetAddress[cc.length];
    	for (int i=0;i<cc.length;i++){
    		ccAdresses[i]=new InternetAddress(cc[i]);
    	}
    	this.toAddress = ccAdresses;
    }    
    public String getUserName() {    
      return userName;    
    }   
    public void setUserName(String userName) {    
      this.userName = userName;    
    }   
    public String getSubject() {    
      return subject;    
    }   
    public void setSubject(String subject) {    
      this.subject = subject;    
    }   
    public String getContent() {    
      return content;    
    }   
    public void setContent(String textContent) {    
      this.content = textContent;    
    }    

}
