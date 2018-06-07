package com.shop.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

public class RText {

	private static final Logger log = Logger.getLogger(RText.class);
	private static final String[] templateStr = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
			"z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "E", "T",
			"U", "V", "W", "X", "Y", "Z" };
	private static final String[] templateNum = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	public static List<String> coursewareTypes = new ArrayList<String>();

	/**
	 * 判断是否为空 当数组任意一个为空 返回true
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isAnyEmpty(Object... objs) {
		boolean flag = false;
		for (Object obj : objs) {
			if (isEmpty(obj)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 产生随机的六位数(手机验证码)
	 * 
	 * @return
	 */
	public static String getSix() {
		Random rad = new Random();
		return rad.nextInt(900000) + 100000 + "";
	}

	/**
	 * 判断是否为空 当数组所有值为空 返回true
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isAllEmpty(String... strs) {
		boolean flag = true;
		for (String str : strs) {
			if (!isEmpty(str)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 判断是否为空 当数组所有值为空 返回true
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isAllNotEmpty(Object... objs) {
		return !isAnyEmpty(objs);
	}

	/**
	 * 判断是否为空 当数组所有值为空 返回true
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isAllEmpty(Object... objs) {
		boolean flag = true;
		for (Object obj : objs) {
			if (!isEmpty(obj)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 判断是否为空 空返回true
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isEmpty(Object obj) {
		if (null == obj) {
			return true;
		} else {
			String str = obj.toString();
			if ("".equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否为非空 非空返回true
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isNotEmpty(Object obj) {
		String str = toString(obj);
		if (null != str && (!"".equals(str))) {
			return true;
		}
		return false;
	}

	/**
	 * 判断List集合是否为非空 非空返回true
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isNotEmpty(List list) {
		if (null != list && list.size() >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * 判断Map集合是否为非空
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isNotEmpty(Map map) {
		if (null != map && map.size() >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串分割
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public String[] split(String str, String split) {
		return StringUtils.split(str, split);
	}

	/**
	 * 首字母转大写
	 * 
	 * @return
	 */
	public String toUpperCaseFirstOne(String name) {
		StringBuilder sb = new StringBuilder(name);
		String c = toString(sb.charAt(0));
		if (c.toUpperCase().equals(c)) {
			return sb.toString();
		}
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * 首字母转小写
	 * 
	 * @return
	 */
	public String toLowerCaseFirstOne(String name) {
		StringBuilder sb = new StringBuilder(name);
		String c = toString(sb.charAt(0));
		if (c.toLowerCase().equals(c)) {
			return sb.toString();
		}
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * toString 方法重载 转换成String类型
	 * 
	 * @param obj
	 * @return
	 */
	public String toString(Object obj) {
		if (isEmpty(obj)) {
			return "";
		}
		if (obj instanceof String) {
			String str = obj.toString();
			return str;
		} else {
			String str = "";
			try {
				str = String.valueOf(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return str;
		}
	}

	/**
	 * toString 方法重载List<Object>转List<String>
	 * 
	 * @param list
	 * @return
	 */
	public List<String> toString(List list) {
		List<String> strList = new ArrayList<String>();
		if (null != list && list.size() > 0) {
			for (Object obj : list) {
				strList.add(toString(obj));
			}
		}
		return strList;
	}

	/**
	 * 解码转换字符串，用于防注入
	 * 
	 * @param value
	 * @return
	 */
	public static String decodeParameters(String value) {
		if (value != null) {
			value = ObjectUtils.toString(value).replace("♂1♀", "|");
			value = ObjectUtils.toString(value).replace("♂2♀", "&");
			value = ObjectUtils.toString(value).replace("♂3♀", ";");
			value = ObjectUtils.toString(value).replace("♂4♀", "$");
			value = ObjectUtils.toString(value).replace("♂5♀", "%");
			value = ObjectUtils.toString(value).replace("♂6♀", "@");
			value = ObjectUtils.toString(value).replace("♂7♀", "'");
			value = ObjectUtils.toString(value).replace("♂8♀", "\"");
			value = ObjectUtils.toString(value).replace("♂9♀", "<");
			value = ObjectUtils.toString(value).replace("♂10♀", ">");
			value = ObjectUtils.toString(value).replace("♂11♀", "(");
			value = ObjectUtils.toString(value).replace("♂12♀", ")");
			value = ObjectUtils.toString(value).replace("♂13♀", "+");
			value = ObjectUtils.toString(value).replace("♂14♀", "\n");
			value = ObjectUtils.toString(value).replace("♂15♀", "\r");
			value = ObjectUtils.toString(value).replace("♂16♀", ",");
			value = ObjectUtils.toString(value).replace("♂17♀", "\\");
		}

		return value;
	}

	/**
	 * toString 方法重载Set<Object>转Set<String>
	 * 
	 * @param Set
	 * @return
	 */
	public Set<String> toString(Set set) {
		Set<String> strSet = new HashSet<String>();
		if (null != set && set.size() > 0) {
			for (Object obj : set) {
				strSet.add(toString(obj));
			}
		}
		return strSet;
	}

	/**
	 * toString 方法重载 转换成String类型 def 当obj为空的时候返回默认值def
	 * 
	 * @param obj
	 * @return
	 */
	public String toString(Object obj, String def) {
		if (isEmpty(obj)) {
			return def;
		}
		if (obj instanceof String) {
			String str = obj.toString();
			return str;
		} else {
			String str = def;
			try {
				str = obj.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return str;
		}
	}

	/**
	 * 根据类路径和方法得到controller 命名空间
	 * 
	 * @param className
	 * @param methodName
	 */
	public String getControllerNameSpace(Class clazz, String methodName) {
		String spacePath = null;
		String classNameSpace = getControllerClassNameSpace(clazz);

		spacePath = classNameSpace + methodName;

		return spacePath;
	}

	/**
	 * 根据类路径得到controller 命名空间
	 * 
	 * @param className
	 * @param methodName
	 */
	public String getControllerClassNameSpace(Class clazz) {
		String classNameSpace = "";
		if (null != clazz && clazz.getName().endsWith("Controller")) {
			String classPackagePath = clazz.getName();
			String sipleClassName = clazz.getSimpleName();
			if (isNotEmpty(sipleClassName) && sipleClassName.contains("Controller")) {
				sipleClassName = toLowerCaseFirstOne(sipleClassName.replace("Controller", ""));
			}
			int index = classPackagePath.indexOf("web.controller") + 14;
			String space = classPackagePath.substring(index, classPackagePath.lastIndexOf(".") + 1);
			String project = classPackagePath.substring(classPackagePath.indexOf("com.") + 4,
					classPackagePath.indexOf(".web"));
			StringBuilder nameSpace = new StringBuilder();
			classNameSpace = nameSpace.append(space).append(sipleClassName).append("/").toString().replace(".", "/");
			classNameSpace = "/" + project + classNameSpace;
		}
		return classNameSpace;
	}

	/**
	 * 根据类路径得到controller 命名空间
	 * 
	 * @param className
	 * @param methodName
	 */
	public String getEditControllerClassNameSpace(Class clazz) {
		String namespace = getControllerClassNameSpace(clazz);
		String sipleClassName = clazz.getSimpleName();
		if (isNotEmpty(sipleClassName) && sipleClassName.contains("Controller")) {
			sipleClassName = toLowerCaseFirstOne(sipleClassName.replace("Controller", ""));
		}
		return namespace + sipleClassName;
	}

	/**
	 * toString 方法重载 转换成String数组
	 * 
	 * @param obj
	 * @return
	 */
	public String[] toString(Object[] array) {
		if (isEmpty(array)) {
			return null;
		}
		if (array instanceof String[]) {
			return (String[]) array;
		} else if (array instanceof Object[]) {
			String[] strArray = new String[array.length];
			for (int i = 0; i < array.length; i++) {
				strArray[i] = toString(array[i]);
			}
			return strArray;
		}
		return null;
	}

	/**
	 * 数组转字符串
	 * 
	 * @param array
	 *            需要转换的数组
	 * @param split
	 *            分隔符
	 * @return 例如 arrayToString(new[]{"1","2","3"},",") = "1,2,3"
	 */
	public String arrayToString(Object[] array, String split) {
		if (null == array) {
			return null;
		}
		String result = "";
		StringBuilder sb = new StringBuilder();
		for (Object obj : array) {
			sb.append(toString(obj)).append(split);
		}
		if (!isEmpty(split)) {
			result = removeLastStr(sb.toString(), split);
		}
		return result;
	}

	/**
	 * 数组转字符串
	 * 
	 * @param array
	 *            需要转换的数组
	 * 
	 * @return 例如 arrayToString(new[]{"1","2","3"}) = "1,2,3"
	 */
	public String arrayToString(Object[] array) {
		if (null == array) {
			return null;
		}
		return arrayToString(array, ",");
	}

	/**
	 * List 转字符串
	 * 
	 * @param list
	 *            需要转换的List
	 * @param split
	 *            转字符串的分隔符
	 * @return
	 */
	public String ListToString(List<Object> list, String split) {
		StringBuilder sb = new StringBuilder();
		for (Object obj : list) {
			sb.append(toString(obj)).append(split);
		}
		return removeLastStr(sb.toString(), split);
	}

	/**
	 * List 转字符串 默认以，分割
	 * 
	 * @param list
	 *            需要转换的List
	 * @return
	 */
	public String ListToString(List<Object> list) {
		return ListToString(list, ",");
	}

	/**
	 * List 转字符串
	 * 
	 * @param list
	 *            需要转换的List
	 * @param colunmName
	 *            需要转换的字段
	 * @param split
	 *            转字符串的分隔符
	 * @return
	 */
	public String ListToString(List<Map<String, Object>> list, String colunmName, String split) {
		StringBuilder sb = new StringBuilder();
		for (Map<String, Object> map : list) {
			sb.append(map.get(colunmName)).append(split);
		}
		return removeLastStr(sb.toString(), split);
	}

	/**
	 * set 转字符串
	 * 
	 * @param list
	 *            需要转换的List
	 * @param split
	 *            转字符串的分隔符
	 * @return
	 */
	public String SetToString(Set<Object> sets, String split) {
		StringBuilder sb = new StringBuilder();
		for (Object obj : sets) {
			sb.append(toString(obj)).append(split);
		}
		return removeLastStr(sb.toString(), split);
	}

	/**
	 * set 转字符串 默认以，分割
	 * 
	 * @param list
	 *            需要转换的List
	 * @return
	 */
	public String SetToString(Set<Object> sets) {
		return SetToString(sets, ",");
	}

	/**
	 * toString 方法重载 转换成Map
	 * 
	 * @param obj
	 * @return
	 */
	public Map<String, String> mapToStringMap(Map<Object, Object> map) {
		if (isEmpty(map)) {
			return null;
		}
		Map<String, String> stirngMap = new HashMap<String, String>();
		Set<Object> keys = map.keySet();
		for (Object key : keys) {
			String value = toString(map.get(key));
			stirngMap.put(toString(key), value);
		}
		keys = null;
		return stirngMap;
	}

	/**
	 * 根据数组下标获取值 Object[] array = {1,3,5} toObjectForArray(array,0) = 1
	 * 
	 * toObjectForArray(array,1) = 3
	 * 
	 * toObjectForArray(array,3) = null
	 * 
	 * @param T
	 * @return T
	 */
	public <T> T toObjectForArray(T[] args, int index) {
		if (null == args) {
			return null;
		}
		if (index < args.length) {
			return args[index];
		} else {
			return null;
		}
	}

	/**
	 * 根据数组下标获取值 Object[] array = {1,3,5} toObjectForArray(array,0,0) = 1
	 * 
	 * toObjectForArray(array,1,0) = 3
	 * 
	 * toObjectForArray(array,3,0) = 0
	 * 
	 * @param obj
	 * 
	 * @param def
	 * 
	 * @return
	 */
	public <T> T toObjectForArray(T[] args, int index, T def) {
		T t = toObjectForArray(args, index);
		if (null == t) {
			return def;
		} else {
			return t;
		}
	}

	/**
	 * 根据数组下标获取值 String[] array = {"A","B","C"} toStringForArray(array,0) = "A"
	 * 
	 * toStringForArray(array,1) = "B"
	 * 
	 * toStringForArray(array,3) = ""
	 * 
	 * @param obj
	 * 
	 * 
	 * @return
	 */
	public String toStringForArray(Object[] args, int index) {
		Object obj = toObjectForArray(args, index);
		String str = toString(obj);
		if (!isEmpty(str)) {
			return str;
		}
		return "";
	}

	/**
	 * 根据数组下标获取值 String[] array = {"A","B","C"} toStringForArray(array,0,"def") =
	 * "A"
	 * 
	 * toStringForArray(array,1,"def") = "B"
	 * 
	 * toStringForArray(array,3,"def") = "def"
	 * 
	 * @param obj
	 * 
	 * @param def
	 * 
	 * @return
	 */
	public String toStringForArray(Object[] args, int index, String def) {
		Object obj = toObjectForArray(args, index);
		String str = toString(obj);
		if (!isEmpty(str)) {
			return str;
		}
		return def;
	}

	/**
	 * 字符串转Integer 类型
	 * 
	 * @param str
	 * @return
	 */
	public Integer toInteger(String str) {
		Integer number = 0;
		if (isEmpty(str)) {
			return number;
		}
		try {
			number = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return number;
	}

	/**
	 * Object转Integer 类型
	 * 
	 * @param str
	 * @return
	 */
	public Integer toInteger(Object obj) {
		String str = toString(obj);
		return toInteger(str);
	}

	/**
	 * 字符串转Integer 类型
	 * 
	 * @param str
	 * @return
	 */
	public Integer toInteger(String str, Integer def) {
		Integer number = def;
		if (isEmpty(str)) {
			return number;
		}
		try {
			number = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return number;
	}

	/**
	 * 字符串转Integer 类型
	 * 
	 * @param str
	 * @return
	 */
	public Integer toInteger(Object obj, Integer def) {
		String str = toString(obj);
		return toInteger(str, def);
	}

	/**
	 * 字符串转Long 类型
	 * 
	 * @param str
	 * @return
	 */
	public Long toLong(String str) {
		Long number = null;
		if (isEmpty(str)) {
			return number;
		}
		try {
			number = Long.parseLong(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return number;
	}

	/**
	 * 字符串转Long 类型
	 * 
	 * @param str
	 * @return
	 */
	public Long toLong(Object obj) {
		String str = toString(obj);
		return toLong(str);
	}

	/**
	 * 字符串转Long 类型
	 * 
	 * @param str
	 * @return
	 */
	public Long toLong(String str, Long def) {
		Long number = def;
		if (isEmpty(str)) {
			return number;
		}
		try {
			number = Long.parseLong(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return number;
	}

	/**
	 * 字符串转Long 类型
	 * 
	 * @param str
	 * @return
	 */
	public Long toLong(Object obj, Long def) {
		String str = toString(obj);
		return toLong(str);
	}

	/**
	 * 字符串转boolean 类型
	 * 
	 * @param str
	 * @return
	 */
	public Boolean toBoolean(String str) {
		Boolean bool = null;
		if (isEmpty(str)) {
			return bool;
		}
		try {
			bool = Boolean.parseBoolean(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 字符串转boolean 类型
	 * 
	 * @param str
	 * @return
	 */
	public Boolean toBoolean(String str, Boolean def) {
		Boolean bool = def;
		if (isEmpty(str)) {
			return bool;
		}
		try {
			bool = Boolean.parseBoolean(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}
	/**
	 * byte[]数组转字符串
	 * 
	 * @param bytes
	 * @param coding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String byteToString(byte[] bytes, String coding) throws UnsupportedEncodingException {
		String str = "";
		if (isEmpty(coding)) {
			coding = "UTF-8";
		}

		return new String(bytes, coding);
	}

	/**
	 * byte[]数组转字符串
	 * 
	 * @param bytes
	 * @param coding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String byteToString(byte[] bytes) throws UnsupportedEncodingException {

		return byteToString(bytes, "UTF-8");
	}

	/**
	 * 字符串转byte[]数组
	 * 
	 * @param bytes
	 * @param coding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public byte[] StringTobyte(String str, String coding) throws UnsupportedEncodingException {

		if (isEmpty(coding)) {
			coding = "UTF-8";
		}
		return str.getBytes(coding);
	}

	/**
	 * 字符串转byte[]数组
	 * 
	 * @param bytes
	 * @param coding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public byte[] StringTobyte(String str) throws UnsupportedEncodingException {
		return StringTobyte(str, "UTF-8");
	}

	/**
	 * 对象序列化
	 * 
	 * @param obj
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public byte[] objSerializeByte(Object obj) {
		byte[] bytes = null;
		if (obj instanceof Serializable) {
			bytes = SerializationUtils.serialize((Serializable) obj);
		} else {
			log.error("对象没有集成序列化接口，不能进行序列化转换...");
		}
		return bytes;
	}

	/**
	 * 对象反序列化
	 * 
	 * @param obj
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Object byteSerializeObj(byte[] bytes) {
		Object value = null;
		if (null != bytes) {
			value = SerializationUtils.deserialize(bytes);
		}
		return value;
	}

	/**
	 * base64 加密
	 * 
	 * @param str
	 * @return
	 */
	public String encodeBase64(String str) {
		Base64 base64 = new Base64();
		try {
			str = base64.encodeToString(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * base64 解密
	 * 
	 * @param str
	 * @return
	 */
	public String decodeBase64(String str) {
		str = new String(Base64.decodeBase64(str));
		return str;
	}

	/**
	 * 哈希加密
	 * 
	 * @param str
	 * @return
	 */
	public String encodeHex(String str) {
		try {
			str = Hex.encodeHexString(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;

	}

	/**
	 * 哈希解密
	 * 
	 * @param str
	 * @return
	 */
	public String decodeHex(String str) {
		Hex hex = new Hex();
		try {
			str = new String((byte[]) hex.decode(str));
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * url乱码解析
	 * 
	 * @param value
	 * @param character
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String decodeURI(String value, String character) throws UnsupportedEncodingException {
		String decodeValue = value;
		if (isNotEmpty(value)) {
			value = value.replaceAll("%", "%25");
			decodeValue = URLDecoder.decode(value, character);
		}
		return decodeValue;
	}

	/**
	 * url 乱码解析
	 * 
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String decodeURI(String value) throws UnsupportedEncodingException {
		return decodeURI(value, "UTF-8");
	}



	/**
	 * MD5加密
	 * 
	 * @param str
	 * @return
	 */
	public String encodeMD5(String str) {
		try {
			str = DigestUtils.md5Hex(toString(str).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * SHA加密
	 * 
	 * @param str
	 * @return
	 */
	public String encodeSHA(String str) {
		try {
			str = DigestUtils.sha512Hex(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 得到UUID
	 * 
	 * @return
	 */
	public String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	/**
	 * 随机得到一串数组
	 * 
	 * @return
	 */
	public int getRandomNumber() {
		Random random = new Random((long) (Math.random() * 1000000));
		return random.nextInt();
	}

	/**
	 * 随机得到一串数组
	 * 
	 * @param n
	 *            小于多少
	 * @return
	 */
	public int getRandomNumber(int n) {
		Random random = new Random((long) (Math.random() * 1000000));
		return random.nextInt(n);
	}

	/**
	 * 随机得到一串数组
	 * 
	 * @param min
	 *            大于等于
	 * @param max
	 *            小于等于
	 * @return
	 */
	public int getRandomNumber(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}

	/**
	 * 随机得到一个字符串
	 * 
	 * @return
	 */
	public String getRandomStr() {
		return getRandomStr(templateStr.length);
	}

	/**
	 * 随机得到一个字符串
	 * 
	 * @return
	 */
	public String getRandomStr(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(toStringForArray(templateStr, getRandomNumber(0, templateStr.length - 1), ""));
		}
		return sb.toString();
	}

	/**
	 * 随机得到一串数字
	 * 
	 * @return
	 */
	public String getRandomNum(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(toStringForArray(templateNum, getRandomNumber(templateNum.length - 1), ""));
		}
		return sb.toString();
	}

	/**
	 * 得到文件后缀名（扩展名）
	 * 
	 * @param fileName
	 * @return
	 */
	public String getFileExt(String fileName) {
		if (isEmpty(fileName)) {
			return null;
		}
		int k = fileName.lastIndexOf(".");
		if (k == -1) {
			return null;
		}
		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
		return prefix;
	}

	/**
	 * 转换成beetle模板
	 */
	public String converBeetleTemplate(String template) {
		if (isEmpty(template)) {
			return null;
		}
		template = template.replace("<!--", "<%").replace("-->", "%>").replace("@{", "${");
		return template;
	}

	/**
	 * 替换最后一次出现字符
	 * 
	 * @param str
	 *            需要处理的字符串
	 * @param oldStr
	 *            需要替换的字符
	 * @param newStr
	 *            替换为新的字符
	 * @return
	 */
	public String replaceLast(String str, String oldStr, String newStr) {
		StringBuilder sb = new StringBuilder(str);
		if (isAllNotEmpty(str, oldStr)) {
			if (-1 != str.indexOf(oldStr)) {
				sb.setLength(0);
				int newStrLength = newStr.length() == 0 ? 1 : newStr.length();
				int lastIndex = str.lastIndexOf(oldStr);
				sb.append(str.substring(0, lastIndex));
				sb.append(newStr);
				sb.append(str.substring(lastIndex + newStrLength));
			}
		}

		return sb.toString();
	}

	/**
	 * 删除最后一次出现的字符
	 * 
	 * @param str
	 *            需要处理的字符串
	 * @param lastStr
	 *            需要删除的字符串
	 * @return
	 */
	public String removeLastStr(String str, String lastStr) {
		return replaceLast(str, lastStr, "");
	}

	/**
	 * 获取系统流水号
	 * 
	 * @see 若欲指定返回值的长度or是否全由数字组成等,you can call
	 *      {@link TradePortalUtil#getSysJournalNo(int, boolean)}
	 * @return 长度为20的全数字
	 */
	public String getSysJournalNo() {
		return getSysJournalNo(20, true);
	}

	/**
	 * 获取系统流水号
	 * 
	 * @param length
	 *            指定流水号长度
	 * @param isNumber
	 *            指定流水号是否全由数字组成
	 */
	public String getSysJournalNo(int length, boolean isNumber) {
		// replaceAll()之后返回的是一个由十六进制形式组成的且长度为32的字符串
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		if (uuid.length() > length) {
			uuid = uuid.substring(0, length);
		} else if (uuid.length() < length) {
			for (int i = 0; i < length - uuid.length(); i++) {
				uuid = uuid + Math.round(Math.random() * 9);
			}
		}
		if (isNumber) {
			return uuid.replaceAll("a", "1").replaceAll("b", "2").replaceAll("c", "3").replaceAll("d", "4")
					.replaceAll("e", "5").replaceAll("f", "6");
		} else {
			return uuid;
		}
	}

	/**
	 * 根据value 得到key 按照最先找到的算
	 * 
	 * @return
	 */
	public String getKeyForMap(Map map, Object value) {
		String propertity = null;
		if (null != map && null != value) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				if (value.equals(map.get(key))) {
					propertity = key;
				}
			}
		}
		return propertity;
	}

	public Object toEnum(Class enumClass, String enumStr) {
		if (null != enumClass && isNotEmpty(enumStr)) {
			Object[] enus = enumClass.getEnumConstants();
			for (Object enu : enus) {
				String enuStr = toString(enu);
				if (isNotEmpty(enuStr)) {
					if (enuStr.equals(enumStr.toUpperCase())) {
						return enu;
					}
				}
			}
		}
		return null;
	}

	/**
	 * HTML字符转义
	 * 
	 * @see 对输入参数中的敏感字符进行过滤替换,防止用户利用JavaScript等方式输入恶意代码
	 * @see String input = <img src='http://t1.baidu.com/it/fm=0&gp=0.jpg'/>
	 * @see HtmlUtils.htmlEscape(input); //from spring.jar
	 * @see StringEscapeUtils.escapeHtml(input); //from commons-lang.jar
	 * @see 尽管Spring和Apache都提供了字符转义的方法,但Apache的StringEscapeUtils功能要更强大一些
	 * @see StringEscapeUtils提供了对HTML,Java,JavaScript,SQL,XML等字符的转义和反转义
	 * @see 但二者在转义HTML字符时,都不会对单引号和空格进行转义,而本方法则提供了对它们的转义
	 * @return String 过滤后的字符串
	 */
	public String htmlEscape(String input) {
		if (isEmpty(input)) {
			return input;
		}
		input = input.replaceAll("&", "&amp;");
		input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll(" ", "&nbsp;");
		input = input.replaceAll("'", "&#39;"); // IE暂不支持单引号的实体名称,而支持单引号的实体编号,故单引号转义成实体编号,其它字符转义成实体名称
		input = input.replaceAll("\"", "&quot;"); // 双引号也需要转义，所以加一个斜线对其进行转义
		input = input.replaceAll("\n", "<br/>"); // 不能把\n的过滤放在前面，因为还要对<和>过滤，这样就会导致<br/>失效了
		return input;
	}

	/**
	 * HTML字符转义反转
	 *
	 * @return String 过滤后的字符串
	 */
	public String htmlEscapeReversal(String input) {
		if (isEmpty(input)) {
			return input;
		}
		input = input.replaceAll("&amp;", "&");
		input = input.replaceAll("&lt;", "<");
		input = input.replaceAll("&gt;", ">");
		input = input.replaceAll("&nbsp;", " ");
		input = input.replaceAll("&#39;", "'"); // IE暂不支持单引号的实体名称,而支持单引号的实体编号,故单引号转义成实体编号,其它字符转义成实体名称
		input = input.replaceAll("&quot;", "\""); // 双引号也需要转义，所以加一个斜线对其进行转义
		input = input.replaceAll("<br/>", "\n"); // 不能把\n的过滤放在前面，因为还要对<和>过滤，这样就会导致<br/>失效了
		return input;
	}



	

	/**
	 * 数据转换float
	 * 
	 * @return
	 */
	public float toFloat(String key) {
		return NumberUtils.toFloat(key);
	}

	/**
	 * 获取搜索字段的key
	 * 
	 * @param fieldSeracehKey
	 * @return
	 */
	public String getSerachField(String fieldSeracehKey) {
		String fieldName = fieldSeracehKey;
		if (isNotEmpty(fieldSeracehKey)) {
			if (fieldSeracehKey.startsWith("BETWEEN_START_")) {
				fieldName = fieldSeracehKey.replace("BETWEEN_START_", "");
			} else if (fieldSeracehKey.startsWith("BETWEEN_END_")) {
				fieldName = fieldSeracehKey.replace("BETWEEN_END_", "");
			}
		}
		return fieldName;
	}

	/**
	 * 是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 对字符串MD5加密
	 * 
	 * @param plainText
	 * @return
	 */
	public String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			return buf.toString();// 32位的加密

			// buf.toString().substring(8,24);//16位的加密

		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}

	public static String getNowDate() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static String getFormatDate(Date date) {
		String returnValue = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date != null) {
			returnValue = format.format(date);
		}
		return returnValue;
	}

	public static String getSyncData(Object obj) {
		String returnValue = "";
		if (obj instanceof Date) {
			returnValue = RText.getFormatDate((Date) obj);
		} else {
			returnValue = String.valueOf(obj);
		}

		if (StringUtils.isEmpty(returnValue)) {
			returnValue = "null";
		}

		return returnValue;
	}

	public static String ToString(Object obj) {
		return RText.ToString(obj, "");
	}

	// 可以返回指定字符串
	public static String ToString(Object inValue, String inDefaultValue) {
		try {
			return inValue.toString();
		} catch (Exception e) {
		}
		return inDefaultValue;
	}

	public static long DateDiff(Long inStart, Long inEnd, int inDiffType) {
		int[] DTDiv = new int[] { 86400000, 86400000, 86400000, 3600000, 60000, 1000 };
		long diff = inEnd - inStart;
		return diff / DTDiv[inDiffType];
	}

	public static String dateDiff(String date1, String date2) {
		long n = DateDiff(toDate(date1, "yyyy-MM-dd HH:mm").getTime(), toDate(date2, "yyyy-MM-dd HH:mm").getTime(), 3);
		return ObjectUtils.toString(n);
	}

	public static String dateDiff(String date1, String date2, int inDiffType) {
		long n = DateDiff(toDate(date1, "yyyy-MM-dd HH:mm:ss").getTime(),
				toDate(date2, "yyyy-MM-dd HH:mm:ss").getTime(), inDiffType);
		return ObjectUtils.toString(n);
	}

	public static Date toDate(String date, String pattern) {
		SimpleDateFormat dt = new SimpleDateFormat(pattern);
		try {
			return dt.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// from javahelper.java
	public static Integer ToInteger(Object inValue, Integer inDefaultValue) {
		try {
			Double _double = Double.parseDouble(inValue.toString());
			return _double.intValue();
		} catch (Exception e) {
		}
		return inDefaultValue;
	}

	public static String transCoding(String str, String charSet) {
		/*
		 * try { return new String(str.getBytes("gbk"), "utf-8"); } catch
		 * (UnsupportedEncodingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		String encoding = guessEncoding(str);
		if (encoding.equalsIgnoreCase(charSet)) {
			return str;
		} else {
			try {
				return new String(str.getBytes(encoding), charSet);
			} catch (UnsupportedEncodingException e) { // TODO Auto-generated
				// catch block
				e.printStackTrace();
			}
		}

		return str;
	}

	public static String guessEncoding(String str) {
		String encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {

		}
		return "";
	}

	public String getWebServicePath(String path) {
		if (!isEmpty(path)) {
			return path.substring(0, path.lastIndexOf("/"));
		}
		return "";
	}

	public boolean isConverCourseware(String path) {
		/*
		 * SystemParameter sp = SystemParameterImpl.getSystemParameter();
		 * GlobalConstant2.Global_system_parameter = sp; coursewareTypes =
		 * StringToList(ObjectUtils.toString(GlobalConstant2.Global_system_parameter
		 * .getAttribute(GlobalConstant2.CoursewareConversionType)), ",", true);
		 */
		return coursewareTypes.contains(getPathExt(path).toUpperCase());
	}

	public String getPathExt(String path) {
		if (!isEmpty(path)) {
			return path.substring(path.lastIndexOf(".") + 1);
		}
		return "";
	}

	public static List StringToList(String str, String split, boolean isUpper) {
		String[] strs = str.split(split);
		List<String> list = new ArrayList<String>();
		for (String s : strs) {
			if (isUpper) {
				list.add(s.toUpperCase());
			} else {
				list.add(s);
			}

		}
		return list;
	}

	public String getPath(String path, String ext) {
		if (!isEmpty(path)) {
			return path.substring(0, path.lastIndexOf(".") + 1) + ext;
		}
		return "";
	}

	public String Sort(String str) {
		String value = "";
		if (str != null) {
			char[] chr = str.toCharArray();
			Arrays.sort(chr);
			value = new String(chr);
		}
		return value;
	}

	/**
	 * 截取float类型 小数位数 example:FormatNumber(12.35678f,2) = 12.36
	 * 
	 * @param number
	 * @param dogits
	 * @return
	 */
	public float FormatNumber(float number, int dogits) {
		java.text.NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(dogits);
		nf.setMinimumFractionDigits(0);
		return NumberUtils.toFloat(nf.format(number));
	}

	/**
	 * 截取double类型 小数位数 example:FormatNumber(12.35678,2) = 12.36
	 * 
	 * @param number
	 * @param dogits
	 * @return
	 */
	public double FormatNumber(double number, int dogits) {
		java.text.NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(dogits);
		nf.setMinimumFractionDigits(0);
		return NumberUtils.toDouble(nf.format(number));
	}



	public Field[] getEntityFieldSupers(Class clazz) {
		Field[] fs = getEntityFields(clazz);
		fs = (Field[]) ArrayUtils.addAll(fs, getEntityFields(clazz.getSuperclass()));
		if (clazz.getSuperclass().getSuperclass() != null) {
			fs = (Field[]) ArrayUtils.addAll(fs, getEntityFields(clazz.getSuperclass().getSuperclass()));
		}

		return fs;
	}

	public Field[] getEntityFields(Class clazz) {
		Field[] fs = clazz.getDeclaredFields();
		return fs;
	}

	private String methodTo(String pre, String str) {
		return pre + String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
	}

	/**
	 * 转发页面。 例如 ： 正常发送页面路径是“WEB-INF/content/huarui/user/study/study_centerList.jsp”,
	 * 转发后可以跳转到别的项目的包“WEB-INF/content/lzjy/user/study/study_centerList.jsp”。
	 * 
	 * @param returnStr
	 *            跳转的页面
	 * @param theme
	 *            哪些项目需要转发，写项目的code，如“lzjy”
	 * @return
	 */
	public String forwardPage(String returnStr, String... theme) {
		String str = "forward:/WEB-INF/content/" + returnStr + ".jsp^$";
		for (String themenow : theme) {
			str += themenow + ",";
		}
		return str;
	}

	/**
	 * 编码从Ascii转换成String
	 * 
	 * @param str
	 * @return
	 */
	public static String Ascii2String(String str) {
		StringBuffer buffer = new StringBuffer();
		try {
			if (str != null && !"".equals(str)) {
				String[] chars = str.split("-");
				for (int i = 0; i < chars.length; i++) {
					buffer.append((char) Long.parseLong(chars[i]));
				}
			}
		} catch (Exception e) {
			buffer = new StringBuffer();
			buffer.append(str);
		}
		return buffer.toString();
	}

	/**
	 * 编码从String转换成Ascii
	 * 
	 * @param str
	 * @return
	 */
	public static String String2Ascii(String str) {
		StringBuffer buffer = new StringBuffer();
		if (str != null && !"".equals(str)) {
			String splitString = "";
			char[] chars = str.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				buffer.append(splitString);
				buffer.append((int) chars[i]);
				splitString = "-";
			}
		}
		return buffer.toString();
	}

}
