package com.pwhTest.MFTest.MFSubstatusTable;

import java.util.Map;

public interface CreateMFSubStatusTable {
	public Map<String,Object> pollOneStaus();
	public void conFirmStatus(Map<String,Object> transferMap);
	public boolean isFinish();
}
