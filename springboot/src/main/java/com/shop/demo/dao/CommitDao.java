package com.shop.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import com.shop.demo.entity.Commit;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 评论表
 */
public interface CommitDao {

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
     * @param commit 实例对象
     * @return 对象列表
     */
    List<Map<String, Object>> queryAll(Commit commit);

    /**
     * 新增数据
     *
     * @param commit 实例对象
     * @return 影响行数
     */
    int insert(Commit commit);

    /**
     * 修改数据
     *
     * @param commit 实例对象
     * @return 影响行数
     */
    int update(Commit commit);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
