package com.atguigui.servlet;

import com.atguigui.bean.ScheduleList;
import com.atguigui.service.ScheduleListService;
import com.atguigui.service.impl.ScheduleListServiceImpl;
import com.atguigui.util.BaseServlet;

import com.atguigui.util.Result;
import com.atguigui.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/schedule/*")
public class ScheduleListServlet extends BaseServlet {
    ScheduleListService listService=new ScheduleListServiceImpl();
    //查询日程计划表
    protected void getlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //获取参数
        String userid = req.getParameter("userid");

        //调取service方法
        List<ScheduleList> list = listService.getList(userid);

        //返回结果
        Result<List<ScheduleList>> result = new Result<>(200,null,list );
        WebUtil.writeJson(resp,result);

    }

}
