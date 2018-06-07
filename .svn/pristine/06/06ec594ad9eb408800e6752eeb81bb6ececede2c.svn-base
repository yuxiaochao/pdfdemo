package com.shop.utils;


/**
 * 线程控制类
 * 
 * @author baiyu
 *
 */
public class ThreadUtils {
	
	

	private static ThreadLocal<RText> rTexts = new ThreadLocal<RText>() {
		public synchronized RText initialValue() {
			return new RText();
		}
	};


	private static ThreadLocal<ReflectUtil> reflectUtils = new ThreadLocal<ReflectUtil>() {
		public synchronized ReflectUtil initialValue() {
			return new ReflectUtil();
		}
	};

	
	
	public static RText getRTex() {
		return rTexts.get();
	}


	public static ReflectUtil getReflectUtil() {
		return reflectUtils.get();
	}

	

}
