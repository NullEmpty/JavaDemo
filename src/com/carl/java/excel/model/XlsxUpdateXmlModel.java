package com.carl.java.excel.model;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.carl.java.excel.Entry;
import com.carl.java.excel.ExcelUtil;
import com.carl.java.excel.XmlUtil;
import com.carl.java.excel.config.ConfigXlsx2Xml;
import com.carl.java.excel.config.ConfigXlsxUpdateXml;

/**
 * @author: Peichen Xu
 * @since: 2016-8-11
 */
public class XlsxUpdateXmlModel {

	public XlsxUpdateXmlModel() {

	}

	public void exec(String configPath) {
		List<ConfigXlsxUpdateXml> configList = ConfigXlsxUpdateXml.parse(configPath);
		if (configList == null || configList.size() <= 0) {
			System.out.println("Xlsx2XmlModel=null");
			return;
		}

		try {
			for (ConfigXlsxUpdateXml config : configList) {
				List<Entry> entryList = ExcelUtil.ReadExcel(config.getXlsxPath(),
						config.getSheetName(), config.getValue());
				
				XmlUtil.updateXml(Entry.formatList2Map(entryList), config.getXmlPathSrc(), config.getXmlPathDst());
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
