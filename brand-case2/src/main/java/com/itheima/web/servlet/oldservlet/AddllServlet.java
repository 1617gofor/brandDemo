package com.itheima.web.servlet.oldservlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddllServlet extends HttpServlet {

    private BrandService brandService=new BrandServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
