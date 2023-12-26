package com.atguigui.dao;

import com.atguigui.bean.ScheduleList;

import java.util.List;

public interface ScheduleListDao {
    public List<ScheduleList> getList(String  userid);
 }
