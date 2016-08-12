package com.carl.java.excel.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Peichen Xu
 * @since: 2016-8-11
 */
public class ConfigXlsxUpdateXml {
	
	private String xmlPathSrc;
	private String xmlPathDst;
	private String xlsxPath;
	private String sheetName;
	private String value;
	
	public String getXmlPathSrc() {
		return xmlPathSrc;
	}
	public void setXmlPathSrc(String path) {
		this.xmlPathSrc = path;
	}
	public String getXmlPathDst() {
		return xmlPathDst;
	}
	public void setXmlPathDst(String xmlPathDst) {
		this.xmlPathDst = xmlPathDst;
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
		.append("\nxmlPathSrc="+ xmlPathSrc)
		.append("\nxmlPathDst="+ xmlPathDst)
		.append("\nxlsxPath=" + xlsxPath)
		.append("\nsheetName=" + sheetName)
		.append("\nvalue=" + value)
		.append("\n--------------END-------------");
		return sb.toString();
	}
	
	public static List<ConfigXlsxUpdateXml> parse(String configPath) {
		File f = new File(configPath);
		if (!f.exists()) {
			return null;
		}
		List<ConfigXlsxUpdateXml> list = new ArrayList<ConfigXlsxUpdateXml>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String line = null;
			ConfigXlsxUpdateXml config = null;
			while((line = br.readLine()) != null) {
				int commentStart = line.indexOf("#");
				if (commentStart >= 0) {
					line = line.substring(commentStart);
				}
				line = line.trim();
				
				if (line.startsWith("<xlsx2xml>")) {
					config = new ConfigXlsxUpdateXml();
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
					if (array[0].equalsIgnoreCase("xmlPathSrc")) {
						config.setXmlPathSrc(array[1]);
					} else if (array[0].equalsIgnoreCase("xmlPathDst")) {
						config.setXmlPathDst(array[1]);
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
