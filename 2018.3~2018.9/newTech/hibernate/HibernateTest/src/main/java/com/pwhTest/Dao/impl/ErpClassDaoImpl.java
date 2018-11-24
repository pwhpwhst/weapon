package com.pwhTest.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pwhTest.model.ErpClass;

public class ErpClassDaoImpl implements ErpClassDao{
	public List<ErpClass> getErpClassList(){
		Transaction tx = null;
		List<ErpClass> resultList=new ArrayList<ErpClass>();
		try {
			Configuration cfg = new Configuration();
			SessionFactory sf = cfg.configure().buildSessionFactory();
			Session session = sf.openSession();
			tx = session.beginTransaction();
			
			StringBuffer hqlBuffer=new StringBuffer();
			hqlBuffer.append(" from ErpClass");
			Query query=session.createQuery(hqlBuffer.toString());
			resultList=query.list();
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				e1.printStackTrace();
			}
		} finally {
			return resultList;
		}
	}
}
