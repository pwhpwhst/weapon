package com.pwhTest.MFTest.mapper;

import org.apache.ibatis.annotations.Select;

import com.pwhTest.MFTest.entity.ConditionWaitingEntity;






public interface ConditionWaitingMapper{

    @Select("select * from condition_waiting where id=#{id}")
    public ConditionWaitingEntity getConditionWaiting(int id);

}