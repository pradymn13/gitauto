package com.generic.Actions;

public class ConstantValueProj {
	public static String xlpath=GenericFunction.getProperty("xlPath");
	public static String reportpath=GenericFunction.getProperty("reportpath");
	public static String imagespath=GenericFunction.getProperty("imagespath");
	public static String browser=GenericFunction.getProperty("browser");
	public static String isGridrequired=GenericFunction.getProperty("isGridrequired");
	public static String hube_url=GenericFunction.getProperty("hube_url");
	public static String propertyFilePath="";
	public static String xmlpath=GenericFunction.getProperty("xmlpath");
	public static String isDataProvider=GenericFunction.getProperty("isDataProvider");
	
	public static void setpropertyFilePath(String propertyFilePath) {
		ConstantValueProj.propertyFilePath=propertyFilePath;
	}
	public static void setxlpath() {
		ConstantValueProj.xlpath=GenericFunction.getProperty("xlpath");
	}
	public static void setxmlpath() {
		ConstantValueProj.xlpath=GenericFunction.getProperty("xmlpath");
	}
	public static void setreportpath() {
		reportpath=GenericFunction.getProperty("reportpath");
	}
	public static void setimagespath() {
		reportpath=GenericFunction.getProperty("imagespath");
	}
	public static void setbroswer() {
		reportpath=GenericFunction.getProperty("browser");
	}
	public static void sethube_URL() {
		reportpath=GenericFunction.getProperty("hube_url");
	}
	public static void setisDataProvider() {
		xmlpath=GenericFunction.getProperty("isDataProvider");
	}
	

}
