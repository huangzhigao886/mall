package com.service.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goods.pojo.Brand;
import com.service.goods.dao.BrandMapper;
import com.service.goods.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: huangzhigao
 * @Date: 2020/3/29 14:15
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAllBrand() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findBrandById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Brand> findByBrand(Brand brand) {
        //自定义条件查询对象
        Example example = definedExample(brand);
        return brandMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Brand> findByPage(Integer pageNum, Integer pageSize) {
        //分页实现，后面紧跟集合查询
        PageHelper.startPage(pageNum, pageSize);
        List<Brand> brands = brandMapper.selectAll();
        return new PageInfo<>(brands, 10);
    }

    @Override
    public PageInfo<Brand> findByExamplePage(Brand brand, Integer pageNum, Integer pageSize) {
        //分页实现，后面紧跟集合查询
        PageHelper.startPage(pageNum, pageSize);
        Example example = definedExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);
        return new PageInfo<>(brands, 10);
    }

    @Override
    public void addBrand(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }


    /**
     * 自定义条件查询对象
     *
     * @param brand
     * @return
     */
    public Example definedExample(Brand brand) {
        //自定义条件查询对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //拼接like后面的语句
        if (brand != null) {
            if (StringUtils.isNotEmpty(brand.getName())) {
                // name like %h%
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            if (StringUtils.isNotEmpty(brand.getLetter())) {
                //letter = 'A'
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }

        return example;
    }
}
