package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 代替httpServlet,根据请求最后一段路径分发方法
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1获取请求路径
        String uri = req.getRequestURI();//brand-case2/brand/selectAll
        //2获取最后一段路径，即方法名
        int index=uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);

        //3执行方法
        //3.1获取BrandServlet字节码对象
        Class<? extends BaseServlet> acl = this.getClass();//this代表谁？--谁调用就代表谁,如BaseServlet的子类BrandServlet调用就代表它


        //3.2获取方法Method对象--通过反射获取
        try {
            Method method = acl.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //3.3执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }




    }
}
