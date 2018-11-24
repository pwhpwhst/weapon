package com.pwhTest;

import java.util.List;

import com.pwhTest.Dao.impl.ErpClassDao;
import com.pwhTest.Dao.impl.ErpClassDaoImpl;
import com.pwhTest.model.ErpClass;

public class Test {
	public static void main(String[] args) {
		ErpClassDao erpClassDao=new ErpClassDaoImpl();
		List<ErpClass> list=erpClassDao.getErpClassList();
		System.out.println("fdsfgds");
	}
}
