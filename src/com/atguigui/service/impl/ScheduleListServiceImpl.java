package com.atguigui.service.impl;

import com.atguigui.bean.ScheduleList;
import com.atguigui.dao.ScheduleListDao;
import com.atguigui.dao.impl.ScheduleListDaoImpl;
import com.atguigui.service.ScheduleListService;

import java.util.List;

public class ScheduleListServiceImpl implements ScheduleListService {

    //调取dao层
    ScheduleListDao listDao = new ScheduleListDaoImpl();

    @Override
    public List<ScheduleList> getList(String userid) {
        return listDao.getList(userid);
    }
}
