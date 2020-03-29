package com.mall.file.controller;

import com.mall.file.bean.FdfsFile;
import com.mall.file.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.csource.common.MyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: huangzhigao
 * @Date: 2020/3/29 22:56
 */
@RestController
@RequestMapping("/update")
public class FileController {

    @PostMapping
    public Result uploadFile(@RequestParam("file") MultipartFile file) throws IOException, MyException {
        //调用fdfs工具类
        FdfsFile fdfsFile = new FdfsFile(
                file.getOriginalFilename(), // 文件名
                file.getBytes(),   //文件内容
                StringUtils.getFilenameExtension(file.getOriginalFilename()));   //文件后缀
        String[] update = FastDFSUtil.update(fdfsFile);
        //拼接访问地址
        String url = "http://192.168.1.200:8080/"+update[0]+"/"+update[1];
        return new Result(true, StatusCode.OK, "上传成功",url);
    }
}
