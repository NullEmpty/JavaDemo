package com.carl.java.excel.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Peichen Xu
 * @since: 2016-8-11
 */
public class ConfigXlsx2Xml {
	
	private String xmlPath;
	private String xlsxPath;
	private String sheetName;
	private String value;
	
	public String getXmlPath() {
		return xmlPath;
	}
	public void setXmlPath(String path) {
		this.xmlPath = path;
	}
	public String getXlsxPath() {
		return xlsxPath;
	}
	public void setXlsxPath(String xlsxPath) {
		this.xlsxPath = xlsxPath;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--------------CONFIG-------------")
		.append("xmlPath="+ xmlPath)
		.append("\nxlsxPath=" + xlsxPath)
		.append("\nsheetName=" + sheetName)
		.append("\nvalue=" + value)
		.append("\n--------------END-------------");
		return sb.toString();
	}
	
	public static List<ConfigXlsx2Xml> parse(String configPath) {
		File f = new File(configPath);
		if (!f.exists()) {
			return null;
		}
		List<ConfigXlsx2Xml> list = new ArrayList<ConfigXlsx2Xml>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(configPath), "UTF-8"));
			String line = null;
			ConfigXlsx2Xml config = null;
			while((line = br.readLine()) != null) {
				int commentStart = line.indexOf("#");
				if (commentStart >= 0) {
					line = line.substring(commentStart);
				}
				line = line.trim();
				
				if (line.startsWith("<xlsx2xml>")) {
					config = new ConfigXlsx2Xml();
				} else if (line.startsWith("</xlsx2xml>")) {
					if (config != null) {
						list.add(config);
					}
				} else {
					if (config == null) {
						continue;
					}
					String[] array = line.split("=");
					if (array == null || array.length != 2) {
						continue;
					}
					array[0] = array[0].trim();
					if (array[0].equalsIgnoreCase("xmlPath")) {
						config.setXmlPath(array[1]);
					} else if (array[0].equalsIgnoreCase("xlsxPath")) {
						config.setXlsxPath(array[1]);
					} else if (array[0].equalsIgnoreCase("sheetName")) {
						config.setSheetName(array[1]);
					} else if (array[0].equalsIgnoreCase("value")) {
						config.setValue(array[1]);
					} 
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				br = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
