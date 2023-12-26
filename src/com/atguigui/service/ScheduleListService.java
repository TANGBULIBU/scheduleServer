package com.atguigui.service;

import com.atguigui.bean.ScheduleList;

import java.util.List;

public interface ScheduleListService {
  public List<ScheduleList> getList(String userid);
}
