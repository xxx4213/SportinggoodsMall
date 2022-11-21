package com.shop.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import com.shop.demo.entity.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 */
public interface TypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Map<String, Object> queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param type 实例对象
     * @return 对象列表
     */
    List<Map<String, Object>> queryAll(Type type);

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 影响行数
     */
    int insert(Type type);

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 影响行数
     */
    int update(Type type);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
