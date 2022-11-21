package com.shop.demo.controller;

import com.shop.demo.utils.AjaxResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.demo.entity.Addrs;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.shop.demo.dao.AddrsDao;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 收货地址表
 */
@RestController
@RequestMapping("addrs")
@Api(tags = "收货地址表")
public class AddrsController {
    /**
     * 对象
     */
    @Resource
    private AddrsDao addrsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "查询单条数据")
    @PostMapping("getone")
    public AjaxResult getone(Integer id) {
        return AjaxResult.success(addrsService.queryById(id));
    }

    /**
     * 查询列表数据
     *
     * @return 列表数据
     */
    @ApiOperation(value = "查询列表数据")
    @RequestMapping(value = "getlist", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult getlist(@RequestBody Addrs addrs) {
        PageHelper.startPage(addrs.getOffset(), addrs.getLimit());
        List<Map<String, Object>> list = addrsService.queryAll(addrs);
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
    public AjaxResult del(Integer id) {
        Integer row = addrsService.deleteById(id);
        return row > 0 ? AjaxResult.success() : AjaxResult.fail();
    }

    /**
     * 设置用户所有地址为非默认
     *
     * @return 是否成功
     */
    @ApiOperation(value = "删除数据")
    @PostMapping("allnodefault")
    public AjaxResult allnodefault(Integer uid) {
        Integer row = addrsService.allnodefault(uid);
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
    public AjaxResult save(@RequestBody Addrs addrs) {
        Integer row = 0;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        addrs.setTime(sdf.format(d));
        if (addrs.getId() == null) {
            row = addrsService.insert(addrs);
        } else {
            row = addrsService.update(addrs);
        }
        return row > 0 ? AjaxResult.success() : AjaxResult.fail();
    }

}
