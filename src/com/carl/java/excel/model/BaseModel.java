package com.carl.java.excel.model;
/**
 * @author: Peichen Xu
 * @since: 2016-8-12
 */
public abstract class BaseModel {
	public abstract boolean exec(String configPath);
	
	public static BaseModel newModel(String configPath, Class< ? extends BaseModel> clazz) {
			
		return null;
	}
}
