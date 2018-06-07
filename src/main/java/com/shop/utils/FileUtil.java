package com.shop.utils;

import java.io.File;

public class FileUtil {

	/** 
	 *  根据路径删除指定的目录，无论存在与否 
	 *@param sPath  要删除的目录path 
	 *@return 删除成功返回 true，否则返回 false。 
	 */  
	public static boolean DeleteFolder(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            return deleteFile(sPath);  
	        } else {  // 为目录时调用删除目录方法  
	            return deleteDirectory(sPath);  
	        }  
	    }  
	}
	
	/** 
	 * 删除单个文件 
	 * @param   sPath 被删除文件path 
	 * @return 删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }
	    if(true==flag) {
	    	System.out.println("删除"+sPath+"文件成功！");
	    }else {
	    	System.out.println("删除"+sPath+"文件失败！");
	    }
	    return flag;  
	}
	
	/** 
	 * 删除目录以及目录下的文件 
	 * @param   sPath 被删除目录的路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {
		    System.out.println("删除"+sPath+"文件夹失败！ 该文件不存在或不是个文件夹");
	        return false;  
	    }  
	    boolean flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) {
		    System.out.println("删除"+sPath+"文件夹失败！");
	    	return false;  
	    }
	    //删除当前目录  
	    if (dirFile.delete()) { 
		    System.out.println("删除"+sPath+"文件夹成功！");
	        return true;  
	    } else {
	    	System.out.println("删除"+sPath+"文件夹失败！");
	        return false;  
	    }  
	}
	
}
