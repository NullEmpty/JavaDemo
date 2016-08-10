package com.carl.java.excel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.AttributeImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

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
			MyHandler dh = new MyHandler();
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
	
	public static void writeXml2(List<Entry> list) {
		if (list == null || list.size() <= 0) {
			return;
		}
		String path = "C:\\Users\\pcxu\\Desktop\\excel_test\\sax.xml";
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SAXTransformerFactory sff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		try {
			TransformerHandler th = sff.newTransformerHandler();
			Transformer transformer = th.getTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			File f = new File(path);
			if (!f.exists()) {
				f.createNewFile();
			}
			Result resultString = new StreamResult(new FileOutputStream(f));
			th.setResult(resultString);
			th.startDocument();
			
			AttributeImpl attr = new AttributeImpl(null);
			Attributes atts = new AttributesImpl();
			th.startElement("", "", QNAME_RESOURCES, null);
			
			th.startElement("", "", QNAME_STRING, atts);
			th.endElement("", "", QNAME_STRING);
			
			th.endElement("", "", QNAME_RESOURCES);
			th.endDocument();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
