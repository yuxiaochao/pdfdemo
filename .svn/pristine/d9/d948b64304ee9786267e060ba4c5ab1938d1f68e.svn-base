package com.base.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shop.utils.ApplicationContextUtil;
import com.shop.utils.ThreadUtils;

/**
 * 公共service实现模板，如果有改动可以在子类继承修改
 * 
 * @author baiyu
 *
 * @param <T>
 *            实体类类型
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private Class<?> entityClass = ThreadUtils.getReflectUtil().getGenericParmeterType(this.getClass());

	/**
	 * 根据ID查询实体类
	 * 
	 * @param id
	 * @return
	 */
	public T findById(String id) {
		T t = getMapper().findById(id);
		return t;
	}

	/**
	 * 根据ID查询实体类列表
	 * 
	 * @param id
	 * @return
	 */
	public List<T> findByIdForList(List<String> list) {
		List<T> entityList = getMapper().findByIdForList(list);
		return entityList;
	}

	/**
	 * 查询所有实体类
	 * 
	 * @return
	 */
	public List<T> findAll() {
		List<T> entityList = getMapper().findAll();
		return entityList;
	}
	@Override
    public Page<T> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return getMapper().findByPage();
    }

	/**
	 * 根据实体类进行查询
	 * 
	 * @param t
	 * @return
	 */
	public T findByEntity(T t) {
		T _t = null;
		PageHelper.startPage(1, 1);
		List<T> etityList = getMapper().findByCriteria(t);
		if (null != etityList && etityList.size() > 0) {
			_t = etityList.get(0);
		}
		return _t;
	}

	/**
	 * 根据条件查询
	 * 
	 * @param Topic
	 * @return
	 */
	public List<T> findByCriteria(T t) {
		return getMapper().findByCriteria(t);
	}

	/**
	 * 根据条件统计
	 * 
	 * @param topic
	 * @return
	 */
	public long countByCriteria(T t) {
		return getMapper().countByCriteria(t);
	}

	/**
	 * 插入对象
	 * 
	 * @param topic
	 * @throws InvocationTargetException
	 */
	public String insert(T t) throws SecurityException, IllegalArgumentException, ParseException, NoSuchFieldException,
			IllegalAccessException, InvocationTargetException {
		String insertMsg = FAIL;
		
		int n = getMapper().insert(t);
		if (n > 0) {
			insertMsg = SUCCESS;
		}
		return insertMsg;
	}

	/**
	 * 批量插入对象
	 * 
	 * @param list
	 * @return
	 * @throws InvocationTargetException
	 */
	public String insertBatch(List<T> entityList) throws SecurityException, IllegalArgumentException, ParseException,
			NoSuchFieldException, IllegalAccessException, InvocationTargetException {
		String insertBatchMsg = SUCCESS;
		if (entityList.size() == 0) {
			insertBatchMsg = FAIL;
		} else {

			int step = 500;
			int max = (int) Math.ceil(entityList.size() * 1.0 / step);
			for (int i = 0; i < max; i++) {
				int fromIndex = i * step;
				int toIndex = (i + 1) * step;
				if (i + 1 == max) {
					toIndex = entityList.size();
				}
				List subList = entityList.subList(fromIndex, toIndex);
				long n = getMapper().insertBatch(subList);

				if (n == 0) {
					insertBatchMsg = FAIL;
				} else {
					insertBatchMsg = SUCCESS;
				}
			}

		}
		return insertBatchMsg;
	}

	/**
	 * 更新单个对象
	 * 
	 * @param Topic
	 * @throws InvocationTargetException
	 */
	public String update(T t) throws SecurityException, IllegalArgumentException, ParseException, NoSuchFieldException,
			IllegalAccessException, InvocationTargetException {
		String updateMsg = FAIL;
		int n = getMapper().update(t);
		if (n > 0) {
			updateMsg = SUCCESS;
		}
		return updateMsg;
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public String deleteById(String id) {
		String deleteMsg = FAIL;

		int n = getMapper().deleteById(id);
		if (n > 0) {
			deleteMsg = SUCCESS;
		}

		return deleteMsg;
	}

	/**
	 * 批量删除
	 * 
	 * @param list
	 *            传入需要删除id的list
	 * @return
	 */
	public String deleteBatch(List<String> list) {
		String deleteBatchMsg = SUCCESS;
		if (list.size() == 0) {
			deleteBatchMsg = FAIL;
		} else {
			int step = 100;
			int max = (int) Math.ceil(list.size() * 1.0 / step);
			for (int i = 0; i < max; i++) {
				int fromIndex = i * step;
				int toIndex = (i + 1) * step;
				if (i + 1 == max) {
					toIndex = list.size();
				}
				List subList = list.subList(fromIndex, toIndex);
				long n = getMapper().deleteBatch(subList);

				if (n == 0) {
					deleteBatchMsg = FAIL;
				}
			}

		}
		return deleteBatchMsg;
	}

	/**
	 * 根据类删除
	 * 
	 * @param userresourcerelationEntity
	 */
	public String deleteByEntity(T t) {
		String deleteMsg = FAIL;
		int n = getMapper().deleteByEntity(t);
		if (n > 0) {
			deleteMsg = SUCCESS;
		}
		return deleteMsg;
	}
	/**
	 * 
	 * @return
	 */
	public BaseMapper<T> getMapper() {
		BaseMapper<T> baseMapper = ApplicationContextUtil.getMapperForEntity(entityClass);
		return baseMapper;
	}



	private byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}

	private Object toObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}
}
