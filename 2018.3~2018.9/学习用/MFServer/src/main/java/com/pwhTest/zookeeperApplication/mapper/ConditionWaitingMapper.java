package com.pwhTest.zookeeperApplication.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pwhTest.zookeeperApplication.entity.ConditionWaitingEntity;



public interface ConditionWaitingMapper{

    @Select("select * from condition_waiting where id=#{id}")
    public ConditionWaitingEntity getConditionWaiting(int id);

}