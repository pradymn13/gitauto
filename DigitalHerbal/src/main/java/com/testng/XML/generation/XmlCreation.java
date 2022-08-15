package com.testng.XML.generation;

import java.io.FileWriter;
import java.util.ArrayList;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.generic.Actions.Excel;
import com.generic.Actions.GenericFunction;

public class XmlCreation {

	public static void main(String[] arg) throws Exception {
		creationMethod();
	}

	public static void creationMethod() throws Exception
	{
	    Excel ex= new Excel();
	    
	    String xlPath = GenericFunction.getProperty("xlPath");
	    
	    String[][] XMLGeneration = ex.xlReadSheet(xlPath,"XMLGeneration");
	    
	    System.out.println("11" + XMLGeneration);
	    
	    for(int xmlS=0; xmlS<XMLGeneration.length;xmlS++)//XMLGeneration for loop
	    {
	        if(XMLGeneration[xmlS][1].equalsIgnoreCase("Yes"))
	        {
	            String SuitName= XMLGeneration[xmlS][0];
	            String[][] suitSheet= ex.xlReadSheet(xlPath,SuitName);
	            
	            XmlSuite suit = new XmlSuite();
	            suit.setName(SuitName);
	            suit.addListener("con.testNGListeners.TestListeners");
	            
	            ArrayList<XmlTest> list_test_XmlTest = new ArrayList<XmlTest>(); // to add all tests to suit
	            for(int c =1;c <suitSheet.length ; c++ )//XMLGeneration Iterator for Classes &Test
	            {
	                XmlTest test = new XmlTest(suit);
	                
	                ArrayList<XmlClass> list_classes_XmlClass = new ArrayList<XmlClass>();
	                
	                // to add all classess and then to attach as child for
	                //test.setClasses(classes_XmlClass);
	                XmlClass classes = new XmlClass();
	                
	                // if(suitSheet[c][1].equalsIgnoreCase("Yes")) // xmlGernerationSheet
	                //{
	                
	                String TestName= suitSheet[c][0];
	                System.out.println("11stse" + TestName);
	                String classPath = suitSheet[c][1];
	                System.out.println("1st" + classPath);
	                String exec = suitSheet[c][2];
	                if(exec.equalsIgnoreCase("Yes"))
	                {
	                    test.setName(TestName);
	                    test.setVerbose(2);
	                    
	                    classes.setName(classPath);
	                    System.out.println(TestName);
	                    String[][] methods_sheet = ex.xlReadSheet(xlPath, TestName);
	                    
	                    ArrayList<XmlInclude> list_method_XmlInclude = new ArrayList<XmlInclude>();
	                    // to add all the methods for classess
	                    
	                    for(int m =1; m < methods_sheet.length; m++) // for method  adding
	                    {
	                        //if(methods_sheet[m][1]).equalsIgnoreCase("Yes")){
	                        // String methodNmae = methods_sheet[m][0];
	                        
	                        if(methods_sheet[m][2].equalsIgnoreCase("Yes"))
	                        {
	                             String methodName = methods_sheet[m][1];
	                             
	                             XmlInclude includeMethod  = new  XmlInclude(methodName);//firstMethod is a Testcase
	                             
	                             //the below code is for to iterate the XmlInputs
	                             
	                             //for (int input = 3; input<methods_sheet[m].length; input++) {
	                             
	                             for(int input = 4; input<methods_sheet[m].length; input++)
	                             {
	                                 if(methods_sheet[m][input] != null && !(methods_sheet[m][input]==""))
	                                 {
	                                     System.out.println(methods_sheet[m][input]);
	                                     if(methods_sheet[m][input].split("=").length==2)
	                                     {
	                                         includeMethod.addParameter(methods_sheet[m][input].split("=")[0],methods_sheet[m][input].split("=")[1]);
	                            
	                                     }
	                                     else if (methods_sheet[m][input].split("=").length==1)
	                                     {
	                                         includeMethod.addParameter(methods_sheet[m][input].split("=")[0],"");
	                                     }
	                                     else {
	                                         includeMethod.addParameter(methods_sheet[m][input],"");
	                                     }
	                                 }
	                             }
	                             
	                             list_method_XmlInclude.add(includeMethod);
	                             
	                        }// m if
	                    }// m - for methods
	                    
	                    classes.setIncludedMethods(list_method_XmlInclude);
	                    list_classes_XmlClass.add(classes);
	                    test.setClasses(list_classes_XmlClass);
	                    list_test_XmlTest.add(test);
	                }
	                
	                
	            }//c -- for class xmlGerneration and Test
	            
	            suit.setTests(list_test_XmlTest);
	            System.out.println(suit.toXml());
	            FileWriter fos = new FileWriter(new java.io.File(SuitName+".xml"));
	            fos.write(suit.toXml());
	            fos.close();
	        }
	    }// for loop XMLGeneration
	    
	}
}
