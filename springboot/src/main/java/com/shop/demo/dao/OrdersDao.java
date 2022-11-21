package com.shop.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import com.shop.demo.entity.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 */
public interface OrdersDao {

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
     * @param orders 实例对象
     * @return 对象列表
     */
    List<Map<String, Object>> queryAll(Orders orders);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int insert(Orders orders);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int update(Orders orders);


    /**
     * 修改数据-设置发货状态
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int updatestatus(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 影响行数
     */
    int deleteById(String code);


    /**
     * 自减库存
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int reduce(Orders orders);
}
