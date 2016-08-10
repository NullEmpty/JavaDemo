package com.carl.java;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.carl.java.excel.Entry;
import com.carl.java.excel.ExcelUtil;
import com.carl.java.excel.XmlUtil;

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
		testExcelWrite();
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
		List<Entry> list = XmlUtil.readXml("C:\\Users\\pcxu\\Desktop\\excel_test\\Picture.xml");
		try {
			ExcelUtil.writeExcel("C:\\Users\\pcxu\\Desktop\\excel_test\\Picture.xlsx", list);
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
	
	private static void testXmlWrite() {
		List<Entry> list = XmlUtil.readXml("C:\\Users\\pcxu\\Desktop\\excel_test\\strings.xml");
		String path = "C:\\Users\\pcxu\\Desktop\\excel_test\\sax.xml";
		XmlUtil.writeXml(list, path);
	}

}
