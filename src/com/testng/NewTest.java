package com.testng;


import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.Test;
import java.util.ArrayList;
import com.test.configure.Variable;
import com.test.control.Control;
import com.test.bean.TestCaseBean;


public class NewTest {
  @Test
  public void f() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {	
	  
	  
	  
	  
	  Control con =new Control();
	 // ReportBean rb = new ReportBean();
	  //int i = 0;
	  try {
		//con.run("testcass.xml");
		  ArrayList arr = con.runxml(Variable.cassxmlpath,"testcass.xml");
		  // arr =  con.runxml("testcass.xml");
		  System.out.println(arr.size());
		  for(Object obj: arr){
				  TestCaseBean tcb  =  (TestCaseBean)obj;
				  System.out.println("+用例名称TestcaseName:"+tcb.getTestcaseName()+"+用例成功标志:"+tcb.getflag()+"+++++++步骤成功标志Flag:"+tcb.getStepFlag()+"++++++步骤执行方法StepName:"+tcb.getStepName()+""+
				  "+++++步骤执行Class:"+tcb.getStepClass()+"+++方法输入值:"+tcb.getStepText()+"+++方法描述："+tcb.getStepRemarks());
		  }
		  System.out.println("+++共执行："+Variable.totalCase.split(",").length+"+++成功用例:"+Variable.passCase.split(",").length+""+"+++失败用例数"+Variable.failCase.split(",").length+
				  "+++Error用例数"+Variable.errorCase.split(",").length+"+passCase+"+Variable.passCase+"++errorCase+++"+Variable.errorCase+"+failCase+++"+Variable.failCase); 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }

}
