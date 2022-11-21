package com.shop.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import com.shop.demo.entity.Addrs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 收货地址表
 */
public interface AddrsDao {

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
     * @param addrs 实例对象
     * @return 对象列表
     */
    List<Map<String, Object>> queryAll(Addrs addrs);

    /**
     * 新增数据
     *
     * @param addrs 实例对象
     * @return 影响行数
     */
    int insert(Addrs addrs);

    /**
     * 修改数据
     *
     * @param addrs 实例对象
     * @return 影响行数
     */
    int update(Addrs addrs);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 设置用户所有地址为非默认
     *
     * @param uid
     * @return 影响行数
     */
    int allnodefault(Integer uid);

}
