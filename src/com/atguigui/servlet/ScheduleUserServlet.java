package com.atguigui.servlet;

import com.atguigui.bean.ScheduleUser;
import com.atguigui.service.impl.ScheduleUserServiceImpl;
import com.atguigui.util.BaseServlet;
import com.atguigui.util.Result;
import com.atguigui.util.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Value;

@WebServlet("/user/*")
public class ScheduleUserServlet extends BaseServlet {
    ScheduleUserServiceImpl userService = new ScheduleUserServiceImpl();

    //checkuname
    protected void checkuname(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("checkuname调用");
        //接收参数
        String uname = req.getParameter("uname");
        //调取service
        ScheduleUser user = userService.checkUname(uname);
        //返回数据
        String str=user==null?"用户名可用":"用户名被占用";
        Result<Object> result = new Result<>(200, str, null);
        WebUtil.writeJson(resp,result);


    }


        //toregist()
    protected void toregist(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("toregist调用");
        ScheduleUser user = WebUtil.readJson(req, ScheduleUser.class);
        System.out.println(user);

        //调取信息
        int i = userService.insertUser(user);

        String rs=i>0?"注册成功":"注册失败";

        //结果返回
        Result<Object> result = new Result<>(200, rs, null);
        WebUtil.writeJson(resp, result);

    }

    protected void tologin(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("tologin被调用");
        //接收请求传递过来的数据
        ScheduleUser user = WebUtil.readJson(req, ScheduleUser.class);
        System.out.println(user);

        //调取信息
        ScheduleUser rs_user = userService.login(user);

        //判断
        String rs = null;
        if (rs_user == null) {
            rs = "用户名或密码不正确";
        } else {
            rs = "登录成功";
        }

        //结果返回
        Result<Object> result = new Result<>(200, rs, rs_user);
        WebUtil.writeJson(resp, result);
    }
}
