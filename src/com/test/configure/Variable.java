package com.test.configure;

import org.w3c.dom.Element;



public class Variable {
	
	 public static int wait=5;
	 public static int present=15;
	 public static int refresh=2;
	 
	 public static String[] flag={"Pass","Fail","Error"}; //case ���
	 public static String[] sresult={"����","ʧ��","�쳣"};//������Ϣ����
	 
	 public static String cassxmlpath="\\src\\com\\test\\testcassxml\\";//Ĭ��xml path
	 public static String totalCase="";           //ȫ����������
	 public static String errorCase="";            //����error����
	 public static String failCase="";			   //����ʧ����������
	 public static String passCase="";            //����ͨ����������
	 public static String testCassFlag;      //�ж�����ִ��״̬
	 public static String runlogpath = "\\src\\com\\test\\logs\\";
	 public static String sendPath = "\\src\\com\\test\\conf\\";
	 public static String mailTatle = "Gome�Զ������Ա���";   //�ʼ�����
	 public static String mailServerHost = "mail.yolo24.com"; //�ʼ� ������
	 public static String mailServerPort = "25";  //�˿�
	 public static boolean mailValidate = false;
	 public static String mailUserName="liujinhu@yolo24.com";  //�ʼ����� ��
	 public static String mailpassword;//�ʼ����� ����������
	 public static String startTime; //��¼��ʼʱ��
	 public static String endTime; //��¼ִ�н���ʱ��
	 
	 public static Element gomexml=null;          
	 public static Element send=null;
	 public static Element httpxml=null;
	
	 
}
