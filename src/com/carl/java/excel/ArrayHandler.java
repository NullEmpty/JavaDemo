package com.carl.java.excel;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author: Peichen Xu
 * @since: 2016-8-10
 */
public class ArrayHandler extends DefaultHandler {
	
	private static final String TAG = ArrayHandler.class.getSimpleName();
	private List<Entry> mList;
	private Entry mEntryCur;
	private StringBuilder mSb;
	private String mArrayName;

	public ArrayHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Entry> getList() {
		return mList;
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("endDocument");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		System.out.println("end---uri=" + uri + ",localName=" + localName + ",qName=" + qName);
		
		if ("string-array".equals(qName)) {
			mArrayName = null;
		} else if ("item".equals(qName)) {
			if (mEntryCur != null) {
				mEntryCur.setValue(mSb.toString());
				mList.add(mEntryCur);
			}
			mEntryCur = null;
			mSb.delete(0, mSb.length());
		}
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("startDocument");
		mList = new ArrayList<Entry>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		System.out.println("start---uri=" + uri + ",localName=" + localName + ",qName=" + qName);
		System.out.println("attributes=" + attributes.getValue("name"));
		
		if ("string-array".equals(qName)) {
			mArrayName = attributes.getValue("name");
		} else if ("item".equals(qName)) {
			mEntryCur = new Entry();
			mEntryCur.setName("[array]" + mArrayName);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println("characters=" + new String(ch, start, length));
		if (mSb == null) {
			mSb = new StringBuilder();
		}
		
		if (mEntryCur != null) {
			String value = new String(ch, start, length);
			value = value.replace("&", "&amp;");
//			value = value.replace(" ", "&nbsp;");
			value = value.replace("<", "&lt;");
			value = value.replace(">", "&gt;");
			mSb.append(value);
		}
	}
	
	

}
