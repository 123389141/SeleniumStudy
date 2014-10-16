package com.test.bean;

import com.test.configure.Variable;
import com.test.bean.TestMethodBean;

public class TestCaseBean {
	public  void setTestcaseId(String testcaseId){
		this.testcaseId = testcaseId;
	}
	
	public  void setTestcaseTpye(String testcaseTpye){
		this.testcaseTpye = testcaseTpye;
	}
	
	public void setTestcaseName(String testcaseName){
		this.testcaseName = testcaseName;
	}

	public String getTestcaseId(){
		return this.testcaseId ;
	}
	
	public String getTestcaseTpye(){
		return this.testcaseTpye;
	}
	
	public String getTestcaseName(){
		return this.testcaseName;
	}
	
	public String getflag(){
		return this.flag;
	}
	
	public void  setflag(String flag){
		 this.flag=flag;
	}
	
	
	public void setMethodBean(TestMethodBean tmb){
		this.tmb = tmb;
	}
	
	public TestMethodBean getMethodBean(){
		return this.tmb;
	}
	
	public String getStepName(){
		return tmb.getStepName();
	}
	
	public String getStepClass(){
		return tmb.getStepClass();
	}
	
	public String getStepValses(){
		return tmb.getStepValses();
	}
	
	public String getStepFlag(){
		//String flag = tmb.getStepflag().equals(null)? "":tmb.getStepflag();
		return tmb.getStepflag();
	}
	
	public String getStepText(){
		return tmb.getText();
	}
	public String getStepRemarks(){
		return tmb.getRemarks();
	}

	public String getText(){
		return tmb.getText();
	}
	
	private  String testcaseId;                //xml 获得 testcaseid
	private  String testcaseTpye;			   //xml 获得 testcaseTpye
	private  String testcaseName;			   //xml 
	private  String flag = Variable.flag[0];    //默认成功      
	//private  String Remarks;                   //错误描述
	private TestMethodBean tmb;
	
}
