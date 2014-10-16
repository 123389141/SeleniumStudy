package com.test.control;

   
import java.util.Date;


import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.w3c.dom.Element;

import com.test.bean.MailSenderBean;
import com.test.configure.Variable;
import com.test.tool.Templet;
import com.test.tool.TestTool;
public class SimpleMailSender {
	
	
	
	public MailSenderBean getMailSenderBean() throws Exception{
		
		MailSenderBean msb = new MailSenderBean();
		Element elenent = Variable.send;
		msb.setToAddress(elenent.getElementsByTagName("fromAddress").item(0).getTextContent());
		msb.setFromAddress(elenent.getElementsByTagName("toAddress").item(0).getTextContent());
		
		msb.setContent(Templet.mailTemplet());
		//msb.setFromAddress(elenent.getAttributes().getNamedItem("fromAddress").getTextContent().split(","));elenent.getAttributes().getNamedItem("").getTextContent().split(",");
		//msb.setFromAddress(elenent.getAttributes().getNamedItem("toAddress").getTextContent().split(","));elenent.getAttributes().getNamedItem("").getTextContent().split(",");
	   // msb.setContent("Test");
		return msb;
	}
	
	public Multipart  getMultipart(String content) throws MessagingException{
		Multipart  mailMultipart=new MimeMultipart();
		// ����һ������HTML���ݵ�MimeBodyPart    
	    BodyPart html = new MimeBodyPart(); 
	    html.setContent(content, "text/html; charset=utf-8");
	    mailMultipart.addBodyPart(html);  
	    return mailMultipart;
	}
	
	
	public void sendEMail(MailSenderBean msb) throws AddressException, MessagingException{
		//���� properties(��������˷����ʼ��������ĵ�ַ)
		Session mailSession = msb.getMailSession(msb.getProperties(), "", "");
		//����message(�������ʼ��ڶ��еĲ����Ķ���)
        MimeMessage message = new MimeMessage(mailSession);
        //���÷����� 
        message.setFrom(new InternetAddress(msb.getUserName()));
      //���÷����� �ռ��� 
        message.setRecipients(Message.RecipientType.TO,msb.getFromAddress());
       //���÷����� cc 
        if(msb.getToAddress()!=null){
        	message.setRecipients(Message.RecipientType.CC,msb.getToAddress());
        }
        //�����ʼ�����
        message.setSubject(msb.getSubject());
        message.setSentDate(new Date());
        message.setContent(this.getMultipart(msb.getContent())) ;
        Transport.send(message);
	}


}
