package com.example.demo.dto;

//import java.util.Map;
import io.swagger.annotations.ApiModelProperty;

public class QueryParamDTO
{
//	@ApiModelProperty(value = "conditions",required = true)
//	private Map<String, Object> conditions;
//	@ApiModelProperty(value = "returnFields",required = true)
//    private String[] returnFields;
	@ApiModelProperty(value = "pageStart",required = true)
    private Long pageStart;
	@ApiModelProperty(value = "pageSize",required = true)
    private Long pageSize;

//	public void setConditions(Map<String, Object> conditions){
//		this.conditions=conditions;
//	}
//
//	public Map<String, Object> getConditions(){
//		return this.conditions;
//	}
//
//	public void setReturnFields(String[] returnFields){
//		this.returnFields=returnFields;
//	}
//
//	public String[] getReturnFields(){
//		return this.returnFields;
//	}

	public void setPageStart(Long pageStart){
		this.pageStart=pageStart;
	}

	public Long getPageStart(){
		return this.pageStart;
	}

	public void setPageSize(Long pageSize){
		this.pageSize=pageSize;
	}

	public Long getPageSize(){
		return this.pageSize;
	}

	public QueryParamDTO(){}
}