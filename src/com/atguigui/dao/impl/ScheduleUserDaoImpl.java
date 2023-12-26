package com.atguigui.dao.impl;

import com.atguigui.bean.ScheduleUser;
import com.atguigui.dao.BaseDao;
import com.atguigui.dao.ScheduleUserDao;

public class ScheduleUserDaoImpl extends BaseDao<ScheduleUser>
        implements ScheduleUserDao {
    @Override
    public ScheduleUser login(ScheduleUser user) {
        //向dao层
        String sql = "select * from scheduleuser where username=? and userPwd=?";
        ScheduleUser u1 = queryForObject(sql, ScheduleUser.class, user.getUsername(),user.getUserPwd());
        return u1;
    }

    @Override
    public int insertUser(ScheduleUser user) {
        String sql = "insert into  scheduleuser  values(null,?,?)";
        return update(sql, user.getUsername(), user.getUserPwd());
    }

    @Override
    public ScheduleUser checkUname(String name) {
        String sql="select * from scheduleuser where username=?";
        ScheduleUser scheduleUser = queryForObject(sql, ScheduleUser.class, name);
        return scheduleUser;
    }


}
