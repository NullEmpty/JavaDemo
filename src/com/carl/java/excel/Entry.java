package com.carl.java.excel;
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

}
