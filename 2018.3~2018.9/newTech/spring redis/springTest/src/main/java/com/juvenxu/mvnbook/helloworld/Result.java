package com.juvenxu.mvnbook.helloworld;

public class Result
{
	private Boolean success;

	private Integer data;

	public void setSuccess(Boolean success){
		this.success=success;
	}

	public Boolean getSuccess(){
		return this.success;
	}

	public void setData(Integer data){
		this.data=data;
	}

	public Integer getDate(){
		return this.data;
	}
}