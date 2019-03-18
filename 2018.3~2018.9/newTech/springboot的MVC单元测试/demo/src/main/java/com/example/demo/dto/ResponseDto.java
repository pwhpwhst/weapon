package com.example.demo.dto;
import java.math.BigDecimal;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

public class ResponseDto<T> implements Serializable {

	private String result;
	private String errorCode;
	private String errorMsg;
	private T data;


	public ResponseDto() {
	}

	public ResponseDto(T data) {
		this.result = "SUCCESS";
		this.data = data;
	}


	public ResponseDto(String result, T data, String errorCode, String errorMsg) {
		this.result = result;
		this.data = data;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public static ResponseDto<Long> createLong(Long value) {
		return new ResponseDto(value);
	}

	public static ResponseDto<Short> createShort(Short value) {
		return new ResponseDto(value);
	}

	public static ResponseDto<Integer> createInteger(Integer value) {
		return new ResponseDto(value);
	}

	public static ResponseDto<Float> createFloat(Float value) {
		return new ResponseDto(value);
	}

	public static ResponseDto<Double> createDouble(Double value) {
		return new ResponseDto(value);
	}

	public static ResponseDto<BigDecimal> createBigDecimal(BigDecimal value) {
		return new ResponseDto(value);
	}

	public static ResponseDto<Object> createObject(Object obj) {
		return new ResponseDto(obj);
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String toString() {
		return "ResponseDto [errorCode=" + this.errorCode + ", errorMsg=" + this.errorMsg + ", data=" + this.data + "]";
	}

}



