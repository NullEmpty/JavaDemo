package com.carl.java.excel.model;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.carl.java.excel.Entry;
import com.carl.java.excel.ExcelUtil;
import com.carl.java.excel.XmlUtil;
import com.carl.java.excel.config.ConfigXml2Xlsx;

/**
 * @author: Peichen Xu
 * @since: 2016-8-11
 */
public class Xml2XlsxModel {

	public Xml2XlsxModel() {

	}

	public void exec(String configPath) {
		List<ConfigXml2Xlsx> configList = ConfigXml2Xlsx.parse(configPath);
		if (configList == null || configList.size() <= 0) {
			return;
		}

		try {
			for (ConfigXml2Xlsx config : configList) {
				if (config == null) {
					System.out.println("config= null");
					continue;
				}
				System.out.println(config.toString());
				List<Entry> list = XmlUtil.readXml(config.getXmlPath());
				ExcelUtil.writeExcel(config.getXlsxPath(),
						config.getSheetName(), list, config.getLanguage(),
						config.getValue());
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
