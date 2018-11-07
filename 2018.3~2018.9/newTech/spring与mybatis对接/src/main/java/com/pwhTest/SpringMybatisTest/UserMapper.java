package com.pwhTest.SpringMybatisTest;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper{

    @Select("select * from user where id=#{id}")
    public User getUser(int id);

}