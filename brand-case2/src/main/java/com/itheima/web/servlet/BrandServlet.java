package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private BrandService brandService=new BrandServiceImp();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1调用service查询
        List<Brand> brands = brandService.selectAll();
        //2转换为json
        String s = JSON.toJSONString(brands);
        //3写数据回前端
        //3.1信息中有中文，需要设置编码方法
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(s);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String s = reader.readLine();//获取json数据
        //转换为brand对象
        Brand brand = JSON.parseObject(s, Brand.class);
        //调用service
        brandService.add(brand);

        //执行成功就响应信息
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //接收品牌数据
        BufferedReader reader = request.getReader();
        String s = reader.readLine();//获取json数据
        //转换为int数组
        int[] ids = JSON.parseObject(s, int[].class);
        //调用service
        brandService.deleteByIds(ids);

        //执行成功就响应信息
        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String s = reader.readLine();//获取json数据
        //转换为brand对象
        Brand brand = JSON.parseObject(s, Brand.class);
        //调用service
        brandService.update(brand);

        //执行成功就响应信息
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收前端传来的数据----url?currentPage=xx&pageSize=xx
        String currentPage0 = request.getParameter("currentPage");
        String pageSize0 = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPage0);
        int pageSize = Integer.parseInt(pageSize0);

        //调用service查询
        PageBean<Brand> brandPageBean = brandService.selectByPage(currentPage, pageSize);

        //2转换为json
        String s = JSON.toJSONString(brandPageBean);
        //3写数据回前端
        //3.1信息中有中文，需要设置编码方法
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(s);
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收前端传来的数据----url?currentPage=xx&pageSize=xx
        String currentPage0 = request.getParameter("currentPage");
        String pageSize0 = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPage0);
        int pageSize = Integer.parseInt(pageSize0);

        //接收品牌数据(data段的数据就是用以下方法接收的)
        BufferedReader reader = request.getReader();
        String s = reader.readLine();//获取json数据
        //转换为brand对象
        Brand brand = JSON.parseObject(s, Brand.class);

        //调用service查询
        PageBean<Brand> brandPageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);

        //2转换为json
        String s1 = JSON.toJSONString(brandPageBean);

        //System.out.println(s1);
        //3写数据回前端
        //3.1信息中有中文，需要设置编码方法
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(s1);
    }
}
