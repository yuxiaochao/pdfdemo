package com.base.common;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * 基本Service层模板，其他到层类继承生成模板
 * 
 * @author baiyu
 *
 * @param <T>
 */
public interface BaseService<T> {
	// 成功
	public static final String SUCCESS = "SUCCESS";
	// 失败
	public static final String FAIL = "FAIL";

	/**
	 * 根据id查寻对象
	 * 
	 * @param id
	 * @return
	 */
	public T findById(String id);
	
	/**
	 * 根据ID查询实体类列表
	 * 
	 * @param id
	 * @return
	 */
	public List<T> findByIdForList(List<String> list);

	/**
	 * 查询所有对象
	 * 
	 * @return
	 */
	public List<T> findAll();
	/**
     * 分页查询
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<T> findByPage(int pageNo, int pageSize);


	/**
	 * 根据实体类进行查询
	 * 
	 * @param t
	 * @return
	 */
	public T findByEntity(T t);

	/**
	 * 根据条件查询
	 * 
	 * @param t
	 * @return
	 */
	public List<T> findByCriteria(T t);

	/**
	 * 根据条件统计
	 * 
	 * @param t
	 * @return
	 */
	public long countByCriteria(T t);

	/**
	 * 插入对象
	 * 
	 * @param t
	 */
	public String insert(T t) throws SecurityException, IllegalArgumentException, ParseException, NoSuchFieldException,
			IllegalAccessException, InvocationTargetException;

	/**
	 * 批量插入对象
	 * 
	 * @param list
	 * @return
	 */
	public String insertBatch(List<T> list) throws SecurityException, IllegalArgumentException, ParseException,
			NoSuchFieldException, IllegalAccessException, InvocationTargetException;

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public String deleteById(String id);

	/**
	 * 根据类删除
	 * 
	 * @param userresourcerelationEntity
	 */
	public String deleteByEntity(T t);

	/**
	 * 批量删除
	 * 
	 * @param list
	 *            传入需要删除id的list
	 * @return
	 */
	public String deleteBatch(List<String> list);

	/**
	 * 根据对象更新
	 * 
	 * @param t
	 */
	public String update(T t) throws SecurityException, IllegalArgumentException, ParseException, NoSuchFieldException,
			IllegalAccessException, InvocationTargetException;


}
