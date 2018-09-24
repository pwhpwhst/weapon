package com.pwhTest.MFTest.MFCode;

import java.util.Map;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;


/**
 * 
 * @author pwh
 * 
 */
public class MFCodeImpl implements IMFCode {

	// 公共方法及构造器

	public MFCodeImpl() {
		this.mFCodeStaticImpl=new MFCodeStaticImpl();
		initMF();
	}
	
	public MFCodeImpl(MFCodeStaticImpl mFCodeStaticImpl) {
		this.mFCodeStaticImpl = mFCodeStaticImpl;
		initMF();
	}

	public byte getIdFromGrid(int grid) {
		return this.gridToId.get(new Grid((byte) grid)).content;
	}

	public byte getGridFromId(int id) {
		return this.idToGrid.get(new Grid((byte) id)).content;
	}

	public static void showMF(MFCodeImpl mfCodeImpl) {
		for (int i1 = 0; i1 <= 5; i1++)
			mfCodeImpl.showMFOneFace(i1,
					mfCodeImpl.mFCodeStaticImpl.LOCATION_VIEW_TO_IDS[i1]);
	}

	public static String[] getMFDescription(MFCodeImpl mfCodeImpl) {
		String[] _mf = new String[6];
		for (int i1 = 0; i1 <= 5; i1++) {
			_mf[i1] = mfCodeImpl.getMFOecFaceDescription(i1,
					mfCodeImpl.mFCodeStaticImpl.LOCATION_VIEW_TO_IDS[i1]);
		}
		return _mf;
	}

	public void move(int behave) {
		int _fac = behave / 3;
		byte[] mfGridsOfOneFace = mFCodeStaticImpl.LOCATION_VIEW_TO_IDS[_fac];
		byte[] _grid = new byte[8];
		// 取出二型格和三型格
		for (int i1 = 0; i1 <= 7; i1++)
			_grid[i1] = getGridFromId(mfGridsOfOneFace[i1]);

		changeMFGridStatus(behave, _grid);
		changMFGridPosition(behave, _grid);
	}

	public void move(String behave) {
		if ("".equals(behave))
			return;
		String[] behaves = behave.split(",");
		for (int i1 = 0; i1 < behaves.length; i1++) {
			if (!behaves[i1].matches("\\s*"))
				move(Integer.valueOf(behaves[i1]));
		}
	}

	public void remove(String behave) {
		if ("".equals(behave))
			return;
		String[] behaves = behave.split(",");
		for (int i1 = behaves.length - 1; i1 >= 0; i1--) {
			if (!behaves[i1].matches("\\s*"))
				remove(Integer.valueOf(behaves[i1]));
		}
	}

	public void remove(int behave) {
		int _u1 = behave - (behave % 3);
		int _u2 = behave % 3;
		move(_u1 + (4 - _u2) % 3);
	}

	public void parseMFArray(String[] cols) {
		String[] _cols = new String[6];
		char[] _orderOfparseFace = { 'w', 'y', 'g', 'b', 'r', 'o' };
		for (int i1 = 0; i1 < _orderOfparseFace.length; i1++) {
			for (int i2 = 0; i2 < cols.length; i2++) {
				if (cols[i2].charAt(4) == _orderOfparseFace[i1]) {
					_cols[i1] = cols[i2];
					break;
				}
			}
		}

		byte[][] colNums = new byte[6][9];
		parseCols(_cols, colNums);
		parseMFArray(colNums);
	}

