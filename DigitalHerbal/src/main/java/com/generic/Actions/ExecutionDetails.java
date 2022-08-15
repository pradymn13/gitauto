package com.generic.Actions;

public class ExecutionDetails {
	
	public static Excel exl=new Excel();
	/*
	 * To get the Instances from Main sheet based on Yes
	 */
	public static String getInstances() throws Exception{
		return getDetailsMain("Instances");
	}
	public static String getUrl() throws Exception{
		return getDetailsMain("Url");
	}
	public static String getUrl(String className) throws Exception{
		return getDetailsMain("Url",className);
	}
	public static String getUrl2() throws Exception{
		return getDetailsMain("Url2");
	}
	
	/*
	 * To get the Instances from Main sheet based on Yes and input parameter
	 */
	public static String getDetailsMain(String column)throws Exception{
		String Instances_Execution[] = exl.xlReadColumn(ConstantValueProj.xlpath, "Main", "Instances_Execution");
		String returnDetails=null;
		for(int i=0;i< Instances_Execution.length;i++) {
			if(Instances_Execution[i].equalsIgnoreCase("Yes")) {
				switch(column) {
				case "Instances":
					returnDetails=exl.xlReadCell(ConstantValueProj.xlpath, "Main", "Instances", i+1);
					break;
				case "Url":
					returnDetails=exl.xlReadCell(ConstantValueProj.xlpath, "Main", "Url", i+1);
					break;
				case "Url2":
					returnDetails=exl.xlReadCell(ConstantValueProj.xlpath, "Main", "Url2", i+1);
					break;
				case "UserName":
					returnDetails=exl.xlReadCell(ConstantValueProj.xlpath, "Main", "UserName", i+1);
					break;
				case "Password":
					returnDetails=exl.xlReadCell(ConstantValueProj.xlpath, "Main", "Password", i+1);
					break;
				default:
					returnDetails="no condition Found";
					break;
				}
				break;
			}else {
				returnDetails="please keep Yes in main sheet to confirm instances";
			}
		}
		return returnDetails;
	  	
	}
	public static String getDetailsMain(String column,String class_Name)throws Exception{
		String Instances_Execution[] = exl.xlReadColumn(ConstantValueProj.xlpath, "Main", "Instances_Execution");
		String Class_Name[]=exl.xlReadColumn(ConstantValueProj.xlpath, "Main", "Class_Name");
		String returnDetails=null;
		for(int i=0;i< Instances_Execution.length;i++) {
			if(Instances_Execution[i].equalsIgnoreCase("Yes") && class_Name.equalsIgnoreCase(Class_Name[i]) ) {
				switch(column) {
				case "Instances":
					returnDetails=exl.xlReadCell(ConstantValueProj.xlpath, "Main", "Instances", i+1);
					break;
				case "Url":
					returnDetails=exl.xlReadCell(ConstantValueProj.xlpath, "Main", "Url", i+1);
					break;
				case "Url2":
					returnDetails=exl.xlReadCell(ConstantValueProj.xlpath, "Main", "Url2", i+1);
					break;
				case "UserName":
					returnDetails=exl.xlReadCell(ConstantValueProj.xlpath, "Main", "UserName", i+1);
					break;
				case "Password":
					returnDetails=exl.xlReadCell(ConstantValueProj.xlpath, "Main", "Password", i+1);
					break;
				default:
					returnDetails="no condition Found";
					break;
				}
				break;
			}else {
				returnDetails="please keep Yes in main sheet to confirm instances";
			}
		}
		return returnDetails;
	  	
	}
	public static void main(String[] args) throws Exception{
		System.out.println(System.currentTimeMillis());
		System.out.println(System.currentTimeMillis());
	}
}
