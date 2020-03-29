package com.mall.file.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: huangzhigao
 * @Date: 2020/3/29 22:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FdfsFile implements Serializable {

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件内容
     */
    private byte[] content;
    /**
     * 文件后后缀
     */
    private String ext;
    /**
     * MD5摘要
     */
    private String md5;
    /**
     * 作者
     */
    private String author;


    public FdfsFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
}
