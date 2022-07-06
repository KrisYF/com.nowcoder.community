package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.security.krb5.internal.PAData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello Spring Boot";
    }

    @ResponseBody
    @RequestMapping("/data")
    public String getData(){
        return alphaService.find();
    }

    // Spring MVC演示
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod()); //获取请求方法名
        System.out.println(request.getServletPath()); //获取请求的路径
        Enumeration<String> enumeration = request.getHeaderNames(); //获取消息头，key，value键值对
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code")); //获取属性

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8"); //设置响应的类型(html),并设置字符格式
        try(
                PrintWriter printWriter = response.getWriter(); //获取响应的数据流
                ) {

            printWriter.write("<h1>牛客网</h1>"); //对网页进行打印,输出一级标题牛客网
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //GET请求，用于从服务器获取某些请求,两种传参方式
    //查询所有的学生，路径为students?current=1&limit=20  当前页和每页最多显示多少数据
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //参数成为路径的一部分
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "one student";
    }

    //POST请求，浏览器向服务器提交数据
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {

        System.out.println(name);
        System.out.println(age);
        return "保存成功";
    }


    //response响应html数据
    //方式一
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "郑爱华");
        modelAndView.addObject("age","37");
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    //方式二
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "安徽大学");
        model.addAttribute("age", 120);
        return "/demo/view";
    }

    //向浏览器响应json数据（在异步请求中）
    //当前网页不刷新，但访问了服务器

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "12");
        map.put("salary", "100");
        return map;
    }

    @RequestMapping(path = "/empAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmpAll() {
        List<Map<String, Object>> ans = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "12");
        map.put("salary", "100");
        ans.add(map);

        map = new HashMap<>();
        map.put("name", "李四");
        map.put("age", "15");
        map.put("salary", "50");
        ans.add(map);

        map = new HashMap<>();
        map.put("name", "王五");
        map.put("age", "50");
        map.put("salary", "1000");
        ans.add(map);

        return ans;
    }
}
