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
				  System.out.println("+��������TestcaseName:"+tcb.getTestcaseName()+"+�����ɹ���־:"+tcb.getflag()+"+++++++����ɹ���־Flag:"+tcb.getStepFlag()+"++++++����ִ�з���StepName:"+tcb.getStepName()+""+
				  "+++++����ִ��Class:"+tcb.getStepClass()+"+++��������ֵ:"+tcb.getStepText()+"+++����������"+tcb.getStepRemarks());
		  }
		  System.out.println("+++��ִ�У�"+Variable.totalCase.split(",").length+"+++�ɹ�����:"+Variable.passCase.split(",").length+""+"+++ʧ��������"+Variable.failCase.split(",").length+
				  "+++Error������"+Variable.errorCase.split(",").length+"+passCase+"+Variable.passCase+"++errorCase+++"+Variable.errorCase+"+failCase+++"+Variable.failCase); 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }

}
