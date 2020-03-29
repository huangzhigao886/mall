package com.service.goods.controller;

import com.github.pagehelper.PageInfo;
import com.goods.pojo.Brand;
import com.service.goods.service.BrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: huangzhigao
 * @Date: 2020/3/29 14:16
 */
@RestController
@RequestMapping(value = "/brand")
/**
 * 跨域 A域名访问B域名的数据
 *          域名或者请求端口或者协议不一致时，就跨域了
 *          www.baidu.com  html  www.baidu.cn
 *          www.baidu.com:100  html  www.baidu.com:111
 *          https://www.baidu.com html http://www.baidu.com
 */
@CrossOrigin //跨域
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 查询所有品牌
     */
    @GetMapping(value = "/findAllBrand")
    public Result<List<Brand>> findAllBrand() {
        List<Brand> allBrand = brandService.findAllBrand();
        Result<List<Brand>> listResult = new Result<>(true, StatusCode.OK, "查询所有品牌成功", allBrand);
        return listResult;
    }


    /**
     * 根据主键查询品牌
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/findBrandById/{id}")
    public Result<Brand> findBrandById(@PathVariable("id") Integer id) {
        Brand brand = brandService.findBrandById(id);
        Result<Brand> result = new Result<>(true, StatusCode.OK, "查询品牌成功", brand);
        return result;
    }


    /**
     * 增加品牌
     */
    @PostMapping(value = "/addBrand")
    public Result addBrand(@RequestBody Brand brand) {
        brandService.addBrand(brand);
        return new Result(true, StatusCode.OK, "插入成功");
    }


    /**
     * 更新商品
     *
     * @param brand
     * @return
     */
    @PutMapping(value = "updateBrand/{id}")
    public Result updateBrand(@PathVariable("id") Integer id, @RequestBody Brand brand) {
        brand.setId(id);
        brandService.updateBrand(brand);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    /**
     * 条件查询品牌
     *
     * @param brand
     * @return
     */
    @PostMapping(value = "findByBrand")
    public Result<List<Brand>> findByBrand(@RequestBody Brand brand) {
        List<Brand> brands = brandService.findByBrand(brand);
        Result<List<Brand>> result = new Result(true, StatusCode.OK, "条件查询品牌成功", brands);
        return result;
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Brand> byPage = brandService.findByPage(pageNum, pageSize);
        Result<PageInfo<Brand>> result = new Result(true, StatusCode.OK, "分页查询品牌成功", byPage);
        return result;
    }


    /**
     * 分页查询
     *
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "findByExamplePage/{pageNum}/{pageSize}")
    public Result<PageInfo<Brand>> findByExamplePage(@RequestBody Brand brand, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Brand> byPage = brandService.findByExamplePage(brand, pageNum, pageSize);
        Result<PageInfo<Brand>> result = new Result(true, StatusCode.OK, "分页带条件查询品牌成功", byPage);
        return result;
    }
}
