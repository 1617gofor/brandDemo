package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImp implements BrandService {
    //1获取工厂
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     * @return
     */
    public List<Brand> selectAll() {
        //获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brands = mapper.selectAll();
        //释放资源
        sqlSession.close();
        return brands;
    }

    public void add(Brand brand) {
        //获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.add(brand);
        sqlSession.commit();//对数据库有改动的药提交事务

        sqlSession.close();
    }

    public void deleteByIds(int[] ids) {
        //获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteByIds(ids);
        sqlSession.commit();//对数据库有改动的药提交事务

        sqlSession.close();
    }

    public void update(Brand brand) {
        //获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.update(brand);
        sqlSession.commit();//对数据库有改动的药提交事务

        sqlSession.close();
    }

    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //计算起始索引
        int begin=(currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;

        List<Brand> rows = mapper.selectByPage(begin, size);
        int count = mapper.selectTotalCount();

        PageBean<Brand> brandPageBean = new PageBean<Brand>();
        brandPageBean.setRows(rows);
        brandPageBean.setTotalCount(count);

        sqlSession.close();

        return brandPageBean;
    }

    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //计算起始索引
        int begin=(currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        String brandName = brand.getBrandName();
        if(brandName!=null&&brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if(companyName!=null&&companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }

        List<Brand> rows = mapper.selectByPageAndCondition(begin, size,brand);
        int count = mapper.selectTotalCountByCondition(brand);

        PageBean<Brand> brandPageBean = new PageBean<Brand>();
        brandPageBean.setRows(rows);
        brandPageBean.setTotalCount(count);

        sqlSession.close();

        return brandPageBean;
    }

}
