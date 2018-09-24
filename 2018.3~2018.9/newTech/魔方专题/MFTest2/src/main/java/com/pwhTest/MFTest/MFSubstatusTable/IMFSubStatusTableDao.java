package com.pwhTest.MFTest.MFSubstatusTable;
import java.util.List;
import java.util.Map;

public interface IMFSubStatusTableDao {

	public void addMFSubStatus( Map<String,Object> transferMap);
	
	public void addMFSubStatusBatch( List<Map<String,Object>> transferList);
}