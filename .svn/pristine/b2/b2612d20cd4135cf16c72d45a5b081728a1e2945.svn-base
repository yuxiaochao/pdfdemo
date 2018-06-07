package com.shop.utils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ReflectUtil {
	/**
	 * 得到泛型的类型
	 * 
	 * @param clazz
	 * @return
	 */
	public Class getGenericParmeterType(Class clazz) {
		Type type = clazz.getGenericSuperclass();
		Class<?> genericClass = null;
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			Type[] types = pt.getActualTypeArguments();
			genericClass = (Class<?>) types[0];
		}
		return genericClass;
	}

	/**
	 * 得到实体类的 字段
	 * 
	 * @param clazz
	 * @param filedName
	 * @return
	 */
	public Field getEntityField(Class clazz, String filedName) {
		Field[] fs = clazz.getDeclaredFields();
		Field fd = null;
		for (Field field : fs) {
			String name = field.getName();
			if (name.equals(filedName)) {
				fd = field;
				break;
			}
		}
		return fd;
	}

	/**
	 * 得到实体类所有属性
	 */
	public List<String> getEntityPropertyCollection(Class clazz) {
		Field[] fs = clazz.getDeclaredFields();
		List<String> propertys = new ArrayList<String>(fs.length);
		for (Field field : fs) {
			propertys.add(field.getName());
		}
		return propertys;
	}

	/**
	 * 得到实体类属性的类型
	 * 
	 * @param clazz
	 * @param propertys
	 * @return
	 */
	public Class[] getEntityPropertyType(Class clazz, String... propertys) {
		String[] propertyTypes = propertys;
		Class[] propertyClassTypes = new Class[propertyTypes.length];
		for (int i = 0; i < propertyTypes.length; i++) {
			Field field = getEntityField(clazz, propertyTypes[i]);
			propertyClassTypes[i] = field.getType();
		}

		return propertyClassTypes;
	}

	/**
	 * 得到所有名称和属性类型列表
	 * 
	 * @param entity
	 * @param methodName
	 * @return
	 */
	public Map<String, String> getEntityPropertyOrTypeCollection(Class clazz) {
		Field[] fs = clazz.getDeclaredFields();
		Map<String, String> propertysType = new HashMap<String, String>(fs.length);
		for (Field field : fs) {
			String fieldType = field.getType().getName();
			String fieldName = field.getName();
			propertysType.put(fieldName, fieldType);
		}
		return propertysType;
	}

	/**
	 * 根据所有的方法实例
	 * 
	 * @param entity
	 * @param methodName
	 * @return
	 */
	public Method[] getMethods(Object entity) {
		Method[] methods = null;
		if (null != entity) {
			methods = entity.getClass().getMethods();
		}
		return methods;
	}

	/**
	 * 判断一个对象是否包含一个属性
	 * 
	 * @param entity
	 * @param methodName
	 * @return
	 */
	public boolean containProperty(Class clazz, String property) {
		boolean flag = false;
		if (null != clazz) {
			List<String> propertyList = getEntityPropertyCollection(clazz);
			if (null != propertyList) {
				flag = propertyList.contains(property);
			}
			propertyList = null;
		}
		return flag;
	}

	/**
	 * 获取类型的默认值
	 * 
	 * @param type
	 * @return
	 */
	public Object getDefaultValue(Class type) {
		Object defValue = null;
		if (null != type) {
			if (String.class.isAssignableFrom(type)) {
				defValue = "";
			} else if (Integer.class.isAssignableFrom(type)) {
				defValue = 0;
			} else if (Boolean.class.isAssignableFrom(type)) {
				defValue = false;
			} else if (Long.class.isAssignableFrom(type)) {
				defValue = 0L;
			} else if (Double.class.isAssignableFrom(type)) {
				defValue = 0;
			} else if (Date.class.isAssignableFrom(type)) {
				defValue = null;
			}
		}
		return defValue;
	}

	/**
	 * 根据Class 得到Annotation数组
	 * 
	 * @param clazz
	 * @return
	 */
	public Annotation[] getAnnotations(Class clazz) {
		Annotation[] annotations = clazz.getAnnotations();
		return annotations;
	}

	/**
	 * 得到一个类的注解
	 * 
	 * @param clazz
	 * @param annotationClass
	 * @return
	 */
	public Annotation getAnnotation(Class clazz, Class annotationClass) {
		return clazz.getAnnotation(annotationClass);
	}

	/**
	 * 获取Object的类型
	 * 
	 * @param value
	 * @return
	 */
	public Class getObjectType(Object value) {
		Class sourceType = Object.class;
		if (null != value) {
			if (value instanceof String) {
				sourceType = String.class;
			} else if (value instanceof Integer) {
				sourceType = Integer.class;
			} else if (value instanceof Long) {
				sourceType = Long.class;
			} else if (value instanceof Date) {
				sourceType = Date.class;
			}
		}
		return sourceType;
	}

	/**
	 * 得到一个字段的注解
	 * 
	 * @param field
	 * @param annotationClass
	 * @return
	 */
	public Annotation getAnnotation(Field field, Class annotationClass) {
		return field.getAnnotation(annotationClass);
	}

	/**
	 * 检查实体类是否包含某个注解
	 * 
	 * @param field
	 * @param annotationClass
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public boolean containerAnnotation(Object obj, Class annotationClass)
			throws NoSuchFieldException, SecurityException {
		boolean flag = false;
		if (null != obj) {
			Annotation[] annotations = getAnnotations(obj.getClass());
			if (annotations.length > 0) {
				flag = true;
			} else {
				Annotation annotation = null;
				List<String> fieldNames = getEntityPropertyCollection(obj.getClass());
				for (String fieldName : fieldNames) {
					Field field = getField(obj, fieldName);
					annotation = getAnnotation(field, annotationClass);
					if (null != annotation) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 得到对象的返回类型
	 * 
	 * @return
	 */
	public Class<?> getMethodType(Object obj, String methodName) {
		Class<?> returnType = null;
		if (null != obj) {
			Method[] methods = obj.getClass().getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					returnType = method.getReturnType();
					break;
				}
			}
		}

		return returnType;
	}

	/**
	 * 利用反射获取指定对象的指定属性的值
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @return 目标属性的值
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public Object getFieldValue(Object obj, String fieldName)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Object result = null;
		Field field = getField(obj, fieldName);
		if (field != null) {
			field.setAccessible(true);
			result = field.get(obj);
		}
		return result;
	}

	/**
	 * 利用反射获取指定对象里面的指定属性
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @return 目标字段
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	private Field getField(Object obj, String fieldName) throws NoSuchFieldException, SecurityException {
		Field field = null;
		if (obj != null) {
			for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
				field = clazz.getDeclaredField(fieldName);
				if (null != field) {
					break;
				}

			}
		}
		return field;
	}

	/**
	 * 利用反射获取指定对象里面的所有指定属性 包括父类的属性
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @return 目标字段
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	private Field getAllField(Object obj, String fieldName) throws NoSuchFieldException, SecurityException {
		Field field = null;
		if (obj != null) {
			for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
				field = clazz.getField(fieldName);
				if (null != field) {
					break;
				}

			}
		}
		return field;
	}

	/**
	 * 利用反射设置指定对象的指定属性为指定的值
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @param fieldValue
	 *            目标值
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public void setFieldValue(Object obj, String fieldName, Object fieldValue)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field field = getField(obj, fieldName);
		if (field != null) {
			field.setAccessible(true);
			field.set(obj, fieldValue);
		}
	}

	/**
	 * 利用反射设置指定对象的指定属性为指定的值
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @param fieldValue
	 *            目标值
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public void setObjectValue(Object obj, String fieldName, Object fieldValue)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field field = getAllField(obj, fieldName);
		if (field != null) {
			field.setAccessible(true);
			field.set(obj, fieldValue);
		}
	}

	/**
	 * 根据字符串创建class对象
	 * 
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Object getObjectForName(String className)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Object value = null;
		Class clazz = getClassForName(className);
		if (null != clazz) {
			value = clazz.newInstance();
		}
		return value;
	}

	/**
	 * 根据字符串创建class对象
	 * 
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Object getObjectForClass(Class clazz) throws InstantiationException, IllegalAccessException {
		Object value = null;
		if (null != clazz) {
			value = clazz.newInstance();
		}
		return value;
	}

	/**
	 * 根据字符串获取class
	 * 
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Class getClassForName(String className) throws ClassNotFoundException {
		Class clazz = null;

		clazz = Class.forName(className);

		return clazz;
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager
	 * <Book>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or <code>Object.class</code> if cannot
	 *         be determined
	 */
	public Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager
	 * <Book>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 */
	public Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public boolean isCollection(Object obj) {
		boolean flag = false;
		if (obj instanceof List) {
			flag = true;
		} else if (obj instanceof Set) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public boolean isCollection(Class clazz) {
		String className = clazz.getName();
		boolean falg = false;
		if ("java.util.List".equals(className)) {
			falg = true;
		} else if ("java.util.Set".equals(className)) {
			falg = true;
		}
		return falg;
	}

	/**
	 * 获取某包下（包括该包的所有子包）所有类
	 * 
	 * @param packageName
	 *            包名
	 * @return 类的完整名称
	 */
	public static List<String> getPackageClassName(String packageName) {
		return getPackageClassName(packageName, true);
	}

	/**
	 * 获取某包下所有类
	 * 
	 * @param packageName
	 *            包名
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	public static List<String> getPackageClassName(String packageName, boolean childPackage) {
		List<String> fileNames = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");
		URL url = loader.getResource(packagePath);
		if (url != null) {
			String type = url.getProtocol();
			if (type.equals("file")) {
				fileNames = getClassNameByFile(url.getPath(), null, childPackage);
			} else if (type.equals("jar")) {
				fileNames = getClassNameByJar(url.getPath(), childPackage);
			}
		} else {
			fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, childPackage);
		}
		return fileNames;
	}

	/**
	 * 从项目文件获取某包下所有类
	 * 
	 * @param filePath
	 *            文件路径
	 * @param className
	 *            类名集合
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				if (childPackage) {
					myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
				}
			} else {
				String childFilePath = childFile.getPath();
				if (childFilePath.endsWith(".class")) {
					childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9,
							childFilePath.lastIndexOf("."));
					childFilePath = childFilePath.replace("\\", ".");
					myClassName.add(childFilePath);
				}
			}
		}

		return myClassName;
	}

	/**
	 * 从jar获取某包下所有类
	 * 
	 * @param jarPath
	 *            jar文件路径
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	private static List<String> getClassNameByJar(String jarPath, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		String[] jarInfo = jarPath.split("!");
		String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
		String packagePath = jarInfo[1].substring(1);
		try {
			JarFile jarFile = new JarFile(jarFilePath);
			Enumeration<JarEntry> entrys = jarFile.entries();
			while (entrys.hasMoreElements()) {
				JarEntry jarEntry = entrys.nextElement();
				String entryName = jarEntry.getName();
				if (entryName.endsWith(".class")) {
					if (childPackage) {
						if (entryName.startsWith(packagePath)) {
							entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
							myClassName.add(entryName);
						}
					} else {
						int index = entryName.lastIndexOf("/");
						String myPackagePath;
						if (index != -1) {
							myPackagePath = entryName.substring(0, index);
						} else {
							myPackagePath = entryName;
						}
						if (myPackagePath.equals(packagePath)) {
							entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
							myClassName.add(entryName);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myClassName;
	}

	/**
	 * 从所有jar中搜索该包，并获取该包下所有类
	 * 
	 * @param urls
	 *            URL集合
	 * @param packagePath
	 *            包路径
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		if (urls != null) {
			for (int i = 0; i < urls.length; i++) {
				URL url = urls[i];
				String urlPath = url.getPath();
				// 不必搜索classes文件夹
				if (urlPath.endsWith("classes/")) {
					continue;
				}
				String jarPath = urlPath + "!/" + packagePath;
				myClassName.addAll(getClassNameByJar(jarPath, childPackage));
			}
		}
		return myClassName;
	}

	public boolean isBasicType(Object obj) {
		boolean flag = false;
		if (obj instanceof Boolean) {
			flag = true;
		} else if (obj instanceof Byte) {
			flag = true;
		} else if (obj instanceof Short) {
			flag = true;
		} else if (obj instanceof Character) {
			flag = true;
		} else if (obj instanceof Integer) {
			flag = true;
		} else if (obj instanceof Long) {
			flag = true;
		} else if (obj instanceof Float) {
			flag = true;
		} else if (obj instanceof Double) {
			flag = true;
		}
		return flag;
	}
}
