package com.carl.java.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Peichen Xu
 * @since: 2016-8-10
 */
public class Entry {
	
	private String name;
	private String value;
	private String value_en;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue_en() {
		return value_en;
	}
	public void setValue_en(String value_en) {
		this.value_en = value_en;
	}
	
	public String toString() {
		return new StringBuilder().append("name=").append(name).append(",value=").append(value).append(",value_en=").append(value_en).toString();
	}
	
	public static Map<String, Entry> formatList2Map(List<Entry> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		Map<String, Entry> map = new HashMap<String, Entry>();
		for (Entry entry : list) {
			map.put(entry.getName(), entry);
		}
		
		return map;
	}

}
