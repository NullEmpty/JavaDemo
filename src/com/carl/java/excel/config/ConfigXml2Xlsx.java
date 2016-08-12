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
public class ConfigXml2Xlsx {
	
	private String xmlPath;
	private String xlsxPath;
	private String sheetName;
	private String[] language;
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
	public String[] getLanguage() {
		return language;
	}
	public void setLanguage(String[] language) {
		this.language = language;
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
		sb.append("xmlPath="+ xmlPath)
		.append("\nxlsxPath=" + xlsxPath)
		.append("\nsheetName=" + sheetName)
		.append("\nlanguage=" + language)
		.append("\nvalue=" + value);
		return sb.toString();
	}
	
	public static List<ConfigXml2Xlsx> parse(String configPath) {
		File f = new File(configPath);
		if (!f.exists()) {
			return null;
		}
		List<ConfigXml2Xlsx> list = new ArrayList<ConfigXml2Xlsx>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String line = null;
			ConfigXml2Xlsx config = null;
			while((line = br.readLine()) != null) {
				int commentStart = line.indexOf("#");
				if (commentStart >= 0) {
					line = line.substring(commentStart);
				}
				line = line.trim();
				
				if (line.startsWith("<xml2xlsx>")) {
					config = new ConfigXml2Xlsx();
				} else if (line.startsWith("</xml2xlsx>")) {
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
					} else if (array[0].equalsIgnoreCase("language")) {
						String[] lang = array[1].split(",");
						config.setLanguage(lang);
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
