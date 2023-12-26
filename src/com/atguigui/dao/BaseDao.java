package com.atguigui.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author 袁海鹏
 * @Create 10:23
 */

public class BaseDao<T> {
     //1.变量声明
    protected  Connection connection=null;
    protected  PreparedStatement pps =null;
    protected  ResultSet resultSet =null;

    //创建一个属性(*.properties)文件，单独保存连接数据库的信息
    //该文件的存值特点: key=value 每行只写一组key-value
    //*.properties属性文件的保存位置: 项目名下创建resources文件夹，设置该文件夹为资源文件夹，再创建属性文件
    static   DataSource dataSource =null;
    //2.拆解连接数据库的步骤到每个方法中
    static {
        try {
            //读取属性文件-通过类加载器
            InputStream inputStream = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            //数据库连接池-德鲁伊
          /* dataSource.setUrl(properties.getProperty("url"));
           dataSource.setPassword(properties.getProperty("pass"));
           dataSource.setUsername(properties.getProperty("username"));
           dataSource.setDriverClassName(properties.getProperty("driverClass"));*/
            //特色场景: 当属性文件中的key=德鲁伊中属性的名称时，可以自动赋值
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //2.2 获取连接
    protected  Connection createConnection(){
        try {
            connection=dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  connection;
    }
    //2.3 准备sql,获取发射器(预状态通道)
    protected  PreparedStatement createPps(String sql){
        try {
            pps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pps;
    }

    //2.4 绑定占位的值,动态参数传递过来以后是数组类型
    // params(null) params("")
    protected  void params(Object... args){
        try {
            if(args!=null&&args.length>0){
                for(int i=0;i<args.length;i++) {
                    pps.setObject(i + 1, args[i]);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //2.5 执行sql
    //2.5.1 执行查询的方法  Class cla 表示resultSet 结果集封装的实体类
    public T queryForObject(String sql,Class<T> cla,Object... args){
        T o =null;
        try {
            createConnection();
            createPps(sql);
            params(args);
            resultSet = pps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();//获取结果集中列相关的信息
            while(resultSet.next()){
                //1.先创建对象
                 o = cla.newInstance();
                //2.先取出对应列的值,给属性赋值（实体类的命名: 列名/列的别名=属性名）
                //2.1 获取结果集中列的数量
                int columnCount = metaData.getColumnCount();
                //2.2 获取每列的数据
                for(int i=1;i<=columnCount;i++){
                    String columnLabel = metaData.getColumnLabel(i);//获取列名
                    //给属性赋值
                    Field field = cla.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(o,resultSet.getObject(i));
                }
            }
            return o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            closeAll();
        }
    }
    //2.5.4 查询单行单列的结果
    public Object queryForOne(String sql,Object... args){
        Object object =null;
        try {
            createConnection();
            createPps(sql);
            params(args);
            resultSet = pps.executeQuery();
            while(resultSet.next()){
                object = resultSet.getObject(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll();
        }
        return object;
    }

    //2.5.3 查询多行数据
    public List<T> queryForList(String sql,Class<T> cla,Object... args){
        List<T> list=new ArrayList<T>();
        try {
            createConnection();
            createPps(sql);
            params(args);
            resultSet = pps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();//获取结果集中列相关的信息
            while(resultSet.next()){
                //1.先创建对象
                T o = cla.newInstance();
                //2.先取出对应列的值,给属性赋值（实体类的命名: 列名/列的别名=属性名）
                //2.1 获取结果集中列的数量
                int columnCount = metaData.getColumnCount();
                //2.2 获取每列的数据
                for(int i=1;i<=columnCount;i++){
                    String columnLabel = metaData.getColumnLabel(i);//获取列名
                    //给属性赋值
                    Field field = cla.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(o,resultSet.getObject(i));
                }
                //2.3 将对象赋值到集合中
                list.add(o);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            closeAll();
        }
    }

    //2.5.2 执行增删改的方法
    public int update(String sql,Object... args){
        try {
            createConnection();
            createPps(sql);
            params(args);
            int i = pps.executeUpdate();
            return i;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll();
        }
    }
    //2.6关闭资源
    public  void closeAll(){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pps != null) {
                pps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
