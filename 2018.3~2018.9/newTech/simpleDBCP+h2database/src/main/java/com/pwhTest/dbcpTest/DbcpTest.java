package com.pwhTest.dbcpTest;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mysql.jdbc.*;
import com.mysql.jdbc.Driver;

class DbcpTest{
	public static void main(String args[]){




//        String driver = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false";
//        String username = "root";
//        String password = "qw#$%ersd*N(fhbsduoirt%^&yh,ml;dsm;lv%^&*IObnszfvb,.asd";
//        Connection conn = null;
//
//        try {
//            Class.forName(driver); //classLoader,���ض�Ӧ����//(1)
//            conn = (Connection) DriverManager.getConnection(url, username, password);//��������(2)
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {//���쳣Ҫ��������!(3)
//            e.printStackTrace();
//        }
/*
		createTable();
*/
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


    //��������:�������ݿ�,����Connectionʵ��
    private static Connection getConn() {

        return KCYDBCPUtil.getConnection();//����Connection����
    }

// �ο� https://blog.csdn.net/haining711/article/details/8481397

	//��
    private static void createTable() {
        Connection conn = getConn();

		StringBuffer sb=new StringBuffer();
		sb.append("create table students");
		sb.append("(");
		sb.append("id int primary key auto_increment,");
		sb.append("Name varchar(20) null,");
		sb.append("Sex varchar(20) null,");
		sb.append("Age varchar(20) null");
		sb.append(")");


        //����PreparedStatmentʵ��
        PreparedStatement pstmt;
        try {
            //����sql���
            pstmt = (PreparedStatement) conn.prepareStatement(sb.toString());
            pstmt.execute();
            pstmt.close();//��
            conn.close();//��
        } catch (SQLException e) {//�����쳣
            e.printStackTrace();
        }
    }

    private static void dropTable() {
        Connection conn = getConn();

		StringBuffer sb=new StringBuffer();
		sb.append("drop table grade");


        //����PreparedStatmentʵ��
        PreparedStatement pstmt;
        try {
            //����sql���
            pstmt = (PreparedStatement) conn.prepareStatement(sb.toString());
            pstmt.execute();
            pstmt.close();//��
            conn.close();//��
        } catch (SQLException e) {//�����쳣
            e.printStackTrace();
        }
    }



	//��
    private static int insert(Student student) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into students (Name,Sex,Age) values(?,?,?)";//(4)
        //����PreparedStatmentʵ��
        PreparedStatement pstmt;
        try {
            //����sql���
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSex());
            pstmt.setString(3, student.getAge());
            //ִ��
            i = pstmt.executeUpdate();//������Ӧ���Ƿ���affected row(s) ��Ӱ����
            pstmt.close();//��
            conn.close();//��
        } catch (SQLException e) {//�����쳣
            e.printStackTrace();
        }
        return i;
    }

    /**
     *
     * @param student
     * @return
     * ��
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
    *ɾ
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