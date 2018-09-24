package com.pwhTest.dbcpTest;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mysql.jdbc.*;
import com.mysql.jdbc.Driver;

/**
 * Created by butter on 16-11-19.
 */

/**
 *
 * Student类,与数据表相同
 * 属性: Id, Name, Sex, Age
 * 方法: get(),set()
 */

public class Student {
    private String Id;
    private String Name;
    private String Sex;
    private String Age;

    Student(String Name, String Sex, String Age) {
        this.Id = null; //default
        this.Name = Name;
        this.Sex = Sex;
        this.Age = Age;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getAge() {
        return Age;
    }

    public void setage(String Age) {
        this.Age = Age;
    }
}