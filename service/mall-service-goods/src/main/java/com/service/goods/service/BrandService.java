package com.service.goods.service;

import com.github.pagehelper.PageInfo;
import com.goods.pojo.Brand;

import java.util.List;

/**
 * @Author: huangzhigao
 * @Date: 2020/3/29 14:14
 */
public interface BrandService {

    /**
     * 查询所有品牌
     *
     * @return
     */
    List<Brand> findAllBrand();

    /**
     * 根据ID查询品牌
     *
     * @param id
     * @return
     */
    Brand findBrandById(Integer id);

    /**
     * 根据品牌查询
     *
     * @return
     */
    List<Brand> findByBrand(Brand brand);


    /**
     * 分页查询
     *
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @return
     */
    PageInfo<Brand> findByPage(Integer pageNum, Integer pageSize);

    /**
     * 分页+自定义查询
     *
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Brand> findByExamplePage(Brand brand, Integer pageNum, Integer pageSize);

    /**
     * 增加品牌
     *
     * @param brand
     */
    void addBrand(Brand brand);

    /**
     * 修改品牌
     *
     * @param brand
     */
    void updateBrand(Brand brand);


}
