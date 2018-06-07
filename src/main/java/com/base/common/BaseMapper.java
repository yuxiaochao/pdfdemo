package com.base.common;

import java.util.List;

import com.github.pagehelper.Page;

/**
 * 基本dao模板，其他到层类继承生成模板
 * 
 * @author baiyu
 *
 * @param <T>
 */
public interface BaseMapper<T> {

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
     * 分页查询数据
     * @return
     */
    Page<T> findByPage();
    
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
	 * 根据id删除
	 * 
	 * @param id
	 */
	public int deleteById(String id);
	
	/**
	 * 根据类删除
	 * 
	 * @param id
	 */
	public int deleteByEntity(T t);
	
	/**
	 * 根据ROLEID删除
	 * 删除角色权限
	 * 
	 * @param id
	 */
	public int deleteByRoleId(String id);

	/**
	 * 根据对象更新
	 * 
	 * @param t
	 */
	public int update(T t);

	/**
	 * 插入对象
	 * 
	 * @param t
	 */
	public int insert(T t);

	/**
	 * 批量插入对象
	 * 
	 * @param list
	 * @return
	 */
	public long insertBatch(List<T> list);

	/**
	 * 批量删除
	 * 
	 * @param list
	 *            传入需要删除id的list
	 * @return
	 */
	public long deleteBatch(List<String> list);

}