	public void parseMFArray(byte[][] MFArray) {

		for (int locationId = 1; locationId <= 20; locationId++) {
			Grid _gridForParse = new Grid((byte) 0);
			int grid = 0;
			int status = 0;
			int gridType = 0;
			byte _oneCol = 6;
			int _oneFac = 6;
			for (int fac = 0; fac <= 5; fac++) {
				byte _pos = mFCodeStaticImpl.ID_TO_LOCATION_VIEW[locationId - 1][fac];
				if (_pos == 9)
					continue;
				else {
					gridType++;
					if (gridType == 1) {
						_oneCol = MFArray[fac][_pos];
						_oneFac = fac;
					}
				}
				byte _col = MFArray[fac][_pos];
				switch (_col) {
				case 0:
					grid += 0x10;
					status = fac >> 1;
					break;
				case 1:
					grid += 0x30;
					status = fac >> 1;
					break;
				case 2:
					grid += 0x04;
					break;
				case 3:
					grid += 0x0c;
					break;
				case 4:
					grid += 0x01;
					break;
				case 5:
					grid += 0x03;
					break;
				default:
				}
			}
			if (gridType == 2) {
				_gridForParse.content = (byte) grid;
				byte _gridId = mFCodeStaticImpl.LOCATION_TO_ID
						.get(_gridForParse).content;
				byte _stdCol = mFCodeStaticImpl.STANDARDIZE_GRIDS[_gridId - 1][_oneCol >> 1];
				int _actualPos = mFCodeStaticImpl.getActualPos(locationId, 0);
				byte _stdFac = mFCodeStaticImpl.STD_GRID_COL_TO_LOCAL_COL[_actualPos][_stdCol >> 1];
				if (_stdFac == _oneFac) {
					status = 0;
				} else
					status = 1;
			}
			setIdToGrid((byte) grid, locationId);
			setGridToId(locationId, (byte) (grid + (status << 6)));
		}

	}

	public int extractMFSubStatus(String type, boolean isInvert) {
		//xyz
		// int 4字节
		byte[] grid00 = { 0x11, 0x14, 0x13, 0x1c };// for cross of white face
		byte[] grid01 = { 0x15, 0x17, 0x1f, 0x1d };// for conner of white face
		byte[] grid02 = { 0x05, 0x07, 0x0f, 0x0d };
		byte[] grid03 = { 0x31, 0x3c, 0x33, 0x34 };// for cross of yellow face
		byte[] grid04 = { 0x3d, 0x3f, 0x37, 0x35 };// for conner of yellow face
		byte[] grid = null;
		if ("00".equals(type) && !isInvert || "03".equals(type) && isInvert) {
			grid = grid00;
		}else if ("01".equals(type) && !isInvert || "04".equals(type)
				&& isInvert) {
			grid = grid01;
		}else if ("02".equals(type)) {
			grid = grid02;
		}else if ("03".equals(type) && !isInvert || "00".equals(type)
				&& isInvert) {
			grid = grid03;
		}else if ("04".equals(type) && !isInvert || "01".equals(type)
				&& isInvert) {
			grid = grid04;
		}
			
		int result = 0;
		for (int i1 = 0; i1 < grid.length; i1++) {
			byte id = getIdFromGrid(grid[i1]);
			int status = getGridFromId(id) & 0xc0;
			result |= (mFCodeStaticImpl.getLocation(id) + status) << (24 - 8 * i1);
		}
		return result;
	}
	
	
	public String extractMFSubStatusExt(String type) {
		//xyz
		// int 4字节
		byte[] grid00 = { 0x11, 0x14, 0x13, 0x1c };// for cross of white face
		byte[] grid01 = { 0x15, 0x17, 0x1f, 0x1d };// for conner of white face
		byte[] grid02 = { 0x05, 0x07, 0x0f, 0x0d };
		byte[] grid03 = { 0x31, 0x3c, 0x33, 0x34 };// for cross of yellow face
		byte[] grid04 = { 0x3d, 0x3f, 0x37, 0x35 };// for conner of yellow face
		byte[] grid05 = { 0x11, 0x14, 0x13, 0x1c, 0x15};//遍历耗时：403	导入数据库耗时：564
		byte[] grid = null;
		if ("00".equals(type)) {
			grid = grid00;
		}else if ("01".equals(type)) {
			grid = grid01;
		}else if ("02".equals(type)) {
			grid = grid02;
		}else if ("03".equals(type)) {
			grid = grid03;
		}else if ("04".equals(type)) {
			grid = grid04;
		}else if ("05".equals(type)) {
			grid = grid05;
		}
		
		StringBuffer sb=new StringBuffer();
		for (int i1 = 0; i1 < grid.length; i1++) {
			byte id = getIdFromGrid(grid[i1]);
			int status = getGridFromId(id) & 0xc0;
			sb.append(mFCodeStaticImpl.getLocation(id) + status);
			if(i1!=(grid.length-1)) {
				sb.append("-");
			}
		}
		return sb.toString();
	}

