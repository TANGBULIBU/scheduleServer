package com.atguigui.dao;

import com.atguigui.bean.ScheduleUser;

public interface ScheduleUserDao {
    public ScheduleUser login(ScheduleUser user);
    public int insertUser(ScheduleUser user);

    public ScheduleUser checkUname(String name);
}
