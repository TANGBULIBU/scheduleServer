package com.atguigui.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleList {
    private Integer sid;
    private Integer userid;
    private String content;
    private Integer completed;
}
