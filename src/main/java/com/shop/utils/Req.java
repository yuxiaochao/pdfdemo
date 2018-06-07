package com.shop.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class Req implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(Req.class);
	
	
	
	/**
	 * 唯一校验字段
	 */
	private String uniqueValidate;
	/**
	 * 参数列表
	 */
	private  Map parameterMap;
	private  String parameter;
	
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
		Map parameterMap=new HashMap<>();
		Gson gson = new Gson();
		this.setParameterMap(gson.fromJson(parameter, parameterMap.getClass()));
	}
	
	/**
	 * 查询类型
	 */
	private Map<String, String> queryTypeMap = new HashMap<String, String>();

	/**
	 * 实体类Class名称
	 */
	private String entityClassName;
	/**
	 * 操作的主键
	 */
	public String ids;
	
	/**
	 * 图片类型
	 */
	public String imgType;
	
	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	/**
	 * 获取字符串类型参数
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		String value = null;
		if (null != parameterMap) {
			value = (String) parameterMap.get(key);
		}
		return value;
	}

	/**
	 * 获取Integer类型参数
	 * 
	 * @param key
	 * @return
	 */
	public Integer getInteger(String key) {
		Integer value = null;
		if (null != parameterMap) {
			value = (Integer) parameterMap.get(key);
		}
		
		return value;
	}
	
	/**
	 * 获取Double类型参数
	 * 
	 * @param key
	 * @return
	 */
	public Double getDouble(String key) {
		Double value = null;
		if (null != parameterMap) {
			value = (Double) parameterMap.get(key);
		}
		
		return value;
	}
	
	/**
	 * 获取Float类型参数
	 * 
	 * @param key
	 * @return
	 */
	public Float getFloat(String key) {
		Float value = null;
		if (null != parameterMap) {
			value = (Float) parameterMap.get(key);
		}
		return value;
	}

	/**
	 * 获取key的值
	 * 
	 * @param key
	 * @return
	 */
	public Object getWebKey(String key) {
		Object value = parameterMap.get(key);
		
		if (null == value) {
			value = parameterMap.get(key);
		}
		return value;
	}

	/**
	 * 获取Boolean类型参数
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(String key) {
		boolean value = false;
		if (null != parameterMap) {
			value = (boolean) parameterMap.get(key);
		}
		return value;
	}
	
	/**
	 * 获取Object类型参数
	 * 
	 * @param key
	 * @return
	 */
	public Object getObject(String key) {
		Object value = null;
		
		if (null != parameterMap) {
			value = parameterMap.get(key);
		}
		return value;
	}

	/**
	 * 向参数列表加入参数
	 * 
	 * @param key
	 * @param value
	 */
	public void putParameter(String key, Object value) {
		if (null == parameterMap) {
			parameterMap = new HashMap();
		}
		parameterMap.put(key, value);
	}

	public Map getParameterMap() {
		return parameterMap;
	}

	public String getUniqueValidate() {
		return uniqueValidate;
	}

	public void setUniqueValidate(String uniqueValidate) {
		this.uniqueValidate = uniqueValidate;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setParameterMap(Map parameterMap) {
		this.parameterMap = parameterMap;
	}

	public Map<String, String> getQueryTypeMap() {
		return queryTypeMap;
	}

	public void setQueryTypeMap(Map<String, String> queryTypeMap) {
		this.queryTypeMap = queryTypeMap;
	}

	public String getEntityClassName() {
		return entityClassName;
	}

	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}

	
	@Override  
	public Object clone() {
        try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}  
        return new Req();
    }

	
}
