package com.carl.java.excel;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * @author: Peichen Xu
 * @since: 2016-8-10
 */
public class XmlUtil {
	
	private static final String QNAME_RESOURCES = "resources";
	private static final String QNAME_STRING = "string";
	private static final String BR = "\n";
	
	public static List<Entry> readXml(String path) {
		if (path == null) {
			return null;
		}
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			File f = new File(path);
			StringHandler dh = new StringHandler();
			parser.parse(f, dh);
			List<Entry> list = dh.getList();
			
			return list;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Entry> readXmlArray(String path) {
		if (path == null) {
			return null;
		}
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			File f = new File(path);
			ArrayHandler dh = new ArrayHandler();
			parser.parse(f, dh);
			List<Entry> list = dh.getList();
			
			return list;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void writeXml(List<Entry> list, String path) {
		if (list == null || list.size() <= 0) {
			return;
		}
		
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>".getBytes("utf-8"));
			bos.write(BR.getBytes("utf-8"));
			bos.write("<resources>".getBytes("utf-8"));
			bos.write(BR.getBytes("utf-8"));
			
			for(Entry entry : list) {
				bos.write(String.format("  <string name=\"%s\">%s</string>", entry.getName(), entry.getValue()).getBytes("utf-8"));
				bos.write(BR.getBytes("utf-8"));
			}
			
			bos.write("</resources>".getBytes("utf-8"));
			bos.flush();
			bos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateXml(Map<String, Entry> map, String pathSrc, String pathDst) {
		if (map == null || map.isEmpty()) {
			return;
		}
		File fileSrc = new File(pathSrc);
		if (!fileSrc.exists()) {
			return;
		}
		File fileDst = new File(pathDst);
		if (fileDst.exists()) {
			fileDst.delete();
		}
		try {
			fileDst.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader brSrc = null;
		BufferedWriter bwDst = null;
		try {
			brSrc= new BufferedReader(new FileReader(fileSrc));
			bwDst= new BufferedWriter(new FileWriter(fileDst));
			String line = null;
			Pattern pattern = Pattern.compile(" name=\"\\w+\"");// name="\w+"
			while((line=brSrc.readLine()) != null) {
				
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					String result = matcher.group().trim();
					
					String name = result.replace("name=\"", "").replace("\"", "");
					System.out.println("name=" + name);
					Entry entry = map.get(name);
					if (entry != null && entry.getValue() != null) {
						String newValue = entry.getValue();
						System.out.println("newValue=" + newValue);
						line = line.replaceAll(">.*</string>", ">" + newValue + "</string>");
					}
				}
				bwDst.write(line);
				bwDst.newLine();
			}
			
			bwDst.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bwDst != null) {
					bwDst.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (brSrc != null) {
					brSrc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
