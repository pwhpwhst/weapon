package com.pwhTest.MFTest.MFCode;

import java.util.Map;

public interface IMFCode {

	public byte getIdFromGrid(int grid);
	
	public byte getGridFromId(int id);
	
//	public void showMF();
	
//	public String[] getMFDescription();
	
	public void move(int behave);
	
	public void move(String behave);
	
	public void remove(int behave);
	
	public void remove(String behave);
	
	public void parseMFArray( byte[][] MFArray);
	
	public void parseMFArray( String[] cols);
	
	public  int  extractMFSubStatus(String type,boolean isInvert);
	public  int  extractMFSubStatus(String type);
	
	public String extractMFSubStatusExt(String type);
	
	public final String X_AXIS="X";
	public final String Y_AXIS="Y";
	public final String Z_AXIS="Z";
	public final String GRID="grid";
	public final String LOCATION="location";	
	
}
