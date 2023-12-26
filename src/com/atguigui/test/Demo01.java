package com.atguigui.test;

import com.atguigui.bean.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Demo01 {
    public static void main(String[] args) throws Exception{
        //实现java对象(JSON对象)和json字符串的转换
        //1.java=>json字符串 （默认的转换规则: 属性名=json的key,属性值=json的value）
        Student student = new Student("s1001","王五",18);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(student);
        System.out.println(s);
        //2.json字符串转java对象,实际开发中json字符串是由前端发送过来
        Student student1 = objectMapper.readValue(s, Student.class);
        System.out.println(student1);
    }
}
