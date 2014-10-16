package com.test.configure;

import org.w3c.dom.Element;



public class Variable {
	
	 public static int wait=5;
	 public static int present=15;
	 public static int refresh=2;
	 
	 public static String[] flag={"Pass","Fail","Error"}; //case 结果
	 public static String[] sresult={"正常","失败","异常"};//错误信息描述
	 
	 public static String cassxmlpath="\\src\\com\\test\\testcassxml\\";//默认xml path
	 public static String totalCase="";           //全部用例名称
	 public static String errorCase="";            //保存error名称
	 public static String failCase="";			   //保存失败用例名称
	 public static String passCase="";            //保存通过用例名称
	 public static String testCassFlag;      //判断用例执行状态
	 public static String runlogpath = "\\src\\com\\test\\logs\\";
	 public static String sendPath = "\\src\\com\\test\\conf\\";
	 public static String mailTatle = "Gome自动化测试报告";   //邮件标题
	 public static String mailServerHost = "mail.yolo24.com"; //邮件 服务器
	 public static String mailServerPort = "25";  //端口
	 public static boolean mailValidate = false;
	 public static String mailUserName="liujinhu@yolo24.com";  //邮件发送 人
	 public static String mailpassword;//邮件发送 人邮箱密码
	 public static String startTime; //记录开始时间
	 public static String endTime; //记录执行结束时间
	 
	 public static Element gomexml=null;          
	 public static Element send=null;
	 public static Element httpxml=null;
	
	 
}
