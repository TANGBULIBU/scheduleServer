package com.atguigui.dao.impl;

import com.atguigui.bean.ScheduleList;
import com.atguigui.dao.BaseDao;
import com.atguigui.dao.ScheduleListDao;

import java.util.List;

public class ScheduleListDaoImpl extends BaseDao<ScheduleList>
        implements ScheduleListDao {

    @Override
    public List<ScheduleList> getList(String userid) {
        //sql语句
        String sql = "select * from schedulelist where userid=?";
        return queryForList(sql, ScheduleList.class,userid);
    }
}
