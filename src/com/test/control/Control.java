package com.test.control;

import java.lang.reflect.Method;
import java.util.ArrayList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.test.action.CusAction;
import com.test.action.IAction;
import com.test.action.*;
import com.test.bean.TestCaseBean;
import com.test.bean.TestMethodBean;
import com.test.configure.Variable;
import com.test.tool.TestTool;


public class Control {

	public ArrayList  runxml(String path,String fileName ) throws Exception{
		Variable.startTime = TestTool.getDate();
		TestTool.initElement();
		NodeList nodelist = TestTool.getElement(path,fileName).getElementsByTagName("testcase");
        return listAllNodes(nodelist);
	}

	public  ArrayList listAllNodes(NodeList nodelist) throws Exception{
		ArrayList reportlist = new ArrayList(); 
		for (int i = 0;i<nodelist.getLength();i++){
        	Node node = nodelist.item(i);
        	if(node.getNodeName().equals("testcase")){
        		if (node.hasChildNodes()){
        			Variable.testCassFlag = Variable.flag[0];  //用例执行结果默认值
        			TestCaseBean tcb = new TestCaseBean();
        			listAllNodeTypes(node,tcb);
        			TestTool.writeLog("+++++++++++++++++"+TestTool.getDate()+"=>TestCase-Start->"+tcb.getTestcaseName()); //记录用例结束 
        			NodeList teststep = node.getChildNodes();
        			for(int j= 0;j<teststep.getLength();j++){
        				//测试用例 关联 测试步骤
        				TestCaseBean tcb1 = new TestCaseBean();
        				tcb1.setTestcaseTpye(tcb.getTestcaseTpye());
        				tcb1.setTestcaseId(tcb.getTestcaseId());
        				tcb1.setTestcaseName(tcb.getTestcaseName());
        				TestMethodBean tmb = new TestMethodBean();
        				if(teststep.item(j).getNodeName().equals("method")){
        					tcb1.setMethodBean(ReflectionTest(listAllNodeTypes(teststep.item(j),tmb)));
        					Variable.testCassFlag=TestTool.isFlag(Variable.testCassFlag,tcb1);          //判断用例状态
        					reportlist.add(tcb1);
        					TestTool.writeLog(TestTool.getDate()+"=>Method:"+tcb1.getStepName()+"===成功标志位:"+tcb1.getStepFlag()); 
        				}
        				if(j==teststep.getLength()-1){     //判断用例执行到最后一条
    						CaseResult(tcb1.getTestcaseName(),Variable.testCassFlag);
    						TestTool.writeLog("+++++++++++++++++"+TestTool.getDate()+"=>TestCase-End+"+tcb.getTestcaseName());    //记录用例结束 
    					}
        			}
        		}
        	}
        }
		return reportlist;
	}
	public  void listAllNodeTypes(Node node,TestCaseBean tcb){
		  if(node.getNodeType() == Node.ELEMENT_NODE){
		   //遍历当前元素节点的属性
		   if(node.hasAttributes()){
		    NamedNodeMap attrs = node.getAttributes();
		    for(int i=0; i<attrs.getLength(); i++){
			     Node attrNode = attrs.item(i);
			     if(attrNode.getNodeName().equals("id")){
			    	 tcb.setTestcaseId(attrNode.getNodeValue());
			     }else if (attrNode.getNodeName().equals("name")){
			    	 tcb.setTestcaseName(attrNode.getNodeValue());
			     }else if(attrNode.getNodeName().equals("tpye")){
			    	 tcb.setTestcaseTpye(attrNode.getNodeValue());
			     }
		    	}
		   }
		  }  
	}
	
	public  TestMethodBean listAllNodeTypes(Node node,TestMethodBean tmb){
		  if(node.getNodeType() == Node.ELEMENT_NODE){
		   //遍历当前元素节点的属性
		   if(node.hasAttributes()){
		    NamedNodeMap attrs = node.getAttributes();
		    for(int i=0; i<attrs.getLength(); i++){
		    	Node attrNode = attrs.item(i);
		    	String steptest=node.getNodeName().equals("method")? node.getTextContent():"";
		    	tmb.setText(steptest);
		    	if (attrNode.getNodeName().equals("methodname")){
			    	 tmb.setStepName(attrNode.getNodeValue());
			     }else if (attrNode.getNodeName().equals("class")){
			    	 tmb.setStepClass(attrNode.getNodeValue());
			     }else if (attrNode.getNodeName().equals("values")){
			    	 tmb.setStepValses(attrNode.getNodeValue());
			     }
		    	}
		   }
		  }
		  return tmb;
	}
	
	public TestMethodBean ReflectionTest(TestMethodBean tmb)  {
		//Class cls = null;
	  Method method;
	  IAction obj = null;
	//  SeleniumAction obj1 = null;
	  try{
		  if (tmb.getStepClass().equals("CusAction")){
			  obj = new CusAction();
		  }else if (tmb.getStepClass().equals("Test")){
			  obj = new Test();
		  }else if(tmb.getStepClass().equals("HttpAction")){
			  obj = new HttpAction();
		  }
		   Class<?> cls =obj.getClass();
		   String[] variable = {tmb.getStepValses(),tmb.getText()};
		   Object object =  variable;
		   method = cls.getMethod(tmb.getStepName(),Object.class);
		   String[] str  = (String[])method.invoke(obj,object);
		   tmb = TestTool.repustTestMethodBean(tmb,str);
		   return tmb;
	  }catch (Exception e){
		  e.printStackTrace();
		  //抛出反射异常
		  String[] str = {Variable.flag[2],Variable.sresult[2]};
		  tmb = TestTool.repustTestMethodBean(tmb,str);
		  return tmb;
	  }
	}

	public TestMethodBean  excCase(TestMethodBean tmb) throws Exception{		
		return ReflectionTest(tmb);
	}
	
	public void CaseResult(String caseNasme,String caseFlag){
		Variable.totalCase = Variable.totalCase.length()==0 ? caseNasme : Variable.totalCase+","+caseNasme;
		if(caseFlag.equals(Variable.flag[1])){
			Variable.failCase = Variable.failCase.length()==0 ? caseNasme : Variable.failCase+","+caseNasme;
		}else if(caseFlag.equals(Variable.flag[0])){
			Variable.passCase = Variable.passCase.length()==0 ? caseNasme : Variable.passCase+","+caseNasme;
		}else if(caseFlag.equals(Variable.flag[2])){
			Variable.errorCase = Variable.errorCase.length()==0 ? caseNasme : Variable.errorCase+","+caseNasme;
		}	
	}
}