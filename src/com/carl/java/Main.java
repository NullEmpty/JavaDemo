package com.carl.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.carl.java.excel.Entry;
import com.carl.java.excel.ExcelUtil;
import com.carl.java.excel.XmlUtil;
import com.carl.java.excel.config.ConfigUtil;
import com.carl.java.excel.model.BaseModel;
import com.carl.java.excel.model.Xlsx2XmlModel;
import com.carl.java.excel.model.XlsxUpdateXmlModel;
import com.carl.java.excel.model.Xml2XlsxModel;

/**
 * @author: Peichen Xu
 * @since: 2016-8-10
 */
public class Main {

	private static Map<Integer, String> MAP_MAIN = new HashMap<Integer, String>();
	private static Map<Integer, String> MAP_XML_XLSX = new HashMap<Integer, String>();
	static {
		MAP_MAIN.put(1, "xml and xlsx");
		MAP_MAIN.put(0, "quite");
		
		MAP_XML_XLSX.put(1, "xml2xlsx");
		MAP_XML_XLSX.put(2, "xlsx2xml");
		MAP_XML_XLSX.put(3, "xlsx update xml");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Xml2XlsxModel model = new Xml2XlsxModel();
		// model.exec(ConfigUtil.PATH_CONFIG_XML2XLSX);

		// Xlsx2XmlModel model = new Xlsx2XmlModel();
		// model.exec(ConfigUtil.PATH_CONFIG_XLSX2XML);

		// XlsxUpdateXmlModel model = new XlsxUpdateXmlModel();
		// model.exec(ConfigUtil.PATH_CONFIG_XLSX_UPDATE_XML);

		try {
			int main = select(MAP_MAIN);
			System.out.println("main select=" + main + MAP_MAIN.get(main));
			switch (main) {
			case 0:
				System.exit(0);
				break;

			case 1:
				selectXmlAndXlsx();
				break;

			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		test();
	}
	
	private static void test() {
		String pathXml = "C:\\Users\\pcxu\\Desktop\\excel_t2\\settings_2.xml";
		List<Entry> list = XmlUtil.readXml(pathXml);
		Map<String, Entry> map = Entry.formatList2Map(list);
		String pathXlsx = "C:\\Users\\pcxu\\Desktop\\excel_t2\\20160810_X2 中文UI语言整理.xlsx";
		try {
			ExcelUtil.updateExcel(pathXlsx, "settings", map, "英文");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static void selectXmlAndXlsx() throws IOException {
		int xml_xlsx = select(MAP_XML_XLSX);
		System.out.println("xml_xlsx select=" + xml_xlsx + MAP_XML_XLSX.get(xml_xlsx));
		switch (xml_xlsx) {
		case 0:
			System.exit(0);
			break;

		case 1:
			String configPath = ConfigUtil.PATH_CONFIG_XML2XLSX;
			System.out.println("start");
//			BaseModel model = BaseModel.newModel("", Xml2XlsxModel.class);
			BaseModel model = new Xml2XlsxModel();
			boolean succ = model.exec(configPath);
			System.out.println("end:" + (succ ? "success" : "fail"));
			
			break;

		case 2:
			String configPath2 = ConfigUtil.PATH_CONFIG_XLSX2XML;
			System.out.println("start");
			BaseModel model2 = BaseModel.newModel("", Xlsx2XmlModel.class);
			boolean succ2 = model2.exec(configPath2);
			System.out.println("end:" + (succ2 ? "success" : "fail"));
			break;
			
		case 3:
			String configPath3 = ConfigUtil.PATH_CONFIG_XLSX_UPDATE_XML;
			System.out.println("start");
//			BaseModel model3 = BaseModel.newModel("", XlsxUpdateXmlModel.class);
			BaseModel model3 = new XlsxUpdateXmlModel();
			boolean succ3 = model3.exec(configPath3);
			System.out.println("end:" + (succ3 ? "success" : "fail"));
			break;
		default:
			break;
		}
	}

	private static Integer select(Map<Integer, String> map) throws IOException {
		if (map == null) {
			return null;
		}
		Set<Integer> keyset = map.keySet();
		for (Integer key : keyset) {
			System.out.println(key + "," + map.get(key));
		}
		System.out.println("input:");

		Integer input = null;
		String value = null;
		Scanner scanner = new Scanner(System.in);
		while (value == null) {
			input = scanner.nextInt();
			value = map.get(input);
			if (value == null) {
				System.out.println("input error!!!");
				System.out.println("input:");
			}
		}
		return input;
	}
}
