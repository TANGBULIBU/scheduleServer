package com.atguigui.bean;

public class Student {
    private String sid;
    private String sname;
    private Integer sage;

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                '}';
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public Student() {
    }

    public Student(String sid, String sname, Integer sage) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
    }
}