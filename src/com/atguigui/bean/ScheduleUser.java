package com.atguigui.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//实体类要和前端一致
public class ScheduleUser {
    private Integer uid;
    private String  username;
    private String  userPwd;
}
