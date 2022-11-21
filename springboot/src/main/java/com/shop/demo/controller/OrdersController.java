package com.shop.demo.controller;

import com.shop.demo.utils.AjaxResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.demo.entity.Orders;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.shop.demo.dao.OrdersDao;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单表
 */
@RestController
@RequestMapping("orders")
@Api(tags = "订单表")
public class OrdersController {
    /**
     * 对象
     */
    @Resource
    private OrdersDao ordersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "查询单条数据")
    @PostMapping("getone")
    public AjaxResult getone(Integer id) {
        return AjaxResult.success(ordersService.queryById(id));
    }

    /**
     * 查询列表数据
     *
     * @return 列表数据
     */
    @ApiOperation(value = "查询列表数据")
    @RequestMapping(value = "getlist", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult getlist(@RequestBody Orders orders) {
        PageHelper.startPage(orders.getOffset(), orders.getLimit());
        List<Map<String, Object>> list = ordersService.queryAll(orders);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    /**
     * 删除
     *
     * @return 是否成功
     */
    @ApiOperation(value = "删除数据")
    @PostMapping("del")
    public AjaxResult del(String code) {
        Integer row = ordersService.deleteById(code);
        return row > 0 ? AjaxResult.success() : AjaxResult.fail();
    }

    /**
     * 保存
     *
     * @return 是否成功
     */
    @ApiOperation(value = "保存数据")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@RequestBody Orders orders) {
        Integer row = 0;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        orders.setTime(sdf.format(d));
        if (orders.getId() == null) {
            //自减库存
            ordersService.reduce(orders);
            row = ordersService.insert(orders);
        } else {
            row = ordersService.update(orders);
        }
        return row > 0 ? AjaxResult.success() : AjaxResult.fail();
    }


    /**
     * 设置发货状态
     *
     * @return 是否成功
     */
    @ApiOperation(value = "设置发货状态")
    @RequestMapping(value = "updatestatus", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updatestatus(@RequestBody Orders orders) {
        Integer row = 0;
        row = ordersService.updatestatus(orders);
        return row > 0 ? AjaxResult.success() : AjaxResult.fail();
    }

}
