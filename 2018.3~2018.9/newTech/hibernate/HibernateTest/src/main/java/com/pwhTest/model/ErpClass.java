package com.pwhTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "erp_class")
public class ErpClass {
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator", strategy = "identity")
	@Column(name="ID", length=32)
	private String id;
	@Column(name = "name", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	private String name;
	@Column(name = "fullname", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	private String fullname;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
	
}
