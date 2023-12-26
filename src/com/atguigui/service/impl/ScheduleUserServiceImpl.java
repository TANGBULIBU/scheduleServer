package com.atguigui.service.impl;

import com.atguigui.bean.ScheduleUser;
import com.atguigui.dao.ScheduleUserDao;
import com.atguigui.dao.impl.ScheduleUserDaoImpl;
import com.atguigui.service.ScheduleUserService;

public class ScheduleUserServiceImpl implements ScheduleUserService {
    ScheduleUserDao userDao = new ScheduleUserDaoImpl();

    @Override
    public ScheduleUser login(ScheduleUser user) {
        return userDao.login(user);
    }

    @Override
    public int insertUser(ScheduleUser user) {
        return userDao.insertUser(user);
    }

    @Override
    public ScheduleUser checkUname(String name) {
        return userDao.checkUname(name);
    }


}
