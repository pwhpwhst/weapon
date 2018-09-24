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
//            Class.forName(driver); //classLoader,���ض�Ӧ����//(1)
//            conn = (Connection) DriverManager.getConnection(url, username, password);//��������(2)
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {//���쳣Ҫ��������!(3)
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


    //��������:�������ݿ�,����Connectionʵ��
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "059030849";
        Connection conn = null;


        /**(1)
         * Ҫ��ȡ���ݿ�����������һ�¼�������:
         * (1)ע��Driver��������
         * (2)ȡ��Connection��������
         * (3)�ر�Connection��������
         *
         * ����(1)ʹ��JDBCʱ,Ҫ�����.class�ĵ��ķ�ʽ��4��
         * (1)ʹ��Class.getName();
         * (2)���н���Driver�ӿڲ������ʵ��(registerDriver)
         * (3)����JVMʱ,ָ��jdbc.drivers����
         * (4)�趨JAR��/services/java.drivers����
         */

        /**(2)
         * Connection��������:
         * Connection�ӿڵĲ�������ʱ���ݿ������������,Ҫȡ��Connection��������,����ͨ��DriverManager��getConnection();
         * Connection conn = DriverManager.getConnection(jdbcURL, username, password);
         *
         * jdbcURL:�������������ݿ�ʱ��Э��,��Э��,����Դʶ��()
         * mysqlΪ��
         * ��Э������:mysql
         * ����Դʶ����Ҫ ������ݿ��ַ,�˿ں�, ����, �û�, ����
         *
         */

        /**
         * (3)
         * SQLException: ���ݿ���������з�������Ĵ������,
         * SQLExecption���ܼ��쳣(Checked Exception)����TryCatchFinally
         * ���쳣ʱ�ر������Դ
         *
         */

        //����ע��:DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //ʵ���Ϻ����Լ�׫д����������ж���,ֻͨ��forName()�Ϳ��Խ��ж�̬��������������

        try {
            Class.forName(driver); //classLoader,���ض�Ӧ����//(1)
            conn = (Connection) DriverManager.getConnection(url, username, password);//��������(2)
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {//���쳣Ҫ��������!(3)
            e.printStackTrace();
        }
        return conn;//����Connection����
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