package com.carl.java.excel.config;
/**
 * @author: Peichen Xu
 * @since: 2016-8-11
 */
public class ConfigUtil {
	
	public static String PATH_CONFIG_XML2XLSX;// = "C:\\Users\\pcxu\\Desktop\\excel_t2\\XML2XLSX.config";
	public static String PATH_CONFIG_XLSX2XML;// = "C:\\Users\\pcxu\\Desktop\\excel_t2\\XLSX2XML.config";
	public static String PATH_CONFIG_XLSX_UPDATE_XML;// = "C:\\Users\\pcxu\\Desktop\\excel_t2\\XLSX_UPDATE_XML.config";
	private static String sCurDir;
	
	static {
		sCurDir = System.getProperty("user.dir");
		PATH_CONFIG_XML2XLSX = sCurDir + "\\config\\XML2XLSX.config"; 
		PATH_CONFIG_XLSX2XML = sCurDir + "\\config\\XLSX2XML.config";
		PATH_CONFIG_XLSX_UPDATE_XML = sCurDir + "\\config\\XLSX_UPDATE_XML.config";
	}
	
	public static String getCurDir() {
		return sCurDir;
	}

}
