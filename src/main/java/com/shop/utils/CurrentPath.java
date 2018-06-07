package com.shop.utils;

/**
 * 切面路径公用类
 * @author Administrator
 *
 */
public class CurrentPath {
	
	private static String CURRENTPATH;

	/**
	 * 智能获取当前返回页面路径<br/> 
	 * <br/> 
	 * 获取路径方式：<br/> 
	 * &nbsp;&nbsp;&nbsp;&nbsp;类名去掉Controller首字母小写文件夹下的<br/> 
	 * &nbsp;&nbsp;&nbsp;&nbsp;类名去掉Controller首字母小写_方法名.jsp文件<br/>
	 * example:<br/> 
	 * &nbsp;&nbsp;&nbsp;&nbsp;BaseController&nbsp;|&nbsp;basePage<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;base/base_basePage.jsp
	 * @return
	 */
	public static String getCURRENTPATH() {
		return CURRENTPATH;
	}

	/**
	 * 添加页面路径
	 */
	public static void setCURRENTPATH(String cURRENTPATH) {
		CURRENTPATH = cURRENTPATH;
	} 
}
