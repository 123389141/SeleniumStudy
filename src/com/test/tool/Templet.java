package com.test.tool;

import com.test.configure.Variable;

public class Templet {

	static String mailHtml;
	public static String mailTemplet(){
		
		///头部+时间 
		mailHtml = "<table border=\"0\"  cellpadding=\"0\"  cellspacing=\"0\"   width=\"100%\"  height=\"100%\" style=\"background-color: #F6F6F6\" >";
		mailHtml=mailHtml+"<tbody><tr><td valign=\"top\">";
		mailHtml=mailHtml+"<table border=\"0\" cellpadding=\"0\"  cellspacing=\"0\" align=\"center\"   width=\"800\"  style=\"border-right: 1px solid #ccc; border-left: 1px solid #ccc;background-color:white\">";
		mailHtml=mailHtml+"<tbody>";
		mailHtml=mailHtml+"<tr>";
		mailHtml=mailHtml+"<td valign=\"top\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"   height=\"157\"  width=\"800\">";
		mailHtml=mailHtml+"</tr><tr>";
		mailHtml=mailHtml+"<td style=\"font-family: 楷体; font-size: 25px; padding: 0 20px\" colspan=\"2\" height=\"13\" valign=\"top\" width=\"800\"><strong>商品购物流程测试报告</strong></td></tr>";
		mailHtml=mailHtml+"<tr><td style=\"font-family: 楷体; font-size: 15px; padding: 0 20px; color: gray\" colspan=\"2\" height=\"13\" valign=\"top\" width=\"800\"><hr/>测试时间：<font style=\"font-family: Comic Sans MS\">#start_time</font><hr/><br/></td></tr>";
		mailHtml = mailHtml.replace("#start_time", Variable.startTime);
		mailHtml=mailHtml+"</tbody></table></td></tr><tbody>";
		int totalCase = Variable.totalCase ==""? 0 : Variable.totalCase.split(",").length;
		int errorCase = Variable.errorCase ==""? 0 : Variable.errorCase.split(",").length;
		int failCase = Variable.failCase ==""? 0 : Variable.failCase.split(",").length;
		int passCase = Variable.passCase ==""? 0 : Variable.passCase.split(",").length;
		//汇总统计
		mailHtml=mailHtml+"<tr>";
		mailHtml=mailHtml+"<td valign=\"top\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"   height=\"157\"  width=\"800\" >";
		mailHtml=mailHtml+"<tbody><tr><tr><td style=\"font-family: 楷体; font-size: 20px; padding: 0 20px\" colspan=\"4\" height=\"13\" valign=\"top\" width=\"800\"><strong>测试汇总：</strong><br/></td></tr>";
		mailHtml=mailHtml+"<tr><td style=\"font-family: 楷体; font-size: 16px; padding: 0 20px\" colspan=\"4\" height=\"13\" valign=\"top\" width=\"800\">共执行：<font style=\"font-family: Comic Sans MS\">#total</font><br/></td></tr>";
		mailHtml = mailHtml.replace("#total", String.valueOf(totalCase));
		mailHtml=mailHtml+"<tr><td style=\"font-family: 楷体; font-size: 16px; padding: 0 20px\" colspan=\"4\" height=\"13\" valign=\"top\" width=\"800\">执行成功：<font style=\"font-family: Comic Sans MS\">#pass</font><br/></td>";
		mailHtml = mailHtml.replace("#pass", String.valueOf(passCase));
		mailHtml=mailHtml+"<tr><td style=\"font-family: 楷体; font-size: 16px; padding: 0 20px\" colspan=\"4\" height=\"13\" valign=\"top\" width=\"800\">执行失败：<font style=\"font-family: Comic Sans MS\">#fail</font></td></tr>";
		mailHtml = mailHtml.replace("#fail", String.valueOf(failCase));
		mailHtml=mailHtml+"<tr><td style=\"font-family: 楷体; font-size: 16px; padding: 0 20px\" colspan=\"4\" height=\"13\" valign=\"top\" width=\"800\">执行异常：<font style=\"font-family: Comic Sans MS\">#error</font><br/><hr></td>";
		mailHtml = mailHtml.replace("#error", String.valueOf(errorCase));
		mailHtml=mailHtml+"</tr></tbody></td></tr>";
		
		//用例执行明细
		mailHtml=mailHtml+"<tr>";
		mailHtml=mailHtml+"<td valign=\"top\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"   height=\"157\"  width=\"800\" >";
		mailHtml=mailHtml+"<tbody>";
		mailHtml=mailHtml+"<tr><td style=\"font-family: 楷体; font-size: 20px; padding: 0 20px\" colspan=\"4\" height=\"13\" valign=\"top\" width=\"800\"><strong>测试用例明细：</strong><br/></td></tr>";
		for( String eCase:Variable.errorCase.split(",")){
			if(eCase.length()>0){
				mailHtml=mailHtml+"<tr><td style=\"font-family: 楷体; font-size: 16px; padding: 0 20px\" colspan=\"2\" height=\"13\" valign=\"top\" width=\"800\">#testcase<font style=\"font-family: Comic Sans MS\">#requst</font></td>";
				mailHtml = mailHtml.replace("#testcase", eCase+":");
				mailHtml = mailHtml.replace("#requst", "Error");
			}
		}
		for( String fCase:Variable.failCase.split(",")){
			if(fCase.length()>0){
				mailHtml=mailHtml+"<tr><td style=\"font-family: 楷体; font-size: 16px; padding: 0 20px\" colspan=\"2\" height=\"13\" valign=\"top\" width=\"800\">#testcase<font style=\"font-family: Comic Sans MS\">#requst</font></td>";
				mailHtml = mailHtml.replace("#testcase", fCase+":");
				mailHtml = mailHtml.replace("#requst", "Fail");
			}
		}
		
		for( String pCase:Variable.passCase.split(",")){
			if(pCase.length()>0){
				mailHtml=mailHtml+"<tr><td style=\"font-family: 楷体; font-size: 16px; padding: 0 20px\" colspan=\"2\" height=\"13\" valign=\"top\" width=\"800\">#testcase<font style=\"font-family: Comic Sans MS\">#requst</font></td>";
				mailHtml = mailHtml.replace("#testcase", pCase+":");
				mailHtml = mailHtml.replace("#requst", "Pass");
			}
		}
		mailHtml=mailHtml+"</tbody></td></tr>";
		
		
		//底部
		mailHtml=mailHtml+"<tr><td valign=\"top\"><br/><table  border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  width=\"800\" style=\"border-top: 1px solid #ccc\"><tr><td style=\"font-family: 楷体; font-size: 16px; padding: 0 20px; color: silver\" colspan=\"2\" height=\"13\" valign=\"top\" >";
		mailHtml=mailHtml+"<br/> 国美自动化测试：<br/>测试状态、测试机器、测试环境等信息可查看附件</td>	</tr>";
		
		return mailHtml;
	}
	


	public static String getPhonesRequst() {
		int totalCase = Variable.totalCase ==""? 0 : Variable.totalCase.split(",").length;
		int errorCase = Variable.errorCase ==""? 0 : Variable.errorCase.split(",").length;
		int failCase = Variable.failCase ==""? 0 : Variable.failCase.split(",").length;
		int passCase = Variable.passCase ==""? 0 : Variable.passCase.split(",").length;
		return "自动化用例执行完毕,明细如下=>共执行:"+totalCase+"条,成功:"+passCase+"条,失败:"+failCase+"条,Error:"+errorCase+"条";
	}


}
