package com.pwhTest.MFTest.MFCode;

import java.util.Map;

public interface IMFCodeStatic {
	public void showIdToLocation(int id);

	public byte getLocation(int id);

	public byte getIdFromLocation(int location);

	public void showLocationToId(int location);

	public Map<Grid, Grid> LOCATION_TO_ID = null;

	public Map<Grid, Grid> ID_TO_LOCATION = null;

}
