package com.atguigui.service;

import com.atguigui.bean.ScheduleUser;

public interface ScheduleUserService {
    public ScheduleUser login(ScheduleUser user);

    public int insertUser(ScheduleUser user);

    public ScheduleUser checkUname(String name);
}