	public int extractMFSubStatus(String type) {
		return extractMFSubStatus(type, false);
	}

	public int extractMFOneGridStatus(int grid) {
		byte id = getIdFromGrid(grid);
		int status = getGridFromId(id) & 0xc0;
		return status;
	}

	// //////////////////////////////////////////////////////
	private Map<Grid, Grid> gridToId;

	private Map<Grid, Grid> idToGrid;

	private MFCodeStaticImpl mFCodeStaticImpl;

	private void setGridToId(int id, byte grid) {
		idToGrid.get(new Grid((byte) id)).content = grid;
	}

	private void setIdToGrid(byte grid, int id) {
		gridToId.get(new Grid((byte) grid)).content = (byte) id;
	}

	private void showMFOneFace(int MFFaceId, byte[] locationId) {
		for (int i2 = 0; i2 <= 2; i2++) {
			showOneGridCol(MFFaceId, locationId[i2]);
		}
		System.out.println();

		showOneGridCol(MFFaceId, locationId[3]);
		System.out.print(MFFaceId);
		showOneGridCol(MFFaceId, locationId[4]);
		System.out.println();

		for (int i2 = 5; i2 <= 7; i2++) {
			showOneGridCol(MFFaceId, locationId[i2]);
		}
		System.out.println();
		System.out.println();
	}

	private String getMFOecFaceDescription(int MFFaceId, byte[] locationId) {
		StringBuffer str = new StringBuffer();
		for (int i2 = 0; i2 <= 2; i2++) {
			str.append(showOneGridCol(MFFaceId, locationId[i2], false));
			str.append(",");
		}
		str.append(showOneGridCol(MFFaceId, locationId[3], false));
		str.append(",");
		str.append(MFFaceId);
		str.append(",");
		str.append(showOneGridCol(MFFaceId, locationId[4], false));
		str.append(",");
		for (int i2 = 5; i2 <= 7; i2++) {
			str.append(showOneGridCol(MFFaceId, locationId[i2], false));
			if (i2 != 7)
				str.append(",");
		}
		return str.toString();
	}

	private Byte showOneGridCol(int MFFaceId, byte locationId,
			boolean isShowInScreen) {
		// 得到小格并分析其状态和拥有的颜色
		byte grid = getGridFromId(locationId);
		int status = ((grid & 0xc0) >> 6);
		// 获得白面上小格的标准色
		int _pos = mFCodeStaticImpl.getActualPos(locationId, status);
		byte stdGridCol = mFCodeStaticImpl.LOCAL_COL_TO_STD_GRID_COL[_pos][MFFaceId];
		// 获得原色到标准色的映射
		byte gridId = mFCodeStaticImpl.getIdFromLocation(grid & 0x3f);
		byte[] StdColToOriCol = mFCodeStaticImpl.NORMALIZE_GRIDS[gridId - 1];
		byte oriCol = StdColToOriCol[(stdGridCol >> 1)];
		if (isShowInScreen)
			System.out.print(Byte.valueOf((byte) oriCol));
		return Byte.valueOf((byte) oriCol);
	}

	private void showOneGridCol(int MFFaceId, byte locationId) {
		showOneGridCol(MFFaceId, locationId, true);
	}

