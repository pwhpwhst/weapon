package com.pwhTest.MFTest.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pwhTest.MFTest.entity.ConditionEntity;



public interface ConditionMapper{

    @Select("select * from condition where id=#{id}")
    public ConditionEntity getCondition(int id);

}