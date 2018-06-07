package com.shop.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.base.common.BaseMapper;

/**
 * application 工具类
 * 
 * @author baiyu
 *
 */
public class ApplicationContextUtil { 

	private static final Map<ClassLoader, WebApplicationContext> currentContextPerThread =
			new ConcurrentHashMap<ClassLoader, WebApplicationContext>(1);
	/**
	 * 根据实体类获取能够操作的ServiceImpl
	 * 
	 * @param entityClass
	 *            获取实体类的Class 需要类似与 UserEntity 的类 能对应到 UserServiceImpl
	 * @return
	 */
	public static BaseMapper getMapperForEntity(Class entityClass) {
		BaseMapper baseMapper = null;
		if (null != entityClass) {
			String entityClassName = entityClass.getSimpleName();
			if (entityClassName.endsWith("Entity")) {
				StringBuilder sb = new StringBuilder();
				sb.append(entityClassName.replace("Entity", ""));
				RText rt=new RText();
				String entityServiceImpl = rt.toLowerCaseFirstOne(sb.append("Mapper").toString());
				ClassLoader ccl = Thread.currentThread().getContextClassLoader();
				if (ccl != null) {
					WebApplicationContext ccpt = currentContextPerThread.get(ccl);
				}
				baseMapper = (BaseMapper) getBean(entityServiceImpl);
			}
		}
		return baseMapper;
	}
	/**
	 * 根据Beanid 获取bean
	 * 
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
		ApplicationContext applicationContext = ApplicationContextRegister.getApplicationContext();
		//WebApplicationContext application = ContextLoader.getCurrentWebApplicationContext();
		Object bean = applicationContext.getBean(beanId);
		return bean;
	}
}