	private void changeMFGridStatus(int behave, byte[] _grid) {
		int _fac = behave / 3;
		int[] type3 = { 0, 2, 5, 7 };
		// 改变三型格状态
		if (behave % 3 != 2)
			for (int i1 = 0; i1 <= 3; i1++) {
				int _id = type3[i1];
				int _presentGrid = (int) _grid[_id];
				int _presentStatus = (_presentGrid) >> 6;
				_presentStatus = _presentStatus >= 0 ? _presentStatus : 2;
				int _nextStatus = (6 - (_fac >> 1) - _presentStatus) % 3;
				_presentGrid = (_nextStatus << 6) + (_presentGrid & 0x3F);
				_grid[_id] = (byte) _presentGrid;
			}
		// 改变二型格状态
		if (behave % 3 == 0) {
			if (_fac == 0 || _fac == 3 || _fac == 5) {
				_grid[1] = (byte) (_grid[1] ^ 64);
				_grid[6] = (byte) (_grid[6] ^ 64);
			} else if (_fac == 1 || _fac == 2 || _fac == 4) {
				_grid[3] = (byte) (_grid[3] ^ 64);
				_grid[4] = (byte) (_grid[4] ^ 64);
			}
		} else if (behave % 3 == 1) {
			if (_fac == 0 || _fac == 3 || _fac == 5) {
				_grid[3] = (byte) (_grid[3] ^ 64);
				_grid[4] = (byte) (_grid[4] ^ 64);
			} else if (_fac == 1 || _fac == 2 || _fac == 4) {
				_grid[1] = (byte) (_grid[1] ^ 64);
				_grid[6] = (byte) (_grid[6] ^ 64);
			}
		} else {
			_grid[1] = (byte) (_grid[1] ^ 64);
			_grid[6] = (byte) (_grid[6] ^ 64);
			_grid[3] = (byte) (_grid[3] ^ 64);
			_grid[4] = (byte) (_grid[4] ^ 64);
		}
	}

	private void changMFGridPosition(int behave, byte[] _grid) {
		int _fac = behave / 3;
		byte[] mfGridsOfOneFace = mFCodeStaticImpl.LOCATION_VIEW_TO_IDS[_fac];
		if (behave % 3 == 0) {
			int[] _p = { 5, 3, 0, 6, 1, 7, 4, 2 };
			for (int i1 = 0; i1 <= 7; i1++) {
				setGridToId(mfGridsOfOneFace[i1], _grid[_p[i1]]);
				setIdToGrid((byte) (_grid[_p[i1]] & 0x3f), mfGridsOfOneFace[i1]);
			}
		} else if (behave % 3 == 1) {
			int[] _p = { 2, 4, 7, 1, 6, 0, 3, 5 };
			for (int i1 = 0; i1 <= 7; i1++) {
				setGridToId(mfGridsOfOneFace[i1], _grid[_p[i1]]);
				setIdToGrid((byte) (_grid[_p[i1]] & 0x3f), mfGridsOfOneFace[i1]);
			}
		} else {
			int[] _p = { 7, 6, 5, 4, 3, 2, 1, 0 };
			for (int i1 = 0; i1 <= 7; i1++) {
				setGridToId(mfGridsOfOneFace[i1], _grid[_p[i1]]);
				setIdToGrid((byte) (_grid[_p[i1]] & 0x3f), mfGridsOfOneFace[i1]);
			}
		}
	}

	private void initMF() {
		gridToId = new HashMap<Grid, Grid>();
		idToGrid = new HashMap<Grid, Grid>();
		mFCodeStaticImpl.initLOCATION_TO_ID(gridToId, idToGrid);
	}

	private void parseCols(String[] cols, byte[][] colNums) {
		for (int i1 = 0; i1 <= 5; i1++)
			for (int i2 = 0; i2 <= 8; i2++) {
				switch ((cols[i1].charAt(i2))) {
				case 'w':
					colNums[i1][i2] = 0;
					break;
				case 'y':
					colNums[i1][i2] = 1;
					break;
				case 'g':
					colNums[i1][i2] = 2;
					break;
				case 'b':
					colNums[i1][i2] = 3;
					break;
				case 'r':
					colNums[i1][i2] = 4;
					break;
				case 'o':
					colNums[i1][i2] = 5;
					break;
				default:
					;
				}
			}
	}
	
	


}
