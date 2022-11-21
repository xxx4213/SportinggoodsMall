package com.shop.demo.controller;

import com.shop.demo.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 上传方法
 *
 * @author yamlling
 * @since 2020-10-09 21:53:22
 */
@RestController
@RequestMapping("upload")
@Api(tags = "上传接口")
public class UploadController {

    /**
     * 上传
     */
    public final static String UPLOAD_PATH_PREFIX = "upload/";
    @ApiOperation(value = "上传")
    @PostMapping(headers = "Content-Type=multipart/form-data")
    public AjaxResult addvercon(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {

        if (uploadFile.isEmpty()) {
            //返回选择文件提示
            return AjaxResult.error("请选择上传文件");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        String format = sdf.format(new Date());
        //存放上传文件的文件夹
        File file = new File(realPath + format);
        if (!file.isDirectory()) {
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            uploadFile.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/upload/" + format + newName;
            return AjaxResult.success(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.error("上传失败");
    }

}
