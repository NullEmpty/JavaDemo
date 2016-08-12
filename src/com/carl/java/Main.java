package com.carl.java;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.carl.java.excel.Entry;
import com.carl.java.excel.ExcelUtil;
import com.carl.java.excel.XmlUtil;
import com.carl.java.excel.config.ConfigUtil;
import com.carl.java.excel.model.Xlsx2XmlModel;
import com.carl.java.excel.model.XlsxUpdateXmlModel;
import com.carl.java.excel.model.Xml2XlsxModel;

/**
 * @author: Peichen Xu
 * @since: 2016-8-10
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Xml2XlsxModel model = new Xml2XlsxModel();
//		model.exec(ConfigUtil.PATH_CONFIG_XML2XLSX);
		
//		Xlsx2XmlModel model = new Xlsx2XmlModel();
//		model.exec(ConfigUtil.PATH_CONFIG_XLSX2XML);
		
		XlsxUpdateXmlModel model = new XlsxUpdateXmlModel();
		model.exec(ConfigUtil.PATH_CONFIG_XLSX_UPDATE_XML);
		
	}
	
	private static void testExcelRead() {
		try {
			ExcelUtil.test("C:\\Users\\pcxu\\Desktop\\APP开发环境-作业考核情况.xlsx");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testExcelWrite() {
		List<Entry> list = XmlUtil.readXml("C:\\Users\\pcxu\\Desktop\\excel_test\\settings.xml");
		try {
			ExcelUtil.writeExcel("C:\\Users\\pcxu\\Desktop\\excel_test\\settings.xlsx", "settings_array", list, new String[]{"cn_rZH", "en"}, "cn_rZH");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testExcelArrayWrite() {
		List<Entry> list = XmlUtil.readXmlArray("C:\\Users\\pcxu\\Desktop\\excel_test\\arrays.xml");
		try {
			ExcelUtil.writeExcel("C:\\Users\\pcxu\\Desktop\\excel_test\\settings_array.xlsx", "settings_array", list, new String[]{"cn_rZH", "en"}, "cn_rZH");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testXmlRead() {
		List<Entry> list = XmlUtil.readXml("C:\\Users\\pcxu\\Desktop\\excel_test\\strings.xml");
		if (list != null) {
			System.out.println("======================================");
			for (Entry entry : list) {
				System.out.println(entry.toString());
			}
		}
	}
	
	private static void testXmlArrayRead() {
		List<Entry> list = XmlUtil.readXmlArray("C:\\Users\\pcxu\\Desktop\\excel_test\\arrays.xml");
		if (list != null) {
			System.out.println("======================================");
			for (Entry entry : list) {
				System.out.println(entry.toString());
			}
		}
	}
	
	private static void testXmlWrite() {
		List<Entry> list = XmlUtil.readXml("C:\\Users\\pcxu\\Desktop\\excel_test\\strings.xml");
		String path = "C:\\Users\\pcxu\\Desktop\\excel_test\\sax.xml";
		XmlUtil.writeXml(list, path);
	}

}
