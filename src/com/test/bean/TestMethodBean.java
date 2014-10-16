package com.test.bean;

import com.test.configure.Variable;

public class TestMethodBean {
	
	public void setStepName(String stepName){
		this.stepName = stepName;
	}
	
	public String getStepName(){
		return this.stepName;
	}
	
	public void setStepClass(String stepClass){
		this.stepClass = stepClass;
	}
	
	public String getStepClass(){
		return this.stepClass;
	}

	public void setStepValses(String stepValses){
		this.stepValses = stepValses;
	}
	
	public String getStepValses(){
		return this.stepValses;
	}

	public String getStepflag(){
		return this.stepflag;
	}
	
	public void setStepFlag(String stepflag){
		this.stepflag = stepflag;
	}
	
	
	
	public void setRemarks(String Remarks){
		this.Remarks = Remarks;
	}
	
	public String getRemarks(){
		return this.Remarks;
	}
	
	public void setText(String steptext){
		this.steptext = steptext;
	}
	
	public String getText(){
		return this.steptext;
	}
	
	
	
	private  String stepName;			   //xml methodname
	private  String stepClass;			   //xml class
	private  String stepValses;		   //xml values
	private  String stepflag = Variable.flag[0]; //xml isFalg
	private  String steptext;              //xml 取方法节点 test
	private  String Remarks;               //错误描述
}
