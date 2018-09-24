package com.pwhTest.jdbcTest;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mysql.jdbc.*;
import com.mysql.jdbc.Driver;

class JdbcTest{
	public static void main(String args[]){

//        String driver = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false";
//        String username = "root";
//        String password = "qw#$%ersd*N(fhbsduoirt%^&yh,ml;dsm;lv%^&*IObnszfvb,.asd";
//        Connection conn = null;
//
//        try {
//            Class.forName(driver); //classLoader,加载对应驱动//(1)
//            conn = (Connection) DriverManager.getConnection(url, username, password);//进行链接(2)
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {//这异常要经常处理!(3)
//            e.printStackTrace();
//        }

        System.out.println("getall:");
        getAll();

        System.out.println("insert:");
        insert(new Student("A", "Male", "1"));
        insert(new Student("B", "Male", "2"));
        insert(new Student("C", "Male", "3"));

        System.out.println("getall:");
        getAll();


        System.out.println("update:");
        update(new Student("B", "", "7"));

		System.out.println("delete:");
        delete("C");

		System.out.println("getall:");
        getAll();


		System.out.println("afsadfg");
	}


    //函数功能:链接数据库,返回Connection实例
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "059030849";
        Connection conn = null;


        /**(1)
         * 要获取数据库联机必须有一下几个动作:
         * (1)注册Driver操作对象
         * (2)取得Connection操作对象
         * (3)关闭Connection操作对象
         *
         * 其中(1)使用JDBC时,要求加载.class文档的方式有4种
         * (1)使用Class.getName();
         * (2)自行建立Driver接口操作类的实例(registerDriver)
         * (3)启动JVM时,指定jdbc.drivers属性
         * (4)设定JAR中/services/java.drivers属性
         */

        /**(2)
         * Connection操作对象:
         * Connection接口的操作对象时数据库联机代表对象,要取得Connection操作对象,可以通过DriverManager的getConnection();
         * Connection conn = DriverManager.getConnection(jdbcURL, username, password);
         *
         * jdbcURL:定义了链接数据库时的协议,子协议,数据源识别()
         * mysql为例
         * 子协议名称:mysql
         * 数据源识别主要 标出数据库地址,端口号, 名称, 用户, 密码
         *
         */

        /**
         * (3)
         * SQLException: 数据库操作过程中发生错误的代表对象,
         * SQLExecption是受检异常(Checked Exception)必须TryCatchFinally
         * 在异常时关闭相关资源
         *
         */

        //驱动注册:DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //实际上很少自己撰写上述代码进行动作,只通过forName()就可以进行动态加载驱动程序类

        try {
            Class.forName(driver); //classLoader,加载对应驱动//(1)
            conn = (Connection) DriverManager.getConnection(url, username, password);//进行链接(2)
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {//这异常要经常处理!(3)
            e.printStackTrace();
        }
        return conn;//返回Connection对象
    }



	//增
    private static int insert(Student student) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into students (Name,Sex,Age) values(?,?,?)";//(4)
        //创建PreparedStatment实例
        PreparedStatement pstmt;
        try {
            //设置sql语句
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSex());
            pstmt.setString(3, student.getAge());
            //执行
            i = pstmt.executeUpdate();//这玩意应该是返回affected row(s) 受影响列
            pstmt.close();//关
            conn.close();//关
        } catch (SQLException e) {//处理异常
            e.printStackTrace();
        }
        return i;
    }

    /**
     *
     * @param student
     * @return
     * 改
     */

    private static int update(Student student) {

        Connection conn = getConn();
        int i = 0;
        String sql = "update students set Age='" + student.getAge() + "' where Name='" + student.getName() + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }



	/**
    *删
    */

    private static int delete(String name) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from students where Name='" + name + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }



	private static Integer getAll() {
        Connection conn = getConn();
        String sql = "select * from students";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
             System.out.println("+----------------------------+");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
             System.out.println("+----------------------------+");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}