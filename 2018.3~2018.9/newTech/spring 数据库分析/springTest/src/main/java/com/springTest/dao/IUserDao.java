package com.springTest.dao;
import com.springTest.entity.UserEntity;
import java.util.List;
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.transaction.annotation.Propagation;

@Transactional(propagation=Propagation.REQUIRED)
public interface IUserDao{

	public void save(UserEntity user);

	public List<UserEntity> getUserList();

}







